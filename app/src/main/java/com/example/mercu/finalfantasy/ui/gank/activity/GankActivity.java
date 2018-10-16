package com.example.mercu.finalfantasy.ui.gank.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.FinalFantasyApp;
import com.example.mercu.finalfantasy.model.DataManager;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.http.BaseGankResponse;
import com.example.mercu.finalfantasy.presenter.gank.GankPresenter;
import com.example.mercu.finalfantasy.ui.gank.adapter.GankAdapter;
import com.example.mercu.finalfantasy.ui.gank.adapter.GirlAdapter;
import com.example.mercu.finalfantasy.utils.rx.RxTransformer;
import com.example.mercu.finalfantasy.utils.view.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by qicheng on 2018/10/15.
 */

public class GankActivity extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private Bundle bundle;
    private BaseQuickAdapter mAdapter;
    private ArrayList<GankBean> mData;
    private DataManager mDataManager;
    private int newPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gank);
        initView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Logger.d("onResume");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Logger.d("onRestart");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Logger.d("onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Logger.d("onDestroy");
    }

    protected void initView()
    {
        mRecyclerView = findViewById(R.id.gank_recycler);
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataManager = FinalFantasyApp.getInstance().getAppComponent().getDataManager();
        mDataManager.getGirlList(100,5)
                .compose(RxTransformer.<BaseGankResponse<List<GankBean>>>scheduleHelper())
                .compose(RxTransformer.<List<GankBean>>handleGankReponse())
                .subscribeWith(new Observer<List<GankBean>>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(List<GankBean> value)
                    {
                        mAdapter.setNewData(value);
                        mData = (ArrayList<GankBean>) value;
                        Logger.d("set new value");
                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
        mAdapter = new GankAdapter(mData);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                Log.d("Mercurial","click girl in gank");
                Intent intent = new Intent(GankActivity.this, GirlDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("girls",mData);
                bundle.putInt("currentPosition",position);
                intent.putExtra("detail_girl",bundle);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GankActivity.this,view,view.getTransitionName());

                GankActivity.this.startActivity(intent,options.toBundle());

                return true;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        setExitSharedElementCallback(new SharedElementCallback()
        {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements)
            {
                //super.onMapSharedElements(names, sharedElements);
                if(bundle != null)
                {
                    Logger.d("onMapSharedElements");
                    newPosition = bundle.getInt("currentPosition");
                    names.clear();
                    sharedElements.clear();
                    names.add(mData.get(newPosition).get_id());
                    sharedElements.put(mData.get(newPosition).get_id(),mRecyclerView.getLayoutManager().findViewByPosition(newPosition));
                    bundle = null;
                }
            }
        });


    }

    @Override
    public void onActivityReenter(int resultCode, Intent data)
    {
        super.onActivityReenter(resultCode, data);
        bundle = new Bundle(data.getExtras());
        newPosition = bundle.getInt("currentPosition");

        mRecyclerView.scrollToPosition(newPosition);
        postponeEnterTransition();
        Logger.d("mRecyclerView = " + (mRecyclerView== null));
        mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
                mRecyclerView.requestLayout();
                //共享元素准备好后调用startPostponedEnterTransition来恢复过渡效果
                startPostponedEnterTransition();
                return true;
            }
        });
    }
}
