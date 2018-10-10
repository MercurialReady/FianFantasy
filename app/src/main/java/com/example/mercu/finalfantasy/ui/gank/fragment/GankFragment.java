package com.example.mercu.finalfantasy.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                Log.d("Mercurial","click girl");
                //mFrameLayout.setVisibility(View.VISIBLE);
                startDetailFragment(position,mData);
//                Intent intent = new Intent(getActivity(), GirlDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("girls",mData);
//                bundle.putInt("currentPosition",position);
//                intent.putExtra("detail_girl",bundle);
//                getActivity().startActivity(intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                Log.d("Mercurial","click girl");
                //mFrameLayout.setVisibility(View.VISIBLE);
                startDetailFragment(position,mData);
//                Intent intent = new Intent(getActivity(), GirlDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("girls",mData);
//                bundle.putInt("currentPosition",position);
//                intent.putExtra("detail_girl",bundle);
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,mData.get(position).getUrl());
//
//                getActivity().startActivity(intent,options.toBundle());
                return true;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mData);
        Log.d("Mercurial","showImage");

        setExitSharedElementCallback(new SharedElementCallback()
        {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements)
            {
                //super.onMapSharedElements(names, sharedElements);
                //这个callback虽然是ExitSharedElementCallback,实际上exit和reenter都会回调，所以
                //依据bundle来判断是exit还是reenter
                if(bundle != null)
                {
//                    bundle.
//                    names.clear();
//                    sharedElements.clear();
//                    names.add()
                }
            }
        });
    }

    private void startDetailFragment(int position,ArrayList<GankBean> mData)
    {
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("girls",mData);
//        bundle.putInt("currentPosition",position);
//        bundle.putBoolean("isCreatedFromViewPager",true);
//        BaseMvpFragment girlFragment = BaseMvpFragment.<GirlDetailFragment>getInstance(GirlDetailFragment.class,bundle);
//        transaction.add(R.id.girl_detail_container,girlFragment);
//        transaction.addToBackStack(girlFragment.getClass().getSimpleName());
//        transaction.commitAllowingStateLoss();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("girls",mData);
        bundle.putInt("currentPosition",position);
        bundle.putBoolean("isCreatedFromViewPager",true);
        BaseMvpFragment girlFragment = BaseMvpFragment.<GirlDetailFragment>getInstance(GirlDetailFragment.class,bundle);
        transaction.add(R.id.content_fragment,girlFragment);
        transaction.addToBackStack(girlFragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();

    }

//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        super.onActivityReenter(resultCode, data);
//        bundle = data.getExtras();
//        int currentPosition = bundle.getInt(ImagePagerActivity.STATE_POSITION,0);
//        //做相应的滚动
//        recyclerView.scrollToPosition(currentPosition);
//        //暂时延迟 Transition 的使用，直到我们确定了共享元素的确切大小和位置才使用
//        //postponeEnterTransition后不要忘记调用startPostponedEnterTransition
//        ActivityCompat.postponeEnterTransition(this);
//        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//                // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
//                recyclerView.requestLayout();
//                //共享元素准备好后调用startPostponedEnterTransition来恢复过渡效果
//                ActivityCompat.startPostponedEnterTransition(MainActivity.this);
//                return true;
//            }
//        });
//    }
}
