package com.example.mercu.finalfantasy.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mercu.finalfantasy.R;

/**
 * Created by qicheng on 2018/8/2.
 */

public abstract class LoadingPage extends FrameLayout
{
    private View loadingView;
    private View emptyView;
    private View errorView;
    private View successView;

    private Context mContext;
    private int state;

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_ERROR = 3;
    public static final int STATE_SUCCESS = 4;

    public LoadingPage(@NonNull Context context)
    {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        this(context,attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        showPageViaState();
    }

    private void init()
    {
        state = STATE_UNKNOWN;

        if(loadingView == null)
        {
            loadingView = LayoutInflater.from(mContext).inflate(R.layout.loading,null);
            this.addView(loadingView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if(emptyView == null)
        {
            emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty,null);
            this.addView(emptyView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if(errorView == null)
        {
            errorView = LayoutInflater.from(mContext).inflate(R.layout.error,null);
            errorView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    state = STATE_LOADING;
                    showPageViaState();
                    loadData();
                }
            });
            this.addView(errorView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showPageViaState();
    }

    public void setState(int state)
    {
        this.state = state;
        showPageViaState();
    }

    private void showPageViaState()
    {
        if(loadingView != null)
        {
            loadingView.setVisibility(state == STATE_LOADING ? VISIBLE : INVISIBLE);
        }

        if(emptyView != null)
        {
            emptyView.setVisibility(state == STATE_EMPTY ? VISIBLE : INVISIBLE);
        }

        if(errorView != null)
        {
            errorView.setVisibility(state == STATE_ERROR ? VISIBLE : INVISIBLE);
        }

        if(state == STATE_SUCCESS)
        {
            if(successView == null)
            {
                successView = LayoutInflater.from(mContext).inflate(getLayoutId(),null);
                this.addView(successView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                initView();
            }
            successView.setVisibility(VISIBLE);
        }
        else
        {
            if(successView != null)
            {
                successView.setVisibility(GONE);
            }
        }
    }

    //给ButterKnife使用去bind view
    public View getSuccessView()
    {
        return successView;
    }

    //这个方法只会执行一次，
    protected abstract void initView();

    //这个方法在loadingPage中只会在点击errorView时调用
    //平时的调用是在fragment或者activity的onCreate中调用，也是执行一次
    protected abstract void loadData();

    public abstract int getLayoutId();
}
