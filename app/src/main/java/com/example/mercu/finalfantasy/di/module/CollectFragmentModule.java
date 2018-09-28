package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.ui.wanandroid.adapter.MostUsefulAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/9/28.
 */

@Module
public class CollectFragmentModule
{
    @Provides
    BaseQuickAdapter provideAdapter()
    {
        return new MostUsefulAdapter();
    }
}
