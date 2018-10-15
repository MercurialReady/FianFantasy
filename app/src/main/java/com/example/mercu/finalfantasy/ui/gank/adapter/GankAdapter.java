package com.example.mercu.finalfantasy.ui.gank.adapter;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;

import java.util.List;

/**
 * Created by qicheng on 2018/9/7.
 */

public class GankAdapter extends BaseQuickAdapter<GankBean,BaseViewHolder>
{
    int width;
    int height;
    int realHeight;

    public GankAdapter(List<GankBean> data)
    {
        super(R.layout.item_gank,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GankBean item)
    {
        if(item.getHeight() > 0)
        {
            //Log.d("Mercurial","item.getHeight() = " + item.getHeight());
            helper.getView(R.id.gank_iv).getLayoutParams().height = item.getHeight();
        }
       // Object tag = helper.getView(R.id.gank_iv).getTag(R.id.gank_iv);
       // Log.d("Mercurial","tag = " + tag);
//        if(tag != null && tag != item.getUrl())
//        {
//            //Glide.clear(helper.getView(R.id.gank_iv));
//            Log.d("Mercurial","clear image");
//        }
//        else
//        {
            Glide.with(mContext).load(item.getUrl()).asBitmap().placeholder(R.mipmap.ic_launcher_round)
                    .into(new SimpleTarget<Bitmap>(FinalFantasyApp.getScreen_width() / 2,
                            FinalFantasyApp.getScreen_width() / 2)
                    {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
                        {
                            //如果是第一次加载，需要记录每个item的高度，防止下次因复用导致的item错位
                            if (item.getHeight() <= 0)
                            {
                                width = resource.getWidth();
                                height = resource.getHeight();
                                //Log.d("Mercurial","width = " + width);
                                //Log.d("Mercurial","height = " + height);
                                realHeight = (FinalFantasyApp.getScreen_width() / 2) * height / width;
                                item.setHeight(realHeight);
                                helper.getView(R.id.gank_iv).getLayoutParams().height = item.getHeight();
                            }
                            ((AppCompatImageView) helper.getView(R.id.gank_iv)).setImageBitmap(resource);

                        }
                    });
            //helper.getView(R.id.gank_iv).setTag(R.id.gank_iv, item.getUrl());
            ViewCompat.setTransitionName(helper.getView(R.id.gank_iv),item.get_id());
            helper.addOnClickListener(R.id.gank_iv);
        //}
    }
}
