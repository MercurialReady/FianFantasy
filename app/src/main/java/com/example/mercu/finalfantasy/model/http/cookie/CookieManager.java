package com.example.mercu.finalfantasy.model.http.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Mercu on 2018/8/22.
 */

public class CookieManager implements CookieJar
{
    private Context mContext;

    private static PersistentCookieStore mPersistentCookieStore;

    public CookieManager(Context context)
    {
        mContext = context;
        mPersistentCookieStore = new PersistentCookieStore(mContext);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        mPersistentCookieStore.add(url,cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        return mPersistentCookieStore.get(url);
    }

    public static void clearAllCookies()
    {
        mPersistentCookieStore.removeAll();
    }
}
