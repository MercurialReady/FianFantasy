package com.example.mercu.finalfantasy.presenter.wanandroid;

import android.widget.Toast;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.wanandroid.MostUsefulContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by qicheng on 2018/8/29.
 */

public class MostUsefulPresenter extends RxPresenter<MostUsefulContract.View> implements MostUsefulContract.Presenter
{
    @Inject
    public MostUsefulPresenter(DataManager dataManagerFromInject)
    {
        mDataManager = dataManagerFromInject;
    }

    @Override
    public void getBannerData()
    {
        addRxSubscribe(mDataManager.getBannerData()
                .compose(RxTransformer.<BaseResponse<List<BannerData>>>scheduleHelper())
                .compose(RxTransformer.<List<BannerData>>handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,true)
                {
                    @Override
                    public void onNext(List<BannerData> value)
                    {
                        mView.showBannerData(value);
                    }
                }));

    }

    @Override
    public void getUsefulArticle(int num)
    {
        addRxSubscribe(mDataManager.getFeedArticleList(num)
                .compose(RxTransformer.<BaseResponse<FeedArticleListData>>scheduleHelper())
                .compose(RxTransformer.<FeedArticleListData>handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,true)
                {
                    @Override
                    public void onNext(FeedArticleListData value)
                    {
                        mView.showUsefulArticle(value);
                    }
                }));
    }
}
