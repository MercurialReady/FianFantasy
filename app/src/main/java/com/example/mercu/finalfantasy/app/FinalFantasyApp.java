package com.example.mercu.finalfantasy.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.support.v7.widget.Toolbar;

import com.example.mercu.finalfantasy.base.BaseMvpActivity;
import com.example.mercu.finalfantasy.base.BaseToolBarActivity;
import com.example.mercu.finalfantasy.base.IToolBar;
import com.example.mercu.finalfantasy.di.component.AppComponent;

import com.example.mercu.finalfantasy.di.component.DaggerAppComponent;
import com.example.mercu.finalfantasy.di.module.AppModule;
import com.example.mercu.finalfantasy.di.module.HttpModule;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by qicheng on 2018/8/7.
 */

public class FinalFantasyApp extends Application
        implements HasActivityInjector
{
    @Inject
    public DispatchingAndroidInjector<Activity> mAndroidInjector;

    private static Context mContext;
    private AppComponent mAppComponent;
    private static FinalFantasyApp instance;
    private static int screen_width;
    private static int screen_height;

    public static Context getAppContext()
    {
        return mContext;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
        instance = this;
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        mAppComponent = DaggerAppComponent.builder()
                .httpModule(new HttpModule())
                .appModule(new AppModule(instance))
                .build();
        mAppComponent.inject(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState)
            {
                if(activity instanceof BaseMvpActivity)
                {
                    //Toolbar toolbar = activity.findViewById(R.id.common_toolbar);
                    //FrameLayout frameLayout = toolbar.findViewById(R.id.toolbar_frame);
                    //frameLayout.addView(LayoutInflater.from(instance)
                    //        .inflate(((BaseMvpActivity) activity).getToolBarId(),null));
                    //((BaseMvpActivity) activity).setSupportActionBar(toolbar);
                }
            }

            @Override
            public void onActivityStarted(Activity activity)
            {

            }

            @Override
            public void onActivityResumed(Activity activity)
            {

            }

            @Override
            public void onActivityPaused(Activity activity)
            {

            }

            @Override
            public void onActivityStopped(Activity activity)
            {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState)
            {

            }

            @Override
            public void onActivityDestroyed(Activity activity)
            {

            }
        });
    }

    static
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }


    @Override
    public AndroidInjector<Activity> activityInjector()
    {
        return mAndroidInjector;
    }

    public static synchronized FinalFantasyApp getInstance() {
        return instance;
    }

    public static int getScreen_width()
    {
        return screen_width;
    }

    public static int getScreen_height()
    {
        return screen_height;
    }
}
