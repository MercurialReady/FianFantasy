package com.example.mercu.finalfantasy.model.http.api;

import com.example.mercu.finalfantasy.model.bean.HotMovieBean;
import com.example.mercu.finalfantasy.model.bean.MovieDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mercu on 2018/8/21.
 */

public interface Douban
{
    String HOST = "https://api.douban.com/";

    @GET("v2/movie/top250")
    Observable<HotMovieBean> fetchMovieTop250(@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/subject/{id}")
    Observable<MovieDetailBean> fetchMovieDetail(@Path("id") String id);
}
