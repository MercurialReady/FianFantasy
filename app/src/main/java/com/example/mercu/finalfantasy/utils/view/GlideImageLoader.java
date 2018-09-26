package com.example.mercu.finalfantasy.utils.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by qicheng on 2018/9/3.
 */

public class GlideImageLoader extends ImageLoader
{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView)
    {
        Glide.with(context).load(path).into(imageView);
    }
}
