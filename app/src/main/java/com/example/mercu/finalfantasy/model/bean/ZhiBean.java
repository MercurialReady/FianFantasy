package com.example.mercu.finalfantasy.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Mercu on 2018/9/5.
 */

public class ZhiBean implements MultiItemEntity
{
    public static final int TYPE_HOT = 1;
    public static final int TYPE_THEME = 2;
    public static final int TYPE_DAILY = 3;
    public static final int TYPE_SECTION = 4;
    public static final int TYPE_TITLE = 5;

    private String title;
    private DailyListBean.TopStoriesBean daily;
    private HotListBean.RecentBean hot;
    private SectionListBean.DataBean section;
    private ThemeListBean.OthersBean theme;

    private int type;
    private int span;

    public ZhiBean(int type, int span)
    {
        this.type = type;
        this.span = span;
    }

    public String getTitle()
    {
        return title;
    }

    public ZhiBean setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public DailyListBean.TopStoriesBean getDaily()
    {
        return daily;
    }

    public ZhiBean setDaily(DailyListBean.TopStoriesBean daily)
    {
        this.daily = daily;
        return this;
    }

    public HotListBean.RecentBean getHot()
    {
        return hot;
    }

    public ZhiBean setHot(HotListBean.RecentBean hot)
    {
        this.hot = hot;
        return this;
    }

    public SectionListBean.DataBean getSection()
    {
        return section;
    }

    public ZhiBean setSection(SectionListBean.DataBean section)
    {
        this.section = section;
        return this;
    }

    public ThemeListBean.OthersBean getTheme()
    {
        return theme;
    }

    public ZhiBean setTheme(ThemeListBean.OthersBean theme)
    {
        this.theme = theme;
        return this;
    }

    public int getSpan()
    {
        return span;
    }

    public void setSpan(int span)
    {
        this.span = span;
    }

    @Override
    public int getItemType()
    {
        return type;
    }
}
