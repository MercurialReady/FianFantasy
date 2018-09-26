package com.example.mercu.finalfantasy.ui.wanandroid.fragment;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseFragment;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.wanandroid.NavigationContract;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;
import com.example.mercu.finalfantasy.presenter.wanandroid.NavigationPresenter;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by qicheng on 2018/9/12.
 */

public class NavigationFragment extends BaseMvpFragment<NavigationPresenter>
                    implements NavigationContract.View
{
    @BindView(R.id.vertical_tab)
    VerticalTabLayout mVerticalTabLayout;

    @BindView(R.id.navigation_recycler)
    RecyclerView mRecyclerView;

    private boolean isSelectedFromTab;
    private boolean needScroll;
    private int currentPosition;
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initView()
    {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.getNavigationData();
    }

    @Override
    public void showTag(final List<NavigationListData> title)
    {
        mVerticalTabLayout.setTabAdapter(new TabAdapter()
        {
            @Override
            public int getCount()
            {
                return title.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position)
            {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position)
            {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position)
            {
                return new ITabView.TabTitle.Builder()
                        .setContent(title.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.shallow_green),
                                ContextCompat.getColor(getActivity(), R.color.shallow_grey))
                        .build();
            }

            @Override
            public int getBackground(int position)
            {
                return Color.WHITE;
            }
        });

        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabView tab, int position)
            {
                isSelectedFromTab = true;
                currentPosition = position;
                Log.d("Mercurial","onTabSelected" + "position = " + position);
                int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
                int lastPosition = mLayoutManager.findLastVisibleItemPosition();
                if(position <= firstPosition)
                {
                    //recyclerView会直接滑动过去，无需自己处理
                    mRecyclerView.smoothScrollToPosition(position);
                }
                else if(position <= lastPosition)
                {
                    //当position已经显示在屏幕中
                    //基于recyclerView的回收机制，recyclerView仅仅只有lastPosition - firstPosition个child
                    int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    //这里的top一定是正值，为了向上滚动，根据Android的坐标系，scrollBy参数应设置为正值
                    //即一个view在向上滑动，那么应该被scrollBy参数应该设为正值
                    mRecyclerView.smoothScrollBy(0,top);
                }
                else
                {
                    //需要二次滑动
                    needScroll = true;
                    Log.d("Mercurial","need another scroll");
                    //调用完这一步之后，先执行onScrollStateChanged(RecyclerView recyclerView, int newState)
                    //newState为SCROLL_STATE_SETTLING，再不调用onScrolled(RecyclerView recyclerView, int dx, int dy)N次
                    //最后再调用onScrollStateChanged(RecyclerView recyclerView, int newState)
                    //newState为IDLE，所以逻辑要写在onScrollStateChanged中
                    mRecyclerView.smoothScrollToPosition(position);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position)
            {
                Log.d("Mercurial","onTabReselected" + "position = " + position);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            //这个方法在state变化后回调
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("Mercurial","onScrollStateChanged" + newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && needScroll)
                {
                    needScroll = false;
                    int firstIndex = mLayoutManager.findFirstVisibleItemPosition();
                    Log.d("Mercurial","firstIndex = " + firstIndex);
                    Log.d("Mercurial","currentPosition = " + currentPosition);
                    if(firstIndex <= currentPosition)
                    {
                        int top = mRecyclerView.getChildAt(currentPosition - firstIndex).getTop();
                        mRecyclerView.smoothScrollBy(0,top);
                    }
                }

                if(newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    if(isSelectedFromTab)
                    {
                        isSelectedFromTab = false;
                        return;
                    }
                    int firstIndex = mLayoutManager.findFirstVisibleItemPosition();
                    currentPosition = firstIndex;
                    int top = mRecyclerView.getChildAt(0).getTop();
                    mRecyclerView.smoothScrollBy(0,top);
                    mVerticalTabLayout.setTabSelected(firstIndex,true);
                }
                Log.d("Mercurial","isSelectedFromTab" + isSelectedFromTab);
            }

            //recyclerView滚动结束后回调
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("Mercurial","dy = " + dy);

            }
        });
    }

    @Override
    public void showContent(List<NavigationListData> mData)
    {
        mAdapter.setNewData(mData);
    }


}
