package com.example.mercu.finalfantasy.utils.rx;

import android.util.Log;

import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.http.BaseGankResponse;
import com.example.mercu.finalfantasy.model.http.BaseResponse;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qicheng on 2018/8/30.
 */

public class RxTransformer
{
    public static <T> FlowableTransformer<T,T> flowScheduleHelper()
    {
        return new FlowableTransformer<T, T>()
        {
            @Override
            public Publisher<T> apply(Flowable<T> upstream)
            {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T,T> scheduleHelper()
    {
        return new ObservableTransformer<T, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream)
            {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<BaseResponse<T>,T> handleResult()
    {
        return new ObservableTransformer<BaseResponse<T>, T>()
        {
            @Override
            public Observable<T> apply(Observable<BaseResponse<T>> upstream)
            {
                return upstream.flatMap(new Function<BaseResponse<T>, Observable<T>>()
                {
                    @Override
                    public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception
                    {
                        if(tBaseResponse.getErrorCode() == BaseResponse.SUCCESS &&
                                        tBaseResponse.getData() != null)
                        {
                            return createData(tBaseResponse.getData());
                        }
                        else
                        {
                            return Observable.error(new Throwable("转换数据错误"));
                        }
                    }
                });
            }
        };
    }

    public static <T> ObservableTransformer<BaseGankResponse<T>,T> handleGankReponse()
    {
        return new ObservableTransformer<BaseGankResponse<T>, T>()
        {
            @Override
            public Observable<T> apply(Observable<BaseGankResponse<T>> upstream)
            {
                return upstream.flatMap(new Function<BaseGankResponse<T>, Observable<T>>()
                {
                    @Override
                    public Observable<T> apply(BaseGankResponse<T> tBaseGankResponse) throws Exception
                    {
                        Log.d("Mercurial","transformer" + (tBaseGankResponse.isError() == false));
                        Log.d("Mercurial","transformer" + (tBaseGankResponse.getData() != null));
                        if(tBaseGankResponse.isError() == false &&
                                tBaseGankResponse.getData() != null)
                        {
                            return createData(tBaseGankResponse.getData());
                        }
                        else
                        {
                            return Observable.error(new Throwable("转换数据错误"));
                        }
                    }
                });
            }
        };
    }

    //因为有个SB接口会返回null，fuck
    public static <T> ObservableTransformer<BaseResponse<T>,T> handleCollectResult()
    {
        return new ObservableTransformer<BaseResponse<T>, T>()
        {
            @Override
            public Observable<T> apply(Observable<BaseResponse<T>> upstream)
            {
                return upstream.flatMap(new Function<BaseResponse<T>, Observable<T>>()
                {
                    @Override
                    public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception
                    {
                        if(tBaseResponse.getErrorCode() == BaseResponse.SUCCESS)
                        {
                            //这里仅仅这种数据需要这样，直接强制类型转换
                            return createData((T)new FeedArticleData());
                        }
                        else
                        {
                            return Observable.error(new Throwable("转换数据错误"));
                        }
                    }
                });
            }
        };
    }

    private static <T> Observable<T> createData(final T data)
    {
        return Observable.create(new ObservableOnSubscribe<T>()
        {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception
            {
                try
                {
                    e.onNext(data);
                    e.onComplete();
                }
                catch (Throwable throwable)
                {
                    e .onError(throwable);
                }

            }
        });
    }
}
