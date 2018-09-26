package com.example.mercu.finalfantasy.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.LoginContract;
import com.example.mercu.finalfantasy.presenter.main.LoginPresenter;

import butterknife.BindView;

/**
 * Created by qicheng on 2018/9/26.
 */

public class LoginFragment extends BaseMvpFragment<LoginPresenter>
                    implements LoginContract.View
{
    @BindView(R.id.register)
    TextView register;

    @BindView(R.id.login)
    TextView login;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView()
    {
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //开启注册界面
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCreatedFromViewPager",true);
                BaseMvpFragment registerFragment = BaseMvpFragment.<RegisterFragment>getInstance(RegisterFragment.class,bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.content_fragment,registerFragment);
                transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag(LoginFragment.class.getSimpleName()));
                transaction.show(registerFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //执行登录
            }
        });
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }
}
