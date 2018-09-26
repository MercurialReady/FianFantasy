package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;
import com.example.mercu.finalfantasy.ui.gank.adapter.GankAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/9/7.
 */

@Module
public class GankModule
{
    @Provides
    BaseQuickAdapter provideGankAdapter()
    {
        return new GankAdapter(new ArrayList<GankBean>());
    }
}
