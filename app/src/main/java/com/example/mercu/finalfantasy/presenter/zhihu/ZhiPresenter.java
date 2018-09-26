package com.example.mercu.finalfantasy.presenter.zhihu;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.zhihu.ZhiContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.DailyListBean;
import com.example.mercu.finalfantasy.model.bean.HotListBean;
import com.example.mercu.finalfantasy.model.bean.SectionListBean;
import com.example.mercu.finalfantasy.model.bean.ThemeListBean;
import com.example.mercu.finalfantasy.model.bean.ZhiBean;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;

/**
 * Created by qicheng on 2018/9/5.
 */

public class ZhiPresenter extends RxPresenter<ZhiContract.View>
                    implements ZhiContract.Presenter
{
    @Inject
    public ZhiPresenter(DataManager dataManager)
    {
        mDataManager = dataManager;
    }

    @Override
    public void fetchData(final int hotNum, final int sectionNum, final int themeNum, final int dailyNum)
    {
//        mDataManager.getHotList().compose(RxTransformer.<HotListBean>scheduleHelper())
//                .map(new Function<HotListBean, List<ZhiBean> >()
//                {
//                    @Override
//                    public List<ZhiBean> apply(HotListBean hotListBean) throws Exception
//                    {
//                        List<ZhiBean> mData = new ArrayList<>();
//                        ZhiBean bean1 = new ZhiBean(ZhiBean.TYPE_TITLE,12);
//                        bean1.setTitle("HOT");
//                        mData.add(bean1);
//                        for(int i = 0; i < hotNum ; i++)
//                        {
//                            mData.add((new ZhiBean(ZhiBean.TYPE_HOT,6).setHot(hotListBean.getRecent().get(i))));
//                        }
//                        return mData;
//                    }
//                })
//                .subscribeWith(new BaseObserver<List<ZhiBean>>(mView,true)
//                {
//                    @Override
//                    public void onNext(List<ZhiBean> value)
//                    {
//                        mView.showContent(value);
//                    }
//                });
        Observable.zip(mDataManager.getDailyList(), mDataManager.getHotList()
                , mDataManager.getSectionList(), mDataManager.getThemeList(), new Function4<DailyListBean, HotListBean, SectionListBean, ThemeListBean, List<ZhiBean>>()
                {
                    @Override
                    public List<ZhiBean> apply(DailyListBean dailyListBean, HotListBean hotListBean, SectionListBean sectionListBean, ThemeListBean themeListBean) throws Exception
                    {
                        List<DailyListBean.TopStoriesBean> daily= dailyListBean.getTop_stories();
                        List<HotListBean.RecentBean> hot = hotListBean.getRecent();
                        List<SectionListBean.DataBean> section = sectionListBean.getData();
                        List<ThemeListBean.OthersBean> theme = themeListBean.getOthers();

                        List<ZhiBean> mData = new ArrayList<>();
                        ZhiBean bean1 = new ZhiBean(ZhiBean.TYPE_TITLE,12);
                        bean1.setTitle("HOT");
                        mData.add(bean1);
                        for(int i = 0; i < hotNum ; i++)
                        {
                            mData.add((new ZhiBean(ZhiBean.TYPE_HOT,6).setHot(hot.get(i))));
                        }

                        ZhiBean bean2 = new ZhiBean(ZhiBean.TYPE_TITLE,12);
                        bean2.setTitle("DAILY");
                        mData.add(bean2);
                        for(int i = 0; i < dailyNum ; i++)
                        {
                            mData.add((new ZhiBean(ZhiBean.TYPE_DAILY,6).setDaily(daily.get(i))));
                        }

                        ZhiBean bean3 = new ZhiBean(ZhiBean.TYPE_TITLE,12);
                        bean3.setTitle("THEME");
                        mData.add(bean3);
                        for(int i = 0; i < themeNum ; i++)
                        {
                            mData.add((new ZhiBean(ZhiBean.TYPE_THEME,6).setTheme(theme.get(i))));
                        }

                        ZhiBean bean4 = new ZhiBean(ZhiBean.TYPE_TITLE,12);
                        bean4.setTitle("SECTION");
                        mData.add(bean4);
                        for(int i = 0; i < sectionNum ; i++)
                        {
                            mData.add((new ZhiBean(ZhiBean.TYPE_SECTION,12).setSection(section.get(i))));
                        }
                        return mData;
                    }
                }).compose(RxTransformer.<List<ZhiBean>>scheduleHelper())
                .subscribeWith(new BaseObserver<List<ZhiBean>>(mView,true)
                {
                    @Override
                    public void onNext(List<ZhiBean> value)
                    {
                        mView.showContent(value);
                    }
                });
    }
}
