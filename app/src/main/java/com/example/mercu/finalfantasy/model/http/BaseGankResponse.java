package com.example.mercu.finalfantasy.model.http;

/**
 * Created by qicheng on 2018/9/7.
 */

public class BaseGankResponse<T>
{
    private boolean error;

    private T results;

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public T getData()
    {
        return results;
    }

    public void setData(T data)
    {
        this.results = results;
    }
}
