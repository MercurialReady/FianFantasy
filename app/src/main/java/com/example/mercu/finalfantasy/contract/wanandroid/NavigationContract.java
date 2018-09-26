package com.example.mercu.finalfantasy.contract.wanandroid;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;

import java.util.List;

/**
 * Created by qicheng on 2018/9/12.
 */

public interface NavigationContract
{
    interface View extends BaseView
    {
        void showTag(List<NavigationListData> title);

        void showContent(List<NavigationListData> mData);
    }

    interface Presenter extends BasePresenter<NavigationContract.View>
    {
        void getNavigationData();
    }
}
