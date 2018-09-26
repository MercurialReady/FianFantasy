package com.example.mercu.finalfantasy.contract.main;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.LoginData;

/**
 * Created by qicheng on 2018/9/4.
 */

public interface RootContract
{
    interface View extends BaseView
    {
        void showLoginView(LoginData data);
    }

    interface Presenter extends BasePresenter<View>
    {
        String getLoginAccount();

        boolean needAutoLogin();

        boolean getLoginStatus();

        void doLogin();
    }
}
