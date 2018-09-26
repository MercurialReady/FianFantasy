package com.example.mercu.finalfantasy.ui.gank.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.gank.GankContract;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;
import com.example.mercu.finalfantasy.presenter.gank.GankPresenter;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/7.
 */

public class GankFragment extends BaseMvpFragment<GankPresenter>
                implements GankContract.View
{
    @BindView(R.id.gank_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.girl_list)
    ViewGroup girlListLayout;

    @BindView(R.id.girl_detail_container)
    FrameLayout mFrameLayout;

    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView()
    {
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.fetchData(4,3);
        Log.d("Mercurial","load GankFragment data");
        try
        {
            Log.d("Mercurial","encode" + URLEncoder.encode("福利","UTF-8"));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showImage(final ArrayList<GankBean> mData)
    {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                Log.d("Mercurial","click girl");
                mFrameLayout.setVisibility(View.VISIBLE);
                startDetailFragment(position,mData);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                Log.d("Mercurial","click girl");
                mFrameLayout.setVisibility(View.VISIBLE);
                startDetailFragment(position,mData);
                return true;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mData);
        Log.d("Mercurial","showImage");
    }

    private void startDetailFragment(int position,ArrayList<GankBean> mData)
    {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls",mData);
        bundle.putInt("currentPosition",position);
        bundle.putBoolean("isCreatedFromViewPager",true);
        BaseMvpFragment girlFragment = BaseMvpFragment.<GirlDetailFragment>getInstance(GirlDetailFragment.class,bundle);
        transaction.add(R.id.girl_detail_container,girlFragment);
        transaction.addToBackStack(girlFragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }
}
