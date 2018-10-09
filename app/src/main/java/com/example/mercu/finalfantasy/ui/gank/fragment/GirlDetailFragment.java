package com.example.mercu.finalfantasy.ui.gank.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.gank.GirlContract;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.presenter.gank.GirlPresenter;
import com.example.mercu.finalfantasy.ui.gank.adapter.GirlAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/14.
 */

public class GirlDetailFragment extends BaseMvpFragment<GirlPresenter>
        implements GirlContract.View,ViewPager.OnPageChangeListener
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        if(position == 2)
        {
            mPager.setBackgroundColor(Color.argb(100,0,0,0));
        }
        getActivity().getWindow().getDecorView().setBackgroundColor(Color.argb(200,0,0,0));
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}
