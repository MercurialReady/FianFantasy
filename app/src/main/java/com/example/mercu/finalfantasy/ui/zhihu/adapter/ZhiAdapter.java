package com.example.mercu.finalfantasy.ui.zhihu.adapter;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;

import java.util.List;

/**
 * Created by qicheng on 2018/9/6.
 */

public class ZhiAdapter extends BaseMultiItemQuickAdapter<ZhiBean,BaseViewHolder>
{
    int width;
    int height;
    int realHeight;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ZhiAdapter(List<ZhiBean> data)
    {
        super(data);
        addItemType(ZhiBean.TYPE_TITLE, R.layout.item_title);
        addItemType(ZhiBean.TYPE_DAILY,R.layout.item_daily);
        addItemType(ZhiBean.TYPE_HOT,R.layout.item_hot);
        addItemType(ZhiBean.TYPE_THEME,R.layout.item_theme);
        addItemType(ZhiBean.TYPE_SECTION,R.layout.item_section);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ZhiBean item)
    {
        switch (item.getItemType())
        {
            case ZhiBean.TYPE_TITLE:
            {
                helper.setText(R.id.zhi_title, item.getTitle());
            }
            break;

            case ZhiBean.TYPE_DAILY:
            {
                Log.d("Mercurial","realHeight = " + helper.getView(R.id.daily_iv).getLayoutParams().height);
                width = FinalFantasyApp.getScreen_width()/2;
                height = FinalFantasyApp.getScreen_width()/2;
                Log.d("Mercurial","width = " + width);
                Log.d("Mercurial","height = " + height);
                realHeight = (FinalFantasyApp.getScreen_width()/2) * height / width;
                //item.setHeight(realHeight);
                Log.d("Mercurial","realHeight = " + realHeight);
                helper.getView(R.id.daily_iv).getLayoutParams().height = realHeight;
                helper.setText(R.id.daily_tv,item.getDaily().getTitle());
                Glide.with(mContext).load(item.getDaily().getImage())
                        .into((ImageView) helper.getView(R.id.daily_iv));
                break;
            }

            case ZhiBean.TYPE_SECTION:
            {
                Log.d("Mercurial","realHeight = " + helper.getView(R.id.section_iv).getLayoutParams().height);
                width = FinalFantasyApp.getScreen_width()/2;
                height = FinalFantasyApp.getScreen_width()/2;
                Log.d("Mercurial","width = " + width);
                Log.d("Mercurial","height = " + height);
                realHeight = (FinalFantasyApp.getScreen_width()/2) * height / width;
                //item.setHeight(realHeight);
                Log.d("Mercurial","realHeight = " + realHeight);
                helper.getView(R.id.section_iv).getLayoutParams().height = realHeight;
                helper.setText(R.id.section_tv,item.getSection().getName());
                Glide.with(mContext).load(item.getSection().getThumbnail())
                        .into((ImageView) helper.getView(R.id.section_iv));
                break;
            }

            case ZhiBean.TYPE_THEME:
            {
                Log.d("Mercurial","realHeight = " + helper.getView(R.id.theme_iv).getLayoutParams().height);
                width = FinalFantasyApp.getScreen_width()/2;
                height = FinalFantasyApp.getScreen_width()/2;
                Log.d("Mercurial","width = " + width);
                Log.d("Mercurial","height = " + height);
                realHeight = (FinalFantasyApp.getScreen_width()/2) * height / width;
                //item.setHeight(realHeight);
                Log.d("Mercurial","realHeight = " + realHeight);
                helper.getView(R.id.theme_iv).getLayoutParams().height = realHeight;
                helper.setText(R.id.theme_tv,item.getTheme().getName());
                Glide.with(mContext).load(item.getTheme().getThumbnail())
                        .into((ImageView) helper.getView(R.id.theme_iv));
                break;
            }

            case ZhiBean.TYPE_HOT:
            {
                //(ImageView) helper.getView(R.id.hot_iv)
                helper.setText(R.id.hot_tv,item.getHot().getTitle());
//                Log.d("Mercurial","realHeight = " + helper.getView(R.id.hot_iv).getLayoutParams().height);
//                width = FinalFantasyApp.getScreen_width()/2;
//                height = FinalFantasyApp.getScreen_width()/2;
//                Log.d("Mercurial","width = " + width);
//                Log.d("Mercurial","height = " + height);
//                realHeight = (FinalFantasyApp.getScreen_width()/2) * height / width;
//                //item.setHeight(realHeight);
//                Log.d("Mercurial","realHeight = " + realHeight);
//                helper.getView(R.id.hot_iv).getLayoutParams().height = realHeight;
                Glide.with(mContext).load(item.getHot().getThumbnail()).asBitmap()
                        .into(new SimpleTarget<Bitmap>(FinalFantasyApp.getScreen_width()/2,
                                FinalFantasyApp.getScreen_width()/2)
                        {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
                            {
                        width = resource.getWidth();
                        height = resource.getHeight();
                        Log.d("Mercurial","width = " + width);
                        Log.d("Mercurial","height = " + height);
                        realHeight = (FinalFantasyApp.getScreen_width()/2) * height / width;
//                        //item.setHeight(realHeight);
                        Log.d("Mercurial","realHeight = " + realHeight);
//                        helper.getView(R.id.hot_iv).getLayoutParams().height = realHeight;
                                ((ImageView)helper.getView(R.id.hot_iv)).setImageBitmap(resource);
                            }
                        });

                break;
            }
        }
    }
}
