package com.example.mercu.finalfantasy.contract.gank;

import com.example.mercu.finalfantasy.base.BasePresenter;
import com.example.mercu.finalfantasy.base.BaseView;
import com.example.mercu.finalfantasy.contract.zhihu.ZhiContract;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qicheng on 2018/9/7.
 */

public interface GankContract
{
    interface View extends BaseView
    {
        void showImage(ArrayList<GankBean> mData);
    }

    interface Presenter extends BasePresenter<GankContract.View>
    {
        void fetchData(int num,int page);
    }
}
