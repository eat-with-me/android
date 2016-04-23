package com.example.win7.restapitest.screens.sign_up_screen;


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
}
