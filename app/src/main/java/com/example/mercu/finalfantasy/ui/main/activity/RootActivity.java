package com.example.mercu.finalfantasy.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpActivity;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.RootContract;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.presenter.main.RootPresenter;
import com.example.mercu.finalfantasy.ui.gank.fragment.GankFragment;
import com.example.mercu.finalfantasy.ui.main.adapter.HomeFragmentAdapter;
import com.example.mercu.finalfantasy.ui.main.fragment.HomeFragment;
import com.example.mercu.finalfantasy.ui.main.fragment.LoginFragment;
import com.example.mercu.finalfantasy.ui.wanandroid.fragment.MostUsefulFragment;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;
import com.example.mercu.finalfantasy.utils.view.BottomNavigationViewHelper;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qicheng on 2018/8/29.
 */

public class RootActivity extends BaseMvpActivity<RootPresenter>
                    implements RootContract.View
{
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    private TextView loginView;

    @Override
    protected int getRootLayoutId()
    {
        return R.layout.activity_main_page;
    }

    @Override
    protected int getFrameLayoutId()
    {
        return R.id.content_fragment;
    }

    @Override
    protected int getSuccessViewId()
    {
        return R.layout.fragment_home_wanandroid;
    }

    @Override
    public void initView()
    {
        Log.d("Mercurial","mNavigationView = null is " + (mNavigationView == null));
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.main_drawer);
        mNavigationView.getMenu().findItem(R.id.login).setVisible(false);

        initNavigationView();
    }

    private void initNavigationView()
    {
        loginView = mNavigationView.getHeaderView(0).findViewById(R.id.header_login);
        if(mPresenter.needAutoLogin())
        {
            mPresenter.doLogin();
        }
        else
        {
            loginView.setText("登录");
            loginView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //开启登录界面
                }
            });
        }

        mNavigationView.getMenu().findItem(R.id.collect).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                Log.d("Mercurial","onMenuItemClick");
                if(mPresenter.getLoginStatus())
                {
                    startCollectFragment();
                }
                else
                {
                    startLoginFragment();
                }
                return true;
            }
        });
    }

    private void startLoginFragment()
    {
        Log.d("Mercurial","startLoginFragment");
        mDrawerLayout.closeDrawers();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCreatedFromViewPager",true);
        BaseMvpFragment loginFragment = BaseMvpFragment.<LoginFragment>getInstance(LoginFragment.class,bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_fragment,loginFragment);
        transaction.hide(getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName()));
        transaction.show(loginFragment);
        transaction.commitAllowingStateLoss();
    }

    private void startCollectFragment()
    {

    }

    @Override
    public void showLoginView(LoginData data)
    {
        loginView.setText(mPresenter.getLoginAccount());
        mNavigationView.getMenu().findItem(R.id.login).setVisible(true);
    }

    private void doShowHideFragment(String tag)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName()));
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentByTag(ZhiFragment.class.getSimpleName()));
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentByTag(GankFragment.class.getSimpleName()));
        fragmentTransaction.show(getSupportFragmentManager().findFragmentByTag(tag));
        //fragmentTransaction.addToBackStack(getSupportFragmentManager().findFragmentByTag(tag).getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }



    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        ButterKnife.bind(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCreatedFromViewPager",true);
        BaseMvpFragment homeFragment = HomeFragment.<HomeFragment>getInstance(HomeFragment.class,bundle);
        BaseMvpFragment zhiFragment = ZhiFragment.<ZhiFragment>getInstance(ZhiFragment.class,bundle);
        BaseMvpFragment gankFragment = GankFragment.<GankFragment>getInstance(GankFragment.class,bundle);
        FrameLayout frameLayout = findViewById(R.id.content_fragment);
        frameLayout.removeAllViews();
        transaction.add(R.id.content_fragment,homeFragment,HomeFragment.class.getSimpleName());
        transaction.add(R.id.content_fragment,zhiFragment,ZhiFragment.class.getSimpleName());
        transaction.add(R.id.content_fragment,gankFragment,GankFragment.class.getSimpleName());
        transaction.hide(gankFragment);
        transaction.hide(zhiFragment);
        transaction.show(homeFragment);
        transaction.commitAllowingStateLoss();

        //doShowHideFragment(MostUsefulFragment.class.getSimpleName());
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.item1:
                    {
                        doShowHideFragment(HomeFragment.class.getSimpleName());
                    }
                    break;

                    case R.id.item2:
                    {
                        doShowHideFragment(ZhiFragment.class.getSimpleName());
                    }
                    break;

                    case R.id.item4:
                    {
                        doShowHideFragment(GankFragment.class.getSimpleName());
                    }
                    break;

                    default:break;
                }
                return true;
            }
        });


    }

    @Override
    public void showError()
    {

    }

    @Override
    public void showEmpty()
    {

    }

    @Override
    public void showNormal()
    {

    }

    @Override
    public void showLoading()
    {

    }

    @Override
    public int getToolBarId()
    {
        return R.layout.second_toolbar;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
