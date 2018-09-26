package com.example.mercu.finalfantasy.di.module;

import com.example.mercu.finalfantasy.di.component.BaseActivityComponent;
import com.example.mercu.finalfantasy.ui.main.activity.RootActivity;

import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by qicheng on 2018/8/8.
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule
{
    @ContributesAndroidInjector(modules = RootActivityModule.class)
    abstract RootActivity contributeRootActivityInjector();

}

