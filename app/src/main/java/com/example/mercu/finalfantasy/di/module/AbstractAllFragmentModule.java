package com.example.mercu.finalfantasy.di.module;

import com.example.mercu.finalfantasy.base.BaseFragment;
import com.example.mercu.finalfantasy.di.component.BaseFragmentComponent;
import com.example.mercu.finalfantasy.ui.gank.fragment.GankFragment;
import com.example.mercu.finalfantasy.ui.gank.fragment.GirlDetailFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.CollectFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.HomeFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.LoginFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.RegisterFragment;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.NavigationFragment;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by qicheng on 2018/8/25.
 */

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule
{
    @ContributesAndroidInjector(modules = MostUsefulFragmentModule.class)
    abstract MostUsefulFragment contributeUseMostUsefulFragmentInjector();

    @ContributesAndroidInjector(modules = ZhiFragmentModule.class)
    abstract ZhiFragment contributeZhiFragmentInjector();

    @ContributesAndroidInjector(modules = GankModule.class)
    abstract GankFragment contributeGankFragmentInjector();

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment contributeHomeFragmentInjector();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributeNavigationFragmentInjector();

    @ContributesAndroidInjector(modules = GirlFragmentModule.class)
    abstract GirlDetailFragment contributeGirlDetailFragmentInjector();

    @ContributesAndroidInjector(modules = LoginFragmentModule.class)
    abstract LoginFragment contributeLoginFragmentInjector();

    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterFragment contributeRegisterFragmentInjector();

    @ContributesAndroidInjector(modules = CollectFragmentModule.class)
    abstract CollectFragment contributeCollectFragmentInjector();
}
