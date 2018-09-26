package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.ui.gank.adapter.GankAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/9/10.
 */

@Module
public class HomeModule
{
    @Provides
    BaseQuickAdapter provideHomeAdapter()
    {
        return new GankAdapter(new ArrayList<GankBean>());
    }

}
