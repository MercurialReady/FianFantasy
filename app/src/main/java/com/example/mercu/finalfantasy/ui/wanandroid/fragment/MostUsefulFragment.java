package com.example.mercu.finalfantasy.ui.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.wanandroid.MostUsefulContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.dao.DbHelperImpl;
import com.example.mercu.finalfantasy.model.http.HttpHelperImpl;
import com.example.mercu.finalfantasy.model.pres.PreferenceHelperImpl;
import com.example.mercu.finalfantasy.presenter.wanandroid.MostUsefulPresenter;
import com.example.mercu.finalfantasy.ui.wanandroid.adapter.MostUsefulAdapter;
import com.example.mercu.finalfantasy.utils.view.GlideImageLoader;
import com.example.mercu.finalfantasy.utils.view.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qicheng on 2018/8/29.
 */

public class MostUsefulFragment extends BaseMvpFragment<MostUsefulPresenter>
        implements MostUsefulContract.View
{
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Banner banner;
    private List<String> mBannerTitleList;
    private List<String> mBannerImageList;
    private List<String> mBannerUrlList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_most_useful;
    }

    @Override
    protected void initView()
    {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener()
        {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout)
            {
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout)
            {
                refreshlayout.finishRefresh(2000);
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                clickChildEvent(view,position);
                return true;
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                //todo:到具体细节展览界面
                Log.d("Mercurial","click article");
            }
        });

        //此处mAdapter里面的数据是无意义的，依赖注入的是new ArrayList，需要后面setData
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        LinearLayout header = (LinearLayout) mLayoutInflater.inflate(R.layout.show_banner,null);
        banner = header.findViewById(R.id.inner_banner);
        header.removeView(banner);
        mAdapter.addHeaderView(banner);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.getBannerData();
        mPresenter.getUsefulArticle(0);
        Log.d("Mercurial","load MostUsefulFragment");
        initBanner();
    }

    @Override
    public void showBannerData(List<BannerData> mData)
    {
        for(BannerData data : mData)
        {
            mBannerTitleList.add(data.getTitle());
            mBannerImageList.add(data.getImagePath());
            mBannerUrlList.add(data.getUrl());
        }
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mBannerImageList);
        banner.setBannerTitles(mBannerTitleList);
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(mData.size()*400);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener()
        {
            @Override
            public void OnBannerClick(int position)
            {
                //todo；跳到detail界面
            }
        });
        banner.start();
    }

    @Override
    public void showUsefulArticle(FeedArticleListData mData)
    {
        mAdapter.setNewData(mData.getDatas());
    }

    @Override
    public void collectArticleSuccess(int position, FeedArticleData article)
    {
        mAdapter.setData(position,article);

    }

    @Override
    public void cancelCollectArticleSuccess(int position, FeedArticleData article)
    {
        mAdapter.setData(position,article);
    }

    @Override
    public void cancelFromCollect(int id)
    {
//        int position = 0;
//        for(int i = 0;i < mAdapter.getData().size();i++)
//        {
//            if(((FeedArticleData) mAdapter.getData().get(i)).getId() == id)
//            {
//                position = i;
//                Logger.d("cannot find match item");
//                break;
//            }
//        }
//        FeedArticleData data = (FeedArticleData) mAdapter.getData().get(position);
//        data.setCollect(false);
//        mAdapter.setData(position,data);
//        Logger.d(((FeedArticleData) mAdapter.getData().get(1)).getId() + "id");
//        Logger.d(((FeedArticleData) mAdapter.getData().get(1)).getAuthor() + "author");
//        Logger.d("position = ",position);
        mPresenter.getUsefulArticle(0);
    }

    private void initBanner()
    {
        mBannerImageList = new ArrayList<>();
        mBannerTitleList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
    }

    private void clickChildEvent(View view, int position)
    {
        switch(view.getId())
        {
            case R.id.collect:
            {
                if(((FeedArticleData)mAdapter.getData().get(position)).isCollect())
                {
                    mPresenter.cancelCollectArticle(position,(FeedArticleData) mAdapter.getData().get(position));
                }
                else
                {
                    mPresenter.addCollectArticle(position,(FeedArticleData) mAdapter.getData().get(position));
                }
            }
            break;
        }
    }
}
