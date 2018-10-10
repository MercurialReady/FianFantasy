package com.example.mercu.finalfantasy.ui.gank.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.gank.GirlContract;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.presenter.gank.GirlPresenter;
import com.example.mercu.finalfantasy.ui.gank.adapter.GirlAdapter;
import com.example.mercu.finalfantasy.utils.view.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/14.
 */

public class GirlDetailFragment extends BaseMvpFragment<GirlPresenter>
        implements GirlContract.View
{
    @BindView(R.id.girl_pager)
    ViewPager mPager;

    private List<GankBean> mData;
    private int currentPosition;
    private PagerAdapter mPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            mData = bundle.getParcelableArrayList("girls");
            currentPosition = bundle.getInt("currentPosition");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(),R.style.translucent);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = super.onCreateView(localInflater, container, savedInstanceState);
        view.setBackgroundColor(Color.argb(200,0,0,0));
        return view;
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_detail_girl;
    }

    @Override
    protected void initView()
    {
        mPagerAdapter = new GirlAdapter(mData,getActivity());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(currentPosition);
        //mPager.setBackgroundColor(Color.argb(10,0,0,0));
        Log.d("Mercurial","load girl");
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }


}
