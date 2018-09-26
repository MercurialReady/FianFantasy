package com.example.mercu.finalfantasy.model.pres;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;

import javax.inject.Inject;

/**
 * Created by Mercu on 2018/8/22.
 */

public class PreferenceHelperImpl implements PreferenceHelper
{
    private static final String MY_SHARED_PREFERENCES = "my_preference";
    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferenceHelperImpl()
    {
        mSharedPreferences = FinalFantasyApp.getInstance().getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginAccount(String account)
    {
        mSharedPreferences.edit().putString(Constants.ACCOUNT,account).apply();
    }

    @Override
    public String getLoginAccount()
    {
        return mSharedPreferences.getString(Constants.ACCOUNT,"");
    }

    @Override
    public void setLoginPassword(String password)
    {
        mSharedPreferences.edit().putString(Constants.PASSWORD,password).apply();
    }

    @Override
    public String getLoginPassword()
    {
        return mSharedPreferences.getString(Constants.PASSWORD,"");
    }

    @Override
    public void setLoginStatus(Boolean status)
    {
        mSharedPreferences.edit().putBoolean(Constants.STATUS,status).apply();
    }

    @Override
    public Boolean getLoginStatus()
    {
        return mSharedPreferences.getBoolean(Constants.STATUS,false);
    }
}
