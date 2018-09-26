package com.example.mercu.finalfantasy.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;


import com.example.mercu.finalfantasy.app.FinalFantasyApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qicheng on 2018/8/3.
 */


public abstract class BaseActivity extends AppCompatActivity
{
    private Unbinder mUnbinder;
    private LoadingPage mLoadingPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getRootLayoutId());
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = findViewById(getFrameLayoutId());
        mLoadingPage = new LoadingPage(this)
        {
            @Override
            protected void initView()
            {
                mUnbinder = ButterKnife.bind(mLoadingPage.getSuccessView());
                BaseActivity.this.initView();
            }

            @Override
            protected void loadData()
            {
                BaseActivity.this.loadData();
            }

            @Override
            public int getLayoutId()
            {

                return BaseActivity.this.getSuccessViewId();
            }
        };
        frameLayout.addView(mLoadingPage);
        loadData();
        //ButterKnife.bind(getWindow().getDecorView());
        ActivityCollector.getActivityCollector().addActivity(this);
    }

    @Override
    protected void onDestroy()
    {
        //fragment中无需这一步
        if(mUnbinder != null && mUnbinder != Unbinder.EMPTY)
        {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        ActivityCollector.getActivityCollector().removeActivity(this);
        super.onDestroy();
    }

    public void setState(int state)
    {
        if(mLoadingPage != null)
        {
            mLoadingPage.setState(state);
        }
    }

    //activity的根布局，包含例如actionbar，FloatingActionButton之类的东西
    protected abstract int getRootLayoutId();

    //返回的是getRootLayoutId()这个布局的一个子FrameLayout，
    //这个FrameLayout将会包含的view的id是getSuccessViewId()
    //即它是一个中间layout，包含与被包含
    protected abstract int getFrameLayoutId();

    //成功加载数据后所需页面的ID
    protected abstract int getSuccessViewId();

    public abstract void initView();

    //子类重写这个方法，一般在里面执行presenter的加载数据的方法
    protected abstract void loadData();
}
