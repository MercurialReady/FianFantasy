package com.example.mercu.finalfantasy.model.http;

import android.util.Log;

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
import com.example.mercu.finalfantasy.model.http.api.Douban;
import com.example.mercu.finalfantasy.model.http.api.Gank;
import com.example.mercu.finalfantasy.model.http.api.WanAndroid;
import com.example.mercu.finalfantasy.model.http.api.Zhihu;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mercu on 2018/8/22.
 */

public class HttpHelperImpl implements HttpHelper
{
    private Douban mDouban;
    private Gank mGank;
    private WanAndroid mWanAndroid;
    private Zhihu mZhihu;

    @Inject
    public HttpHelperImpl(Douban douban, Gank gank, WanAndroid wanAndroid, Zhihu zhihu)
    {
        mDouban = douban;
        mGank = gank;
        mWanAndroid = wanAndroid;
        mZhihu = zhihu;
    }

    @Override
    public Observable<HotMovieBean> fetchMovieTop250(int start, int count)
    {
        return mDouban.fetchMovieTop250(start, count);
    }

    @Override
    public Observable<MovieDetailBean> fetchMovieDetail(String id)
    {
        return mDouban.fetchMovieDetail(id);
    }

    @Override
    public Observable<BaseGankResponse<List<GankBean>>> getGirlList(int num, int page)
    {
        return mGank.getGirlList(num,page);
    }

    @Override
    public Observable<BaseGankResponse<List<GankBean>>> getRandomGirl(int num)
    {
        return mGank.getRandomGirl(num);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mWanAndroid.getFeedArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k) {
        return mWanAndroid.getSearchList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mWanAndroid.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mWanAndroid.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mWanAndroid.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid) {
        return mWanAndroid.getKnowledgeHierarchyDetailData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mWanAndroid.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mWanAndroid.getProjectClassifyData();
    }

    @Override
    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mWanAndroid.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mWanAndroid.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mWanAndroid.getRegisterData(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mWanAndroid.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mWanAndroid.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mWanAndroid.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id, int originId)
    {
        return mWanAndroid.cancelCollectPageArticle(id, -1);
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id, int originId)
    {
        return mWanAndroid.cancelCollectArticle(id, -1);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mWanAndroid.getBannerData();
    }

    @Override
    public Observable<DailyListBean> getDailyList()
    {
        return mZhihu.getDailyList();
    }

    @Override
    public Observable<DailyBeforeListBean> getDailyBeforeList(String date)
    {
        return mZhihu.getDailyBeforeList(date);
    }

    @Override
    public Observable<ThemeListBean> getThemeList()
    {
        return mZhihu.getThemeList();
    }

    @Override
    public Observable<ThemeChildListBean> getThemeChildList(int id)
    {
        return mZhihu.getThemeChildList(id);
    }

    @Override
    public Observable<SectionListBean> getSectionList()
    {
        return mZhihu.getSectionList();
    }

    @Override
    public Observable<SectionChildListBean> getSectionChildList(int id)
    {
        return mZhihu.getSectionChildList(id);
    }

    @Override
    public Observable<HotListBean> getHotList()
    {
        return mZhihu.getHotList();
    }

    @Override
    public Observable<ZhihuDetailBean> getDetailInfo(int id)
    {
        return mZhihu.getDetailInfo(id);
    }

    @Override
    public Observable<DetailExtraBean> getDetailExtraInfo(int id)
    {
        return mZhihu.getDetailExtraInfo(id);
    }

    @Override
    public Observable<CommentBean> getLongCommentInfo(int id)
    {
        return mZhihu.getLongCommentInfo(id);
    }

    @Override
    public Observable<CommentBean> getShortCommentInfo(int id)
    {
        return mZhihu.getShortCommentInfo(id);
    }
}
