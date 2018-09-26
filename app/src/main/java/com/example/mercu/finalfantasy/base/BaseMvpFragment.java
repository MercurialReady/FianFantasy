package com.example.mercu.finalfantasy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by qicheng on 2018/8/3.
 */

/*这个类的职责：1.presenter的依赖注入
    2.DaggerAndroid
*/
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment
    implements BaseView
{
    @Inject
    public T mPresenter;

    @Inject
    public BaseQuickAdapter mAdapter;

    public static <T extends BaseMvpFragment>T getInstance(Class clazz,Bundle bundle)
    {
        T fragment = null;
        try
        {
            fragment = (T) clazz.newInstance();
            fragment.setArguments(bundle);
        } catch (java.lang.InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter != null)
        {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView()
    {
        if(mPresenter != null)
        {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }

    @Override
    public void showError()
    {
        setState(LoadingPage.STATE_ERROR);
        Log.d("Mercurial","get data error");
    }

    @Override
    public void showEmpty()
    {
        setState(LoadingPage.STATE_EMPTY);
        Log.d("Mercurial","get data empty");
    }

    @Override
    public void showNormal()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }

    @Override
    public void showLoading()
    {
        setState(LoadingPage.STATE_LOADING);
    }
}
