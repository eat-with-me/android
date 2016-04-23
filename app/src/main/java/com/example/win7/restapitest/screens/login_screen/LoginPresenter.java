package com.example.win7.restapitest.screens.login_screen;

/**
 * Created by win7 on 10/04/2016.
 */
public interface LoginPresenter {

    void onClickLogin();

    void onCLickSignUp();

    boolean isEmailCorrect(String email);

    boolean isEmailValid(String email);

    boolean isPasswordCorrect(String password);

    void tryLogin();
}
