package com.example.mercu.finalfantasy.di.module;

import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.dao.DbHelper;
import com.example.mercu.finalfantasy.model.dao.DbHelperImpl;
import com.example.mercu.finalfantasy.model.http.HttpHelper;
import com.example.mercu.finalfantasy.model.http.HttpHelperImpl;
import com.example.mercu.finalfantasy.model.pres.PreferenceHelper;
import com.example.mercu.finalfantasy.model.pres.PreferenceHelperImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qicheng on 2018/8/24.
 */

@Module
public class AppModule
{
    private FinalFantasyApp mFinalFantasyApp;

    public AppModule(FinalFantasyApp app)
    {
        mFinalFantasyApp = app;
    }

    @Provides
    FinalFantasyApp provideFinalFantasyApp()
    {
        return mFinalFantasyApp;
    }


    @Provides
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelper)
    {
        return httpHelper;
    }

    @Provides
    DbHelper provideDbHelper(DbHelperImpl dbHelper)
    {
        return dbHelper;
    }

    @Provides
    PreferenceHelper providePrefHelper(PreferenceHelperImpl preferenceHelper)
    {
        return preferenceHelper;
    }

    @Provides
    DataManager provideDataManager(HttpHelper httpHelper,DbHelper dbHelper,PreferenceHelper preferenceHelper)
    {
        return new DataManager(dbHelper,httpHelper,preferenceHelper);
    }
}
