package com.example.mercu.finalfantasy.ui.main.fragment;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.RegisterContract;
import com.example.mercu.finalfantasy.presenter.main.RegisterPresenter;

/**
 * Created by Mercu on 2018/9/26.
 */

public class RegisterFragment extends BaseMvpFragment<RegisterPresenter>
                        implements RegisterContract.View
{
    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_register;
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
