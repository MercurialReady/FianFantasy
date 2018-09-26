package com.example.mercu.finalfantasy.model.http.api;

import android.support.transition.Slide;

import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.http.BaseGankResponse;
import com.example.mercu.finalfantasy.model.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mercu on 2018/8/21.
 */

public interface Gank
{
    String HOST = "http://gank.io/api/";

    //妹子列表
    @GET("data/%E7%A6%8F%E5%88%A9/{num}/{page}")
    Observable<BaseGankResponse<List<GankBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    //随机
    @GET("random/data/福利/{num}")
    Observable<BaseGankResponse<List<GankBean>>> getRandomGirl(@Path("num") int num);
}
