package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.LoginContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/26.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View>
                implements LoginContract.Presenter
{
    @Inject
    public LoginPresenter(DataManager manager)
    {
        mDataManager = manager;
    }


    @Override
    public void login(String account, String password)
    {
        mDataManager.getLoginData(account,password)
                .compose(RxTransformer.<BaseResponse<LoginData>>scheduleHelper())
                .compose(RxTransformer.<LoginData>handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,true)
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mDataManager.setLoginAccount(value.getUsername());
                        mDataManager.setLoginPassword(value.getPassword());
                        mDataManager.setLoginStatus(true);
                        mView.loginSuccess(value);
                    }
                });
    }
}
