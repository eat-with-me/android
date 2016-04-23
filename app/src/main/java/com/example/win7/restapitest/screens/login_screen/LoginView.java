package com.example.win7.restapitest.screens.login_screen;

import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.User;

/**
 * Created by win7 on 10/04/2016.
 */
public interface LoginView {

    String getEmail();

    String getPassword();

    void setEmptyEmailError();

    void setEmptyPasswordError();

    void setInvalidEmailError();

    void setEmailTooShortError();

    void showProgressBar();

    void hideProgressBar();

    void showLoginGoesWrongMessage();

    void navigateToMainActivity();

    void navigateToSignUpActivity();

    void showToast(String message);

    void resetErrors();

    void showWrongCredentialMessage();

    void navigateToErrorScreen();

    void saveCredentials(Credentials credentials);

    Credentials getCredentials();
}
