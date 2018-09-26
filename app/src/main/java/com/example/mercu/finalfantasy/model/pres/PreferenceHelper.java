package com.example.mercu.finalfantasy.model.pres;

/**
 * Created by Mercu on 2018/8/22.
 */

public interface PreferenceHelper
{
    void setLoginAccount(String account);

    String getLoginAccount();

    void setLoginPassword(String password);

    String getLoginPassword();

    void setLoginStatus(Boolean status);

    Boolean getLoginStatus();
}
