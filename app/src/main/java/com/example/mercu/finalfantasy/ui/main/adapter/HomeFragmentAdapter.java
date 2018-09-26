package com.example.mercu.finalfantasy.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

/**
 * Created by qicheng on 2018/8/29.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public HomeFragmentAdapter(FragmentManager fm,List<Fragment> mFragments,List<String> mTitles)
    {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTitles.get(position);
    }
}
