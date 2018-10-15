package com.example.mercu.finalfantasy.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.gank.GankContract;
import com.example.mercu.finalfantasy.model.bean.GankBean;
import com.example.mercu.finalfantasy.model.bean.GirlBean;
import com.example.mercu.finalfantasy.presenter.gank.GankPresenter;
import com.example.mercu.finalfantasy.ui.gank.activity.GirlDetailActivity;
import com.example.mercu.finalfantasy.ui.zhihu.fragment.ZhiFragment;
import com.example.mercu.finalfantasy.utils.rx.BaseObserver;
import com.example.mercu.finalfantasy.utils.rx.RxBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;
import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

/**
 * Created by qicheng on 2018/9/7.
 */

public class GankFragment extends BaseMvpFragment<GankPresenter>
                implements GankContract.View
{
    @BindView(R.id.gank_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.girl_list)
    ViewGroup girlListLayout;

    private StaggeredGridLayoutManager mLayoutManager;

    private Bundle bundle;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView()
    {
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
        mPresenter.fetchData(4,3);
        Log.d("Mercurial","load GankFragment data");
        try
        {
            Log.d("Mercurial","encode" + URLEncoder.encode("福利","UTF-8"));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showImage(final ArrayList<GankBean> mData)
    {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, final View view, final int position)
            {
                Log.d("Mercurial","click girl 2");

                getActivity().setExitSharedElementCallback(new SharedElementCallback()
                {
                    @Override
                    public void onMapSharedElements(final List<String> names, final Map<String, View> sharedElements)
                    {
                        //super.onMapSharedElements(names, sharedElements);
                        //这个callback虽然是ExitSharedElementCallback,实际上exit和reenter都会回调，所以
                        //依据bundle来判断是exit还是reenter
                        Log.d("Mercurial","onMapSharedElements");
                        RxBus.getsInstance().toObservable(11111,Integer.class)
                                .subscribeWith(new Observer<Integer>()
                                {
                                    @Override
                                    public void onSubscribe(Disposable d)
                                    {

                                    }

                                    @Override
                                    public void onNext(Integer value)
                                    {
                                        Log.d("Mercurial","Enter or Return");
                                        names.clear();
                                        sharedElements.clear();
                                        sharedElements.put(mData.get(value).getUrl(),view);
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
                    }
                });

                Intent intent = new Intent(getActivity(), GirlDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("girls",mData);
                bundle.putInt("currentPosition",position);
                intent.putExtra("detail_girl",bundle);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,mData.get(position).getUrl());

                getActivity().startActivity(intent,options.toBundle());
                return true;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mData);
        Log.d("Mercurial","showImage");

        android.transition.Slide slide = new android.transition.Slide();
        slide.setDuration(3000);
        getActivity().getWindow().setSharedElementExitTransition(slide);

        getActivity().getWindow().setSharedElementReenterTransition(slide);




    }

    private void startDetailFragment(int position, ArrayList<GankBean> mData, View view)
    {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls",mData);
        bundle.putInt("currentPosition",position);
        bundle.putBoolean("isCreatedFromViewPager",true);
        BaseMvpFragment girlFragment = BaseMvpFragment.<GirlDetailFragment>getInstance(GirlDetailFragment.class,bundle);
        transaction.setTransition(TRANSIT_FRAGMENT_OPEN);
        transaction.addSharedElement(view,"image")
                    .add(R.id.content_fragment,girlFragment)
                    .addToBackStack(girlFragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();

    }
}
