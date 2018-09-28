package com.example.mercu.finalfantasy.app;

import com.example.mercu.finalfantasy.model.http.api.WanAndroid;

import java.io.File;

/**
 * Created by Mercu on 2018/8/23.
 */

public class Constants
{
    public static final String PATH_DATA = FinalFantasyApp.getInstance()
            .getCacheDir().getAbsolutePath()
            + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";

    public static final int REGISTER_SUCCESS = 1990;
    public static final int LOGIN_SUCCESS = 1991;
    public static final int CANCEL_COLLECT = 1992;
}
