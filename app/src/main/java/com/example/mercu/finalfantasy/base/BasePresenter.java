package com.example.mercu.finalfantasy.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by qicheng on 2018/8/3.
 */

public interface BasePresenter<T extends BaseView>
{
    void attachView(T view);

    void detachView();

    void addRxSubscribe(Disposable disposable);

    boolean getNightModeSate();
}
