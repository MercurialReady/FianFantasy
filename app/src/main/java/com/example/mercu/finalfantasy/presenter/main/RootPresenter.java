package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.RootContract;
import com.example.mercu.finalfantasy.contract.wanandroid.MostUsefulContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxBus;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/4.
 */

public class RootPresenter extends RxPresenter<RootContract.View>
                    implements RootContract.Presenter
{
    @Inject
    public RootPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RootContract.View view)
    {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent()
    {
        addRxSubscribe(RxBus.getsInstance().toObservable(Constants.REGISTER_SUCCESS,LoginData.class)
                .compose(RxTransformer.<LoginData>scheduleHelper())
                .subscribeWith(new BaseObserver<LoginData>(mView,true)
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mView.showLoginView(value);
                    }
                }));

        addRxSubscribe(RxBus.getsInstance().toObservable(Constants.LOGIN_SUCCESS,LoginData.class)
                .compose(RxTransformer.<LoginData>scheduleHelper())
                .subscribeWith(new BaseObserver<LoginData>(mView,true)
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mView.showLoginView(value);
                    }
                }));
    }

    @Override
    public String getLoginAccount()
    {
        return mDataManager.getLoginAccount();
    }

    @Override
    public boolean needAutoLogin()
    {
        return mDataManager.getLoginStatus();
    }

    @Override
    public boolean getLoginStatus()
    {
        return mDataManager.getLoginStatus();
    }

    @Override
    public void setLoginStatus(Boolean status)
    {
        mDataManager.setLoginStatus(status);
    }

    @Override
    public void doLogin()
    {
        mDataManager.getLoginData(mDataManager.getLoginAccount(),mDataManager.getLoginPassword())
                .compose(RxTransformer.<BaseResponse<LoginData>>scheduleHelper())
                .compose(RxTransformer.<LoginData>handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,true)
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mView.showLoginView(value);
                    }
                });
    }
}
