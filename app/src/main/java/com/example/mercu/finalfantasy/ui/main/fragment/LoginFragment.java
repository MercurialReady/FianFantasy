package com.example.mercu.finalfantasy.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.app.Constants;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.LoginContract;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.presenter.main.LoginPresenter;
import com.example.mercu.finalfantasy.utils.rx.RxBus;

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

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

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
                mPresenter.login(username.getText().toString().trim(),
                        password.getText().toString().trim());
            }
        });
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }

    @Override
    public void loginSuccess(LoginData data)
    {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(this);
        transaction.show(getActivity().getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName()));
        transaction.commitAllowingStateLoss();

        RxBus.getsInstance().post(Constants.LOGIN_SUCCESS,data);
    }
}
