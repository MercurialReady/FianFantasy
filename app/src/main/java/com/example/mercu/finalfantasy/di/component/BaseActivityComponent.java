package com.example.mercu.finalfantasy.di.component;

import com.example.mercu.finalfantasy.base.BaseMvpActivity;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by qicheng on 2018/8/8.
 */

@Subcomponent(modules = AndroidInjectionModule.class)
public interface BaseActivityComponent extends AndroidInjector<BaseMvpActivity>
{
    @Subcomponent.Builder
    abstract class Buider extends AndroidInjector.Builder<BaseMvpActivity>
    {

    }
}
