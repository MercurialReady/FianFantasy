package com.example.mercu.finalfantasy.presenter.main;

import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.main.CollectContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxBus;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;
import com.example.mercu.finalfantasy.utils.view.Logger;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/9/28.
 */

public class CollectPresenter extends RxPresenter<CollectContract.View>
                        implements CollectContract.Presenter
{
    @Inject
    public CollectPresenter(DataManager manager)
    {
        mDataManager = manager;
    }


    @Override
    public void getCollectArticle(int num)
    {
        addRxSubscribe(mDataManager.getCollectList(num)
                .compose(RxTransformer.<BaseResponse<FeedArticleListData>>scheduleHelper())
                .compose(RxTransformer.<FeedArticleListData>handleResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,true)
                {
                    @Override
                    public void onNext(FeedArticleListData value)
                    {
                        mView.showCollectArticle(value);
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
                        Logger.d("author = " + article.getAuthor());
                        RxBus.getsInstance().post(Constants.CANCEL_COLLECT,article.getId());
                    }
                }));
    }
}
