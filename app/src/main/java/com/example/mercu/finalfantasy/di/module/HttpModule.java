package com.example.mercu.finalfantasy.di.module;

import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.di.qualifier.DoubanURL;
import com.example.mercu.finalfantasy.di.qualifier.GankURL;
import com.example.mercu.finalfantasy.di.qualifier.WanAndroidURL;
import com.example.mercu.finalfantasy.di.qualifier.ZhihuURL;
import com.example.mercu.finalfantasy.model.http.api.Douban;
import com.example.mercu.finalfantasy.model.http.api.Gank;
import com.example.mercu.finalfantasy.model.http.api.WanAndroid;
import com.example.mercu.finalfantasy.model.http.api.Zhihu;
import com.example.mercu.finalfantasy.model.http.cookie.CookieManager;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mercu on 2018/8/23.
 */

@Module
public class HttpModule
{
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder()
    {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder()
    {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder)
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        File file = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(file,1024 * 1024 * 50);
        builder.cache(cache);
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(new CookieManager(FinalFantasyApp.getAppContext()));
        return builder.build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder,OkHttpClient client,String host)
    {
        return  builder
                .baseUrl(host)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    @GankURL
    Retrofit provideGank(Retrofit.Builder builder, OkHttpClient client)
    {
        return createRetrofit(builder,client,Gank.HOST);
    }

    @Singleton
    @Provides
    Gank provideGankApi(@GankURL Retrofit retrofit)
    {
        return retrofit.create(Gank.class);
    }

    @Singleton
    @Provides
    @WanAndroidURL
    Retrofit provideWanandroid(Retrofit.Builder builder, OkHttpClient client)
    {
        return createRetrofit(builder,client,WanAndroid.HOST);
    }

    @Singleton
    @Provides
    WanAndroid provideWanAndroidApi(@WanAndroidURL Retrofit retrofit)
    {
        return retrofit.create(WanAndroid.class);
    }

    @Singleton
    @Provides
    @ZhihuURL
    Retrofit provideZhihu(Retrofit.Builder builder, OkHttpClient client)
    {
        return createRetrofit(builder,client,Zhihu.HOST);
    }

    @Singleton
    @Provides
    Zhihu provideZhihuApi(@ZhihuURL Retrofit retrofit)
    {
        return retrofit.create(Zhihu.class);
    }

    @Singleton
    @Provides
    @DoubanURL
    Retrofit provideDouban(Retrofit.Builder builder, OkHttpClient client)
    {
        return createRetrofit(builder,client,Douban.HOST);
    }

    @Singleton
    @Provides
    Douban provideDoubanApi(@DoubanURL Retrofit retrofit)
    {
        return retrofit.create(Douban.class);
    }
}
