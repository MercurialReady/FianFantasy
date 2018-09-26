package com.example.mercu.finalfantasy.contract.zhihu;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;

import java.util.List;

/**
 * Created by qicheng on 2018/9/5.
 */

public interface ZhiContract
{
    interface View extends BaseView
    {
        void showContent(List<ZhiBean> mData);
    }

    interface Presenter extends BasePresenter<View>
    {
        void fetchData(int hotNum,int sectionNum,int themeNum,int dailyNum);
    }
}
