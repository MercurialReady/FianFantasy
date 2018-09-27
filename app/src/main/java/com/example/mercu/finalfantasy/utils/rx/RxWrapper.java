package com.example.mercu.finalfantasy.utils.rx;

/**
 * Created by qicheng on 2018/9/27.
 */

public class RxWrapper
{
    private int code;
    private Class clz;

    public RxWrapper(int code, Class clz)
    {
        this.code = code;
        this.clz = clz;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Class getClz()
    {
        return clz;
    }

    public void setClz(Class clz)
    {
        this.clz = clz;
    }
}
