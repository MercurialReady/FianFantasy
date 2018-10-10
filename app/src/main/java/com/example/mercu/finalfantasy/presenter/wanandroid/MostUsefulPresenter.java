package com.example.mercu.finalfantasy.presenter.wanandroid;

import android.widget.Toast;

import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.wanandroid.MostUsefulContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxBus;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;
import com.example.mercu.finalfantasy.utils.view.Logger;

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
    public void attachView(MostUsefulContract.View view)
    {
        super.attachView(view);
        addRxSubscribe(RxBus.getsInstance().toObservable(Constants.CANCEL_COLLECT,Integer.class)
                .compose(RxTransformer.<Integer>scheduleHelper())
                .subscribeWith(new BaseObserver<Integer>(mView,true)
                {
                    @Override
                    public void onNext(Integer value)
                    {
                        mView.cancelFromCollect(value);
                        Logger.d("id = ",value);
                    }
                }));
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

    @Override
    public void addCollectArticle(final int position, final FeedArticleData article)
    {
        //addCollectArticle(article.getId())这个方法返回的FeedArticleListData是null
        addRxSubscribe(mDataManager.addCollectArticle(article.getId())
                .compose(RxTransformer.<BaseResponse<FeedArticleListData>>scheduleHelper())
                .compose(RxTransformer.<FeedArticleListData>handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,true)
                {
                    @Override
                    public void onNext(FeedArticleListData value)
                    {
                        //value仅仅是个让数据流走下去，无意义
                        Logger.d("addCollectArticle onNext");
                        value.getDatas();
                        article.setCollect(true);
                        mView.collectArticleSuccess(position, article);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(final int position, final FeedArticleData article)
    {
        addRxSubscribe(mDataManager.cancelCollectPageArticle(article.getId(),-1)
                .compose(RxTransformer.<BaseResponse<FeedArticleListData>>scheduleHelper())
                .compose(RxTransformer.<FeedArticleListData>handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,true)
                {
                    @Override
                    public void onNext(FeedArticleListData value)
                    {
                        //value仅仅是个让数据流走下去，无意义
                        article.setCollect(false);
                        mView.cancelCollectArticleSuccess(position, article);
                    }
                }));
    }

    @Override
    public boolean getLoginStatus()
    {
        return mDataManager.getLoginStatus();
    }
}
