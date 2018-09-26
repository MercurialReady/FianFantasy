package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.RegisterContract;
import com.example.mercu.finalfantasy.model.DataManager;

import javax.inject.Inject;

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


}
