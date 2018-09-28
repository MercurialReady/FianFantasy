package com.example.mercu.finalfantasy.contract.main;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.LoginData;

/**
 * Created by qicheng on 2018/9/26.
 */

public interface LoginContract
{
    interface View extends BaseView
    {
        void loginSuccess(LoginData data);
    }

    interface Presenter extends BasePresenter<View>
    {
        void login(String account,String password);
    }
}
