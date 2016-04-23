package com.example.win7.restapitest.screens.sign_up_screen;


import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.User;

public interface SignUpView {


    String getEmail();

    String getPassword();

    String getPasswordAgain();

    void setEmptyEmailError();

    void setDefferentPasswordError();

    void setEmptyPasswordError();

    void setEmptyPasswordAgainError();

    void setInvalidEmailError();

    void setEmailTooShortError();

    void showProgressBar();

    void hideProgressBar();

    void showLoginGoesWrongMessage();

    void navigateToMainActivity();

    void showToast(String message);

    void resetErrors();

    void showWrongCredentialMessage();

    void navigateToErrorActivity();

    void saveCredentials(Credentials credentials);
}
