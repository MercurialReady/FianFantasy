package com.example.mercu.finalfantasy.ui.wanandroid.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;
import com.example.mercu.finalfantasy.utils.view.Common;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by qicheng on 2018/9/12.
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationListData,BaseViewHolder>
{
    public NavigationAdapter(List<NavigationListData> data)
    {
        super(R.layout.item_navigation);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationListData item)
    {
        helper.setText(R.id.navigation_tv,item.getName());
        final TagFlowLayout mTagFlowLayout = helper.getView(R.id.tag_flow_layout);
        List<FeedArticleData> mTitles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(mTitles)
          {
              //getView的返回值将会被添加到tagFlowLayout中
              @Override
              public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData)
              {
                  TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_flowlayout
                        ,parent,false);
                  tv.setText(feedArticleData.getTitle());
                  tv.setTextColor(Common.randomColor());
                  mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
                  {
                      @Override
                      public boolean onTagClick(View view, int position, FlowLayout parent)
                      {
                          //todo 跳转到具体界面
                          return true;
                      }
                  });
                  return tv;
              }
          }
        );
    }
}
