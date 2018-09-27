package com.example.mercu.finalfantasy.presenter.main;

import android.util.Log;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.RegisterContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Mercu on 2018/9/26.
 */

public class RegisterPresenter extends RxPresenter<RegisterContract.View>
                        implements RegisterContract.Presenter
{
    @Inject
    public RegisterPresenter(DataManager manager)
    {
        mDataManager = manager;
    }


    @Override
    public void register(String account, String password, String repassword)
    {
        addRxSubscribe(mDataManager.getRegisterData(account,password,repassword)
                .compose(RxTransformer.<BaseResponse<LoginData>>scheduleHelper())
                .compose(RxTransformer.<LoginData>handleResult())
                .subscribeWith(new ResourceObserver<LoginData>()
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mView.registerSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.e("Mercurial","register fail");
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                }));
    }

    @Override
    public void autoLogin(final LoginData data)
    {
        addRxSubscribe(mDataManager.getLoginData(data.getUsername(),data.getPassword())
                .compose(RxTransformer.<BaseResponse<LoginData>>scheduleHelper())
                .compose(RxTransformer.<LoginData>handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,true)
                {
                    @Override
                    public void onNext(LoginData value)
                    {
                        mDataManager.setLoginAccount(data.getUsername());
                        mDataManager.setLoginPassword(data.getPassword());
                        mDataManager.setLoginStatus(true);
                        mView.loginSuccess(value);
                    }
                }));
    }
}
