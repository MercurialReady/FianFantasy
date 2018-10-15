package com.example.mercu.finalfantasy.ui.gank.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseActivity;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.ui.gank.adapter.GirlAdapter;
import com.example.mercu.finalfantasy.utils.rx.RxBus;
import com.example.mercu.finalfantasy.utils.view.Logger;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mercu on 2018/10/8.
 */

public class GirlDetailActivity extends AppCompatActivity
{
    ViewPager mViewPager;
    PagerAdapter mPagerAdapter;
    private List<GankBean> mData;
    private int currentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_detail_girl);
        initView();
    }

    private void initView()
    {
        mViewPager = findViewById(R.id.girl_pager);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        Bundle bundle = getIntent().getBundleExtra("detail_girl");
        if(bundle != null)
        {
            mData = bundle.getParcelableArrayList("girls");
            currentPosition = bundle.getInt("currentPosition");
        }

        mPagerAdapter = new GirlAdapter(mData,this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentPosition);

        setEnterSharedElementCallback(new SharedElementCallback()
        {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements)
            {
                names.clear();
                sharedElements.clear();
                int position = mViewPager.getCurrentItem();
                names.add(mData.get(position).get_id());
                sharedElements.put(mData.get(position).get_id(), ((GirlAdapter)mPagerAdapter).getSharedView(position));
            }
        });

        RxBus.getsInstance().toObservable(12345,String.class)
                .subscribeWith(new Observer<String>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(String value)
                    {
                        transitionFinish();
                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });

        RxBus.getsInstance().toObservable(123456,Integer.class)
                .subscribeWith(new Observer<Integer>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(Integer value)
                    {
                        getWindow().getDecorView().setBackgroundColor(Color.argb(value,0,0,0));
                        Logger.d("value" + value);
                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
        //getWindow().setSharedElementEnterTransition(new Fade());
        //getWindow().setSharedElementExitTransition(new Fade());

//        Slide slideReturn =new Slide();
//        slideReturn.setDuration(2000);
//        slideReturn.setSlideEdge(android.view.Gravity.RIGHT);
//        slideReturn.setMode(Visibility.MODE_OUT);
//
//        getWindow().setReturnTransition(slideReturn);

    }

    @Override
    public void onBackPressed()
    {
        transitionFinish();
    }

    private void transitionFinish() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("currentPosition",mViewPager.getCurrentItem());
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finishAfterTransition();
    }
}
