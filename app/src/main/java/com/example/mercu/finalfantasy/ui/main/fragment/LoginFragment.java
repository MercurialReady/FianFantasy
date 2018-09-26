package com.example.mercu.finalfantasy.ui.main.fragment;

import android.util.Log;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.CollectContract;
import com.example.mercu.finalfantasy.presenter.main.CollectPresenter;

/**
 * Created by qicheng on 2018/9/26.
 */

public class LoginFragment extends BaseMvpFragment<CollectPresenter>
                    implements CollectContract.View
{
    @Override
    public int getLayoutId()
    {
        Log.d("Mercurial","load Login");
        return R.layout.fragment_login;
    }

    @Override
    protected void initView()
    {

    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }
}
