package com.example.mercu.finalfantasy.ui.main.fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mercu.finalfantasy.R;
import com.example.mercu.finalfantasy.base.BaseMvpFragment;
import com.example.mercu.finalfantasy.base.LoadingPage;
import com.example.mercu.finalfantasy.contract.main.RegisterContract;
import com.example.mercu.finalfantasy.model.bean.LoginData;
import com.example.mercu.finalfantasy.presenter.main.RegisterPresenter;
import com.example.mercu.finalfantasy.utils.view.Logger;

import butterknife.BindView;

/**
 * Created by Mercu on 2018/9/26.
 */

public class RegisterFragment extends BaseMvpFragment<RegisterPresenter>
                        implements RegisterContract.View
{
    @BindView(R.id.back)
    ImageView backArrow;

    @BindView(R.id.confirm)
    Button confirm;

    @BindView(R.id.register_account)
    EditText register_account;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.confirm_password)
    EditText confirm_password;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView()
    {
        backArrow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Mercurial","back from register");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mPresenter.register(register_account.getText().toString().trim(),
                        password.getText().toString().trim(),
                        confirm_password.getText().toString().trim());
            }
        });
    }

    @Override
    protected void loadData()
    {
        setState(LoadingPage.STATE_SUCCESS);
    }

    @Override
    public void registerSuccess(LoginData data)
    {
        mPresenter.autoLogin(data);
    }

    @Override
    public void loginSuccess(LoginData data)
    {
        //通知首页，让它显示登录状态
        Logger.d("register and login success");
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(this);
        transaction.show(getActivity().getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName()));
        transaction.commitAllowingStateLoss();
    }
}
