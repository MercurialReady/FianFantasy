package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;
import com.example.mercu.finalfantasy.ui.wanandroid.adapter.NavigationAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/9/12.
 */

@Module
public class NavigationFragmentModule
{
    @Provides
    BaseQuickAdapter provideNavigationAdapter()
    {
        return new NavigationAdapter(new ArrayList<NavigationListData>());
    }
}
