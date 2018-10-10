package com.example.mercu.finalfantasy.contract.wanandroid;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;

import java.util.List;

/**
 * Created by qicheng on 2018/8/29.
 */

public interface MostUsefulContract
{
    interface View extends BaseView
    {
        void showBannerData(List<BannerData> mData);

        void showUsefulArticle(FeedArticleListData mData);

        void collectArticleSuccess(int position,FeedArticleData article);

        void cancelCollectArticleSuccess(int position,FeedArticleData article);

        void cancelFromCollect(int id);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getBannerData();

        void getUsefulArticle(int num);

        void addCollectArticle(int position,FeedArticleData article);

        void cancelCollectArticle(int position,FeedArticleData article);

        boolean getLoginStatus();
    }
}
