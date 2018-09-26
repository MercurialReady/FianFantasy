package com.example.mercu.finalfantasy.ui.wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.model.bean.FeedArticleData;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;

import javax.inject.Inject;

/**
 * Created by qicheng on 2018/8/29.
 */

public class MostUsefulAdapter extends BaseQuickAdapter<FeedArticleData,BaseViewHolder>
{
    public MostUsefulAdapter()
    {
        super(R.layout.item_article_list);
    }

    @Override
    public void convert(BaseViewHolder helper,final FeedArticleData item)
    {
        helper.setText(R.id.author,item.getAuthor());
        helper.setText(R.id.type,item.getChapterName());
        if(item.isCollect())
        {
            helper.setImageResource(R.id.collect, R.drawable.icon_like);
        }
        else
        {
            helper.setImageResource(R.id.collect, R.drawable.icon_like_article_not_selected);
        }
        helper.setText(R.id.time,item.getPublishTime() + "");
        helper.setText(R.id.main_content,item.getTitle());
        //helper.setText(R.id.s)

        helper.addOnClickListener(R.id.collect);
    }
}
