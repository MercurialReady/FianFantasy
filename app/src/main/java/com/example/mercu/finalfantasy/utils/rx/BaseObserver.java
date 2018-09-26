package com.example.mercu.finalfantasy.utils.rx;

import android.util.Log;

import com.example.mercu.finalfantasy.base.BaseView;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Mercu on 2018/8/29.
 */

//在RxJava2.0中，subscriber与observer的区别是
//分别对应Flowable和Observable
public abstract class BaseObserver<T> extends ResourceObserver<T>
{
    private BaseView mBaseView;
    private Boolean isShowError;

    public BaseObserver(BaseView baseView, Boolean isShowError)
    {
        mBaseView = baseView;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete()
    {

    }

    @Override
    public void onError(Throwable e)
    {
        if(mBaseView == null)
        {
            return;
        }
        Log.d("Mercurial","e" + e.getMessage());
        Log.d("Mercurial","e" + e.toString());
        mBaseView.showError();
    }
}
