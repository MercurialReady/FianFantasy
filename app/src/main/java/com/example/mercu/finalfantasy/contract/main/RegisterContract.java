package com.example.mercu.finalfantasy.contract.main;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.LoginData;

/**
 * Created by Mercu on 2018/9/26.
 */

public interface RegisterContract
{
    interface View extends BaseView
    {
        void registerSuccess(LoginData data);

        void loginSuccess(LoginData data);
    }

    interface Presenter extends BasePresenter<RegisterContract.View>
    {
        void register(String account,String password,String repassword);

        void autoLogin(LoginData data);
    }
}
