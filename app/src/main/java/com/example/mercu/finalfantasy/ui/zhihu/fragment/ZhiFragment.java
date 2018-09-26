package com.example.mercu.finalfantasy.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.zhihu.ZhiContract;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;
import com.example.mercu.finalfantasy.presenter.zhihu.ZhiPresenter;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/5.
 */

public class ZhiFragment extends BaseMvpFragment<ZhiPresenter>
                    implements ZhiContract.View
{
    @BindView(R.id.zhihu_recycler)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView()
    {
        GridLayoutManager manager = new GridLayoutManager(getActivity(),12);
        manager.setItemPrefetchEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.fetchData(4,4,4,4);
        Log.d("Mercurial","load ZhiFragment data");
    }

    @Override
    public void showContent(final List<ZhiBean> mData)
    {
        mAdapter.setNewData(mData);
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position)
            {
                return mData.get(position).getSpan();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
