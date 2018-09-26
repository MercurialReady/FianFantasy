package com.example.mercu.finalfantasy.utils.rx;

import com.example.mercu.finalfantasy.base.BaseView;

import io.reactivex.observers.ResourceObserver;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Mercu on 2018/8/29.
 */

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T>
{
    private BaseView mBaseView;
    private Boolean isShowError;

    public BaseSubscriber(BaseView baseView, Boolean isShowError)
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
        mBaseView.showError();
    }
}
