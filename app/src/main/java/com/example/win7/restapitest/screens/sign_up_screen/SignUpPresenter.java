package com.example.win7.restapitest.screens.sign_up_screen;

/**
 * Created by win7 on 10/04/2016.
 */
public interface SignUpPresenter {

    void onClickSignUp();

    boolean isEmailCorrect(String email);

    boolean isEmailValid(String email);

    boolean isPasswordCorrect(String password);
}
