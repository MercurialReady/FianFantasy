package com.example.mercu.finalfantasy.model.http;

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

import java.util.List;
import io.reactivex.Observable;

/**
 * Created by Mercu on 2018/8/22.
 */

public interface HttpHelper
{
    Observable<HotMovieBean> fetchMovieTop250(int start,int count);

    Observable<MovieDetailBean> fetchMovieDetail(String id);

    Observable<BaseGankResponse<List<GankBean>>> getGirlList(int num,int page);

    Observable<BaseGankResponse<List<GankBean>>> getRandomGirl(int num);

    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int num);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> getSearchList(int page,String k);

    io.reactivex.Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    io.reactivex.Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    io.reactivex.Observable<BaseResponse<List<BannerData>>> getBannerData();

    io.reactivex.Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page,int cid);

    io.reactivex.Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    io.reactivex.Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    io.reactivex.Observable<BaseResponse<ProjectListData>> getProjectListData(int page,int cid);

    io.reactivex.Observable<BaseResponse<LoginData>> getLoginData(String username,String password);

    io.reactivex.Observable<BaseResponse<LoginData>> getRegisterData(String username,String password,String repassword);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String  title,String author,String link);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> getCollectList(int page);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id,int originId);

    io.reactivex.Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id,int originId);



    Observable<DailyListBean> getDailyList();

    Observable<DailyBeforeListBean> getDailyBeforeList(String date);

    Observable<ThemeListBean> getThemeList();

    Observable<ThemeChildListBean> getThemeChildList(int id);

    Observable<SectionListBean> getSectionList();

    Observable<SectionChildListBean> getSectionChildList(int id);

    Observable<HotListBean> getHotList();

    Observable<ZhihuDetailBean> getDetailInfo(int id);

    Observable<DetailExtraBean> getDetailExtraInfo(int id);

    Observable<CommentBean> getLongCommentInfo(int id);

    Observable<CommentBean> getShortCommentInfo(int id);
}
