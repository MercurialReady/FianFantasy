package com.example.mercu.finalfantasy.model;

import com.example.mercu.finalfantasy.model.bean.BannerData;
import com.example.mercu.finalfantasy.model.bean.CommentBean;
import com.example.mercu.finalfantasy.model.bean.DailyBeforeListBean;
import com.example.mercu.finalfantasy.model.bean.DailyListBean;
import com.example.mercu.finalfantasy.model.bean.DetailExtraBean;
import com.example.mercu.finalfantasy.model.bean.FeedArticleListData;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.HotListBean;
import com.example.mercu.finalfantasy.model.bean.HotMovieBean;
import com.example.mercu.finalfantasy.model.bean.KnowledgeHierarchyData;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.model.bean.MovieDetailBean;
import com.example.mercu.finalfantasy.model.bean.NavigationListData;
import com.example.mercu.finalfantasy.model.bean.ProjectClassifyData;
import com.example.mercu.finalfantasy.model.bean.ProjectListData;
import com.example.mercu.finalfantasy.model.bean.SectionChildListBean;
import com.example.mercu.finalfantasy.model.bean.SectionListBean;
import com.example.mercu.finalfantasy.model.bean.ThemeChildListBean;
import com.example.mercu.finalfantasy.model.bean.ThemeListBean;
import com.example.mercu.finalfantasy.model.bean.TopSearchData;
import com.example.mercu.finalfantasy.model.bean.UsefulSiteData;
import com.example.mercu.finalfantasy.model.bean.ZhihuDetailBean;
import com.example.mercu.finalfantasy.model.dao.DbHelper;
import com.example.mercu.finalfantasy.model.dao.HistoryData;
import com.example.mercu.finalfantasy.model.http.BaseGankResponse;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.model.http.HttpHelper;
import com.example.mercu.finalfantasy.model.pres.PreferenceHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mercu on 2018/8/20.
 */

public class DataManager implements DbHelper,HttpHelper,PreferenceHelper
{
    private DbHelper mDbHelper;
    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(DbHelper dbHelper, HttpHelper httpHelper, PreferenceHelper preferenceHelper)
    {
        mDbHelper = dbHelper;
        mHttpHelper = httpHelper;
        mPreferenceHelper = preferenceHelper;
    }

    @Override
    public List<HistoryData> addHistoryData(String data)
    {
        return mDbHelper.addHistoryData(data);
    }

    @Override
    public void clearAllData()
    {
        mDbHelper.clearAllData();
    }

    @Override
    public List<HistoryData> loadAllData()
    {
        return mDbHelper.loadAllData();
    }

    @Override
    public Observable<HotMovieBean> fetchMovieTop250(int start, int count)
    {
        return mHttpHelper.fetchMovieTop250(start, count);
    }

    @Override
    public Observable<MovieDetailBean> fetchMovieDetail(String id)
    {
        return mHttpHelper.fetchMovieDetail(id);
    }

    @Override
    public Observable<BaseGankResponse<List<GankBean>>> getGirlList(int num, int page)
    {
        return mHttpHelper.getGirlList(num, page);
    }

    @Override
    public Observable<BaseGankResponse<List<GankBean>>> getRandomGirl(int num)
    {
        return mHttpHelper.getRandomGirl(num);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int num)
    {
        return mHttpHelper.getFeedArticleList(num);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int page, String k)
    {
        return mHttpHelper.getSearchList(page, k);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData()
    {
        return mHttpHelper.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites()
    {
        return mHttpHelper.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData()
    {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData()
    {
        return mHttpHelper.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid)
    {
        return mHttpHelper.getKnowledgeHierarchyDetailData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData()
    {
        return mHttpHelper.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData()
    {
        return mHttpHelper.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid)
    {
        return mHttpHelper.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password)
    {
        return mHttpHelper.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword)
    {
        return mHttpHelper.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id)
    {
        return mHttpHelper.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link)
    {
        return mHttpHelper.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page)
    {
        return mHttpHelper.getCollectList(page);
    }

    //这个脑残方法返回null，不过为了api的统一，只好这样写
    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id, int originId)
    {
        return mHttpHelper.cancelCollectPageArticle(id, originId);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id, int originId)
    {
        return mHttpHelper.cancelCollectArticle(id, originId);
    }

    @Override
    public Observable<DailyListBean> getDailyList()
    {
        return mHttpHelper.getDailyList();
    }

    @Override
    public Observable<DailyBeforeListBean> getDailyBeforeList(String date)
    {
        return mHttpHelper.getDailyBeforeList(date);
    }

    @Override
    public Observable<ThemeListBean> getThemeList()
    {
        return mHttpHelper.getThemeList();
    }

    @Override
    public Observable<ThemeChildListBean> getThemeChildList(int id)
    {
        return mHttpHelper.getThemeChildList(id);
    }

    @Override
    public Observable<SectionListBean> getSectionList()
    {
        return mHttpHelper.getSectionList();
    }

    @Override
    public Observable<SectionChildListBean> getSectionChildList(int id)
    {
        return mHttpHelper.getSectionChildList(id);
    }

    @Override
    public Observable<HotListBean> getHotList()
    {
        return mHttpHelper.getHotList();
    }

    @Override
    public Observable<ZhihuDetailBean> getDetailInfo(int id)
    {
        return mHttpHelper.getDetailInfo(id);
    }

    @Override
    public Observable<DetailExtraBean> getDetailExtraInfo(int id)
    {
        return mHttpHelper.getDetailExtraInfo(id);
    }

    @Override
    public Observable<CommentBean> getLongCommentInfo(int id)
    {
        return mHttpHelper.getLongCommentInfo(id);
    }

    @Override
    public Observable<CommentBean> getShortCommentInfo(int id)
    {
        return mHttpHelper.getShortCommentInfo(id);
    }

    @Override
    public void setLoginAccount(String account)
    {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount()
    {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public void setLoginPassword(String password)
    {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginPassword()
    {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(Boolean status)
    {
        mPreferenceHelper.setLoginStatus(status);
    }

    @Override
    public Boolean getLoginStatus()
    {
        return mPreferenceHelper.getLoginStatus();
    }
}
