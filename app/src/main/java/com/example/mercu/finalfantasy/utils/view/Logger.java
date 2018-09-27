package com.example.mercu.finalfantasy.utils.view;

import android.util.Log;

/**
 * Created by qicheng on 2018/9/17.
 */

public class Logger
{
    public static void d(String tag)
    {
        Log.d("Mercurial"," " + tag);
    }

    public static void d(String tag, int t)
    {
        Log.d("Mercurial"," " + tag + t);
    }

    public static void d(String tag, float t)
    {
        Log.d("Mercurial"," " + tag + t);
    }

    public static void d(String tag, double t)
    {
        Log.d("Mercurial"," " + tag + t);
    }
}
