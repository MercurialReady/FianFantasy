package com.example.mercu.finalfantasy.presenter.gank;

import android.util.Log;

import com.example.mercu.finalfantasy.base.RxPresenter;
import com.example.mercu.finalfantasy.contract.gank.GankContract;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;
import com.example.mercu.finalfantasy.model.http.BaseGankResponse;
import com.example.mercu.finalfantasy.model.http.BaseResponse;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by qicheng on 2018/9/7.
 */

public class GankPresenter extends RxPresenter<GankContract.View>
                    implements GankContract.Presenter
{
    @Inject
    public GankPresenter(DataManager dataManagerFromInject)
    {
        mDataManager = dataManagerFromInject;
    }

    @Override
    public void fetchData(int num, int page)
    {
        mDataManager.getGirlList(100,5)
                    .compose(RxTransformer.<BaseGankResponse<List<GankBean>>>scheduleHelper())
                    .compose(RxTransformer.<List<GankBean>>handleGankReponse())
                    .subscribeWith(new BaseObserver<List<GankBean>>(mView,true)
                    {
                        @Override
                        public void onNext(List<GankBean> value)
                        {
                            mView.showImage((ArrayList<GankBean>)value);
                        }
                    });

    }
}
