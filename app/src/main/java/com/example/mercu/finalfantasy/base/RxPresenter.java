package com.example.mercu.finalfantasy.base;

import android.text.TextUtils;

import com.example.mercu.finalfantasy.model.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by qicheng on 2018/8/6.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T>
{
    public DataManager mDataManager;
    public T mView;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view)
    {
        mView = view;
    }

    @Override
    public void detachView()
    {
        mView = null;
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
        {
            mCompositeDisposable.clear();
        }

    }

    @Override
    public void addRxSubscribe(Disposable disposable)
    {
        if(mCompositeDisposable == null)
        {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean getNightModeSate()
    {
        return false;
    }
}
