package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.LoginContract;
import com.example.mercu.finalfantasy.model.DataManager;

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


}
