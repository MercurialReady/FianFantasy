package com.example.mercu.finalfantasy.presenter.gank;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.gank.GirlContract;
import com.example.mercu.finalfantasy.model.DataManager;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/14.
 */

public class GirlPresenter extends RxPresenter<GirlContract.View>
                        implements GirlContract.Presenter
{
    @Inject
    public GirlPresenter(DataManager dataManagerFromInject)
    {
        mDataManager = dataManagerFromInject;
    }
}
