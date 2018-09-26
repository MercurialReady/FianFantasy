package com.example.mercu.finalfantasy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by qicheng on 2018/8/3.
 */

public abstract class BaseFragment extends Fragment
{
    public LoadingPage mLoadingPage;
    private Unbinder mUnbinder;

    //懒加载相关
    private boolean isFirstLoad = true;
    private boolean isViewPrepared;
    private boolean isVisibleToUser;
    private boolean isCreatedFromViewPager;
    public LayoutInflater mLayoutInflater;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.d("Mercury","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("Mercury","onCreate");
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            isCreatedFromViewPager = bundle.getBoolean("isCreatedFromViewPager", false);
        }
        Log.d("Mercurial",getClass().getSimpleName() + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.d("Mercury","onCreateView");
        mLayoutInflater = inflater;
        if(mLoadingPage == null)
        {
            mLoadingPage = new LoadingPage(getContext())
            {
                @Override
                protected void initView()
                {
                    mUnbinder = ButterKnife.bind(BaseFragment.this,mLoadingPage.getSuccessView());
                    BaseFragment.this.initView();
                }

                @Override
                protected void loadData()
                {
                    BaseFragment.this.loadData();
                }

                @Override
                public int getLayoutId()
                {
                    return BaseFragment.this.getLayoutId();
                }
            };
        }
        isViewPrepared = true;
        lazyLoadData();
        return mLoadingPage;
    }

    public void setState(int state)
    {
        if(mLoadingPage != null)
        {
            mLoadingPage.setState(state);
        }
    }

    private void lazyLoadData()
    {
        Log.d("Mercurial","load isVisibleToUser = " + isVisibleToUser);
        Log.d("Mercurial","load isFirstLoad = " + isFirstLoad);
        Log.d("Mercurial","load isViewPrepared = " + isViewPrepared);
        Log.d("Mercurial","isCreatedFromViewPager" + isCreatedFromViewPager);
        if(isCreatedFromViewPager)
        {

        }else
        {
            if(!isVisibleToUser || !isFirstLoad || !isViewPrepared)
            {
                return;
            }
        }
        isFirstLoad = false;
        loadData();
    }

    @Override
    public void onDestroyView()
    {
        Log.d("Mercury",getClass().getSimpleName() +"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        Log.d("Mercury",getClass().getSimpleName() +"onDestroy");
        isFirstLoad = true;
        if(mUnbinder != null && mUnbinder != Unbinder.EMPTY)
        {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroy();
    }

    //此方法会在onCreateView之前调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Mercurial","setUserVisibleHint is " + isVisibleToUser);
        if(getUserVisibleHint())
        {
            this.isVisibleToUser = true;
            lazyLoadData();
        }
        else
        {
            this.isVisibleToUser = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        Log.d("Mercury",getClass().getSimpleName() + "hidden = " + hidden);
        super.onHiddenChanged(hidden);
    }

    public abstract int getLayoutId();

    //初始化View，此时layout已经inflate了，需要初始化各种view，比如findViewById等。
    protected abstract void initView();

    //从网络加载数据
    protected abstract void loadData();


}
