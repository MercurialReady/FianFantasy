package com.example.mercu.finalfantasy.contract.main;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;

/**
 * Created by qicheng on 2018/9/28.
 */

public interface CollectContract
{
    interface View extends BaseView
    {
        void showCollectArticle(FeedArticleListData articleList);

        void collectArticleSuccess(int position,FeedArticleData article);

        void cancelCollectArticleSuccess(int position,FeedArticleData article);
    }

    interface Presenter extends BasePresenter<CollectContract.View>
    {
        void getCollectArticle(int num);

        void addCollectArticle(int position,FeedArticleData article);

        void cancelCollectArticle(int position,FeedArticleData article);
    }
}
