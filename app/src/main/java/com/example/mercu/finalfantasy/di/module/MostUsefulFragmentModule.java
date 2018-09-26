package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.presenter.wanandroid.MostUsefulPresenter;
import com.example.mercu.finalfantasy.ui.wanandroid.adapter.MostUsefulAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/8/31.
 */

@Module
public class MostUsefulFragmentModule
{
    @Provides
    BaseQuickAdapter provideAdapter()
    {
        return new MostUsefulAdapter();
    }
}
