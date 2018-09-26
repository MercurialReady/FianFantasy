package com.example.mercu.finalfantasy.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by qicheng on 2018/8/3.
 */

//类的职责:1.presenter的注入
//    2.DaggerAndroid
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity
        implements HasSupportFragmentInjector,BaseView,IToolBar
{
    @Inject
    public DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Inject
    public T mPresenter;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        if(mPresenter != null)
        {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy()
    {
        if(mPresenter != null)
        {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

//    @Override
//    public void useNightMode(boolean isNightMode)
//    {
//        //这个换肤方案不靠谱！
//        if(isNightMode)
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        else
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//        recreate();
//    }
}
