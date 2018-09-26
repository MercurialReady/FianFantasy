package com.example.mercu.finalfantasy.base;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qicheng on 2018/8/8.
 */

public class ActivityCollector
{
    private volatile static ActivityCollector sInstance;
    private Set<Activity> mActivities;

    private ActivityCollector()
    {

    };

    public static ActivityCollector getActivityCollector()
    {
        if (sInstance == null)
        {
            synchronized (ActivityCollector.class)
            {
                if (sInstance == null)
                {
                    sInstance = new ActivityCollector();
                }
            }
        }
        return sInstance;
    }

    public void addActivity(Activity activity)
    {
        if(mActivities == null)
        {
            mActivities = new HashSet<>();
        }
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        if(mActivities != null)
        {
            mActivities.remove(activity);
        }
    }

    public void exitApp()
    {
        if(mActivities != null)
        {
            synchronized(ActivityCollector.class)
            {
                for (Activity activity : mActivities)
                {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
