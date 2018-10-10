package com.example.mercu.finalfantasy.ui.main.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.ui.gank.fragment.GankFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.TestFragment;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;

/**
 * Created by qicheng on 2018/9/10.
 */

public class TestActivity extends AppCompatActivity
{
    FragmentTransaction transaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        transaction = getSupportFragmentManager().beginTransaction();
//        BaseMvpFragment mostUsefulFragment = MostUsefulFragment.<MostUsefulFragment>getInstance(MostUsefulFragment.class,null);
//        BaseMvpFragment zhiFragment = ZhiFragment.<ZhiFragment>getInstance(ZhiFragment.class,null);
//        BaseMvpFragment gankFragment = GankFragment.<GankFragment>getInstance(GankFragment.class,null);
//        TestFragment testFragment = new TestFragment();
//        FrameLayout frameLayout = findViewById(R.id.fragment_group);
//        frameLayout.removeAllViews();
//        ///transaction.add(R.id.content_frame,mostUsefulFragment);//,MostUsefulFragment.class.getSimpleName());
//        //transaction.add(R.id.content_frame,zhiFragment);//,ZhiFragment.class.getSimpleName());
//        transaction.add(R.id.fragment_group,testFragment);//,GankFragment.class.getSimpleName());
//        //transaction.hide(mostUsefulFragment);
//        //transaction.hide(zhiFragment);
//        transaction.show(testFragment);
//        //transaction.add(MostUsefulFragment.getInstance(MostUsefulFragment.class,null),MostUsefulFragment.class.getSimpleName());
//        //transaction.addToBackStack(RootActivity.class.getName());
//        transaction.commitNowAllowingStateLoss();
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback)
    {
        super.setEnterSharedElementCallback(callback);
    }
}
