package com.example.mercu.finalfantasy.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Mercu on 2018/8/21.
 */

public class GankBean implements Parcelable
{

    /**
     * _id : 5b63cd4e9d21225e0d3f58c9
     * createdAt : 2018-08-03T11:34:38.672Z
     * desc : 2018-08-03
     * publishedAt : 2018-08-03T00:00:00.0Z
     * source : api
     * type : 福利
     * url : https://ww1.sinaimg.cn/large/0065oQSqgy1ftwcw4f4a5j30sg10j1g9.jpg
     * used : true
     * who : lijinshan
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private int Height;

    //todo:这个height的作用是处理瀑布流快速滑动时item错位问题
    public int getHeight()
    {
        return Height;
    }

    public void setHeight(int height)
    {
        Height = height;
    }

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

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(_id);
        parcel.writeString(createdAt);
        parcel.writeString(desc);
        parcel.writeString(publishedAt);
        parcel.writeString(source);
        parcel.writeString(type);
        parcel.writeString(url);
        parcel.writeByte((byte) (used ? 1 : 0));
        parcel.writeString(who);
        parcel.writeInt(Height);
    }

    protected GankBean(Parcel in)
    {
        _id = in.readString();
        createdAt = in.readString();
        desc = in.readString();
        publishedAt = in.readString();
        source = in.readString();
        type = in.readString();
        url = in.readString();
        used = in.readByte() != 0;
        who = in.readString();
        Height = in.readInt();
    }

    public static final Creator<GankBean> CREATOR = new Creator<GankBean>()
    {
        @Override
        public GankBean createFromParcel(Parcel in)
        {
            return new GankBean(in);
        }

        @Override
        public GankBean[] newArray(int size)
        {
            return new GankBean[size];
        }
    };
}
