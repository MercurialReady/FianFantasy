package com.example.mercu.finalfantasy.ui.wanandroid.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseActivity;
import com.example.mercu.finalfantasy.base.IToolBar;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.utils.view.Logger;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/29.
 */

public class ArticleDetailActivity extends BaseActivity implements IToolBar
{
    @BindView(R.id.detail_web_view)
    FrameLayout mWebContent;

    private AgentWeb mAgentWeb;
    private Bundle mBundle;
    private String link;

    @Override
    protected int getRootLayoutId()
    {
        return R.layout.activity_article_detail;
    }

    @Override
    protected int getFrameLayoutId()
    {
        return R.id.detail_web_view;
    }

    @Override
    protected int getSuccessViewId()
    {
        return R.layout.fragment_webview;
    }

    @Override
    public void initView()
    {
        mWebContent = findViewById(R.id.detail_web_view);
        mAgentWeb = AgentWeb.with(this)
                            .setAgentWebParent(mWebContent,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                            .useDefaultIndicator()
                            .createAgentWeb()
                            .ready()
                            .go(link.trim());
        Logger.d("show ArticleDetailActivity");

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
//        if (mPresenter.getNoImageState()) {
//            mSettings.setBlockNetworkImage(true);
//        } else {
//            mSettings.setBlockNetworkImage(false);
//        }
//        if (mPresenter.getAutoCacheState()) {
//            mSettings.setAppCacheEnabled(true);
//            mSettings.setDomStorageEnabled(true);
//            mSettings.setDatabaseEnabled(true);
//            if (CommonUtils.isNetworkConnected()) {
//                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//            } else {
//                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//            }
//        } else {
//            mSettings.setAppCacheEnabled(false);
//            mSettings.setDomStorageEnabled(false);
//            mSettings.setDatabaseEnabled(false);
//            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        }

//        mSettings.setJavaScriptEnabled(true);
//        mSettings.setSupportZoom(true);
//        mSettings.setBuiltInZoomControls(true);
//        //不显示缩放按钮
//        mSettings.setDisplayZoomControls(false);
//        //设置自适应屏幕，两者合用
//        //将图片调整到适合WebView的大小
//        mSettings.setUseWideViewPort(true);
//        //缩放至屏幕的大小
//        mSettings.setLoadWithOverviewMode(true);
//        //自适应屏幕
//        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void loadData()
    {
        //注：此处曾经出现过一个bug，就是setState后其实是立即调用initView的
        //所以要先加载好数据
        mBundle = getIntent().getExtras();
        assert mBundle != null;
        link = mBundle.getString(Constants.ARTICLE_LINK);
        setState(LoadingPage.STATE_SUCCESS);
    }

    @Override
    public int getToolBarId()
    {
        return R.layout.second_toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
}
