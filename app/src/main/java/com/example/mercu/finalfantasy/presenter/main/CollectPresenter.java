package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.CollectContract;
import com.example.mercu.finalfantasy.model.DataManager;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/26.
 */

public class CollectPresenter extends RxPresenter<CollectContract.View>
                implements CollectContract.Presenter
{
    @Inject
    public CollectPresenter(DataManager manager)
    {
        mDataManager = manager;
    }


}
