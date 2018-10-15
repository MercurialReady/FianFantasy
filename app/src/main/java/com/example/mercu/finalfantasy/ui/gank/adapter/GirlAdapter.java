package com.example.mercu.finalfantasy.ui.gank.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.utils.view.CoolImageView;
import com.example.mercu.finalfantasy.utils.view.Logger;

import java.util.List;

/**
 * Created by qicheng on 2018/9/14.
 */

public class GirlAdapter extends PagerAdapter
{
    private List<GankBean> data;
    private Context mContext;
    View sharedElementView;
    private SparseArray<View> mSharedViews;

    public GirlAdapter(List<GankBean> data,Context context)
    {
        this.data = data;
        mContext = context;
        mSharedViews = new SparseArray<>();
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        sharedElementView = LayoutInflater.from(mContext).inflate(R.layout.item_girl_temp,null);
        final CoolImageView iv = sharedElementView.findViewById(R.id.cool_image);
        Glide.with(mContext).load(data.get(position).getUrl()).asBitmap().into(new SimpleTarget<Bitmap>(FinalFantasyApp.getScreen_width(),FinalFantasyApp.getScreen_height())
        {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
            {
                int width = resource.getWidth();
                int height = resource.getHeight();
                int realHeight = (FinalFantasyApp.getScreen_width()) * height / width;
                iv.getLayoutParams().width = FinalFantasyApp.getScreen_width();
                iv.getLayoutParams().height = FinalFantasyApp.getScreen_height();
                iv.setBitmap(resource);
            }
        });
        container.addView(sharedElementView);
        mSharedViews.append(position,iv);
        return sharedElementView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        Logger.d("viewpager destroy item");
        container.removeView((View)object);
    }

    //销毁屏幕外的view
    @Override
    public int getItemPosition(@NonNull Object object)
    {
        return POSITION_NONE;
    }

    public View getSharedView(int position)
    {
        return mSharedViews.get(position);
    }

}
