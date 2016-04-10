package com.example.win7.restapitest.screens.login_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by win7 on 10/04/2016.
 */
public class LoginPresenterImp implements LoginPresenter{

    private LoginActivity loginActivity;
    private ApiConnection apiConnection;

    public LoginPresenterImp(LoginActivity loginActiviy) {
        this.loginActivity = loginActivity;
        apiConnection = Factory.getApiConnection();
    }

    @Override
    public void checkEmail()
    {

    }

    @Override
    public void checkPassword()
    {

    }

}
