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
import android.view.View;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseActivity;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.ui.gank.adapter.GirlAdapter;

import java.util.List;
import java.util.Map;

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
        setContentView(R.layout.activity_detail_girl);
        mViewPager = findViewById(R.id.girl_pager);

        initView();
    }

    private void initView()
    {
        Bundle bundle = getIntent().getBundleExtra("detail_girl");
        if(bundle != null)
        {
            mData = bundle.getParcelableArrayList("girls");
            currentPosition = bundle.getInt("currentPosition");
        }

        mPagerAdapter = new GirlAdapter(mData,this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentPosition);

        mViewPager.setScaleX(0.5f);
        mViewPager.setScaleY(0.5f);
        getWindow().getDecorView().setBackgroundColor(Color.argb(200,0,0,0));

        setEnterSharedElementCallback(new SharedElementCallback() {

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                //View view = mPagerAdapter.getP
//                sharedElements.clear();
//                sharedElements.put("img", view);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        transitionFinish();
    }

    private void transitionFinish() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("currentPosition",mViewPager.getCurrentItem());
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        ActivityCompat.finishAfterTransition(this);
    }
}
