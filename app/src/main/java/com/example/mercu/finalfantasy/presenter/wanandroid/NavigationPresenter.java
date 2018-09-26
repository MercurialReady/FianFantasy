package com.example.mercu.finalfantasy.presenter.wanandroid;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.wanandroid.NavigationContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/12.
 */

public class NavigationPresenter extends RxPresenter<NavigationContract.View>
                    implements NavigationContract.Presenter
{
    @Inject
    public NavigationPresenter(DataManager dataManagerFromInject)
    {
        mDataManager = dataManagerFromInject;
    }

    @Override
    public void getNavigationData()
    {
        mDataManager.getNavigationListData()
                .compose(RxTransformer.<BaseResponse<List<NavigationListData>>>scheduleHelper())
                .compose(RxTransformer.<List<NavigationListData>>handleResult())
                .subscribeWith(new BaseObserver<List<NavigationListData>>(mView,true)
                {
                    @Override
                    public void onNext(List<NavigationListData> value)
                    {
                        mView.showTag(value);
                        mView.showContent(value);
                    }
                });
    }
}
