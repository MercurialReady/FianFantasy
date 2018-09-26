package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.HomeContract;
import com.example.mercu.finalfantasy.contract.main.RootContract;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/8.
 */

public class HomePresenter extends RxPresenter<HomeContract.View>
        implements HomeContract.Presenter
{
    @Inject
    public HomePresenter()
    {

    }
}
