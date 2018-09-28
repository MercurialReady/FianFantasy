package com.example.mercu.finalfantasy.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.CollectContract;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.presenter.main.CollectPresenter;
import com.example.mercu.finalfantasy.ui.wanandroid.adapter.MostUsefulAdapter;
import com.example.mercu.finalfantasy.utils.view.Logger;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/28.
 */

public class CollectFragment extends BaseMvpFragment<CollectPresenter>
                    implements CollectContract.View
{
    @BindView(R.id.collect_refresh_layout)
    com.scwang.smartrefresh.layout.SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.collect_recyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initView()
    {
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ((MostUsefulAdapter)mAdapter).setCollectMode(true);
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

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.getCollectArticle(0);
    }

    private void clickChildEvent(View view, int position)
    {
        switch(view.getId())
        {
            case R.id.collect:
            {
                if(((FeedArticleData)mAdapter.getData().get(position)).isCollect())
                {
                    Logger.d("unCollect");
                    mPresenter.cancelCollectArticle(position,(FeedArticleData) mAdapter.getData().get(position));
                }
                else
                {
                    Logger.d("Collect");
                    mPresenter.addCollectArticle(position,(FeedArticleData) mAdapter.getData().get(position));
                }
            }
            break;
        }
    }

    @Override
    public void showCollectArticle(FeedArticleListData articleList)
    {
        mAdapter.setNewData(articleList.getDatas());
    }

    @Override
    public void collectArticleSuccess(int position, FeedArticleData article)
    {
        mAdapter.setData(position,article);

    }

    @Override
    public void cancelCollectArticleSuccess(int position, FeedArticleData article)
    {
        //mAdapter.setData(position,article);
        mAdapter.remove(position);
    }
}
