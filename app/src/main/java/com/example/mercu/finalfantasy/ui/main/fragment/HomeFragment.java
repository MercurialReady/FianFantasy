package com.example.mercu.finalfantasy.ui.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.HomeContract;
import com.example.mercu.finalfantasy.presenter.main.HomePresenter;
import com.example.mercu.finalfantasy.ui.gank.fragment.GankFragment;
import com.example.mercu.finalfantasy.ui.main.adapter.HomeFragmentAdapter;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.NavigationFragment;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/8.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter>
                    implements HomeContract.View
{
    private List<Fragment> mFragments;
    private List<String> mTitles;

    @BindView(R.id.tab)
    TabLayout mTabLayout;

    @BindView(R.id.android_page)
    ViewPager mPager;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_home_wanandroid;
    }

    @Override
    protected void initView()
    {
        initData();
        mPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(),mFragments,mTitles));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setupWithViewPager(mPager);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }

    private void initData()
    {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();

        mFragments.add(MostUsefulFragment.<MostUsefulFragment>getInstance(MostUsefulFragment.class,null));
        mFragments.add(ZhiFragment.<ZhiFragment>getInstance(ZhiFragment.class,null));
        mFragments.add(GankFragment.<GankFragment>getInstance(GankFragment.class,null));
        mFragments.add(NavigationFragment.<NavigationFragment>getInstance(NavigationFragment.class,null));

        mTitles.add("1");
        mTitles.add("2");
        mTitles.add("3");
        mTitles.add("4");
    }
}
