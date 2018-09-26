package com.example.mercu.finalfantasy.di.component;

import com.example.mercu.finalfantasy.base.BaseMvpFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by qicheng on 2018/8/8.
 */

@Subcomponent(modules = AndroidInjectionModule.class)
public interface BaseFragmentComponent extends AndroidInjector<BaseMvpFragment>
{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseMvpFragment>
    {

    }
}
