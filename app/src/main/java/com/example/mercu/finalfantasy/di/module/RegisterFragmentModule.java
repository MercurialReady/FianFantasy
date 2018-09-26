package com.example.mercu.finalfantasy.di.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;
import com.example.mercu.finalfantasy.ui.zhihu.adapter.ZhiAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mercu on 2018/9/26.
 */

@Module
public class RegisterFragmentModule
{
    @Provides
    BaseQuickAdapter provideZhiAdapter()
    {
        return new ZhiAdapter(new ArrayList<ZhiBean>());
    }
}
