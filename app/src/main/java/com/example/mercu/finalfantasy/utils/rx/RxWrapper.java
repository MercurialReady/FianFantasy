package com.example.mercu.finalfantasy.utils.rx;

/**
 * Created by qicheng on 2018/9/27.
 */

public class RxWrapper
{
    private int code;
    private Object mObject;

    public RxWrapper(int code, Object clz)
    {
        this.code = code;
        this.mObject = clz;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Object getObject()
    {
        return mObject;
    }

    public void setObject(Object object)
    {
        mObject = object;
    }
}
