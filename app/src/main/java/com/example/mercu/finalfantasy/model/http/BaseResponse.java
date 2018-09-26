package com.example.mercu.finalfantasy.model.http;

/**
 * Created by Mercu on 2018/8/21.
 */

public class BaseResponse<T>
{
    public static int SUCCESS = 0;

    private int errorCode;

    private String message;

    private T data;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
