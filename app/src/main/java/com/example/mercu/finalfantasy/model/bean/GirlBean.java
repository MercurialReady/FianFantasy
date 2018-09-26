package com.example.mercu.finalfantasy.model.bean;

import java.util.List;

/**
 * Created by qicheng on 2018/9/7.
 */

public class GirlBean
{
    /**
     * _id : 56cc6d23421aa95caa707b39
     * createdAt : 2015-07-01T02:31:57.773Z
     * desc : 7.1
     * publishedAt : 2015-07-01T03:59:29.34Z
     * type : 福利
     * url : http://ww1.sinaimg.cn/large/7a8aed7bgw1etn2gzjoegj20gz0p9400.jpg
     * used : true
     * who : 张涵宇
     * source : chrome
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private String source;
//    private int Height;
//
//    //todo:这个height的作用是处理瀑布流快速滑动时item错位问题
//    public int getHeight()
//    {
//        return Height;
//    }
//
//    public void setHeight(int height)
//    {
//        Height = height;
//    }

    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id)
    {
        this._id = _id;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getPublishedAt()
    {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public boolean isUsed()
    {
        return used;
    }

    public void setUsed(boolean used)
    {
        this.used = used;
    }

    public String getWho()
    {
        return who;
    }

    public void setWho(String who)
    {
        this.who = who;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }
}
