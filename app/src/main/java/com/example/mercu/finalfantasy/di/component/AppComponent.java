package com.example.mercu.finalfantasy.di.component;

import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.di.module.AbstractAllActivityModule;
import com.example.mercu.finalfantasy.di.module.AbstractAllFragmentModule;
import com.example.mercu.finalfantasy.di.module.AppModule;
import com.example.mercu.finalfantasy.di.module.HttpModule;
import com.example.mercu.finalfantasy.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by qicheng on 2018/8/8.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent
{
    void inject(FinalFantasyApp finalFantasyApp);

    DataManager getDataManager();

    FinalFantasyApp getContext();
}
