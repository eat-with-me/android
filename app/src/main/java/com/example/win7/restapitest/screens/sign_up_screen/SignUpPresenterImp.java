package com.example.win7.restapitest.screens.sign_up_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnLoginListener;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.others.Factory;

import java.util.regex.Pattern;


public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView signUpView;
    private ApiConnection apiConnection;
    private static final Pattern EMAIL = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public SignUpPresenterImp(SignUpView signUpView){

        this.signUpView = signUpView;
        apiConnection = Factory.getApiConnection();
    }



    @Override
    public void onClickSignUp() {

        final String email = signUpView.getEmail();
        final String password = signUpView.getPassword();
        final String secondPassword = signUpView.getPasswordAgain();


        signUpView.resetErrors();

        if(!isEmailCorrect(email)){
            return;
        }
        else if(!isPasswordCorrect(password)){
            return;
        }
        else if(secondPassword.isEmpty()){
            signUpView.setEmptyPasswordAgainError();
            return;
        }
        else if(!secondPassword.equals(password)){
            signUpView.setDifferentPasswordError();
            return;
        }
        else
        {
            signUpView.showProgressBar();
            apiConnection.signUp(new Credentials(email, password, secondPassword), new OnLoginListener() {
                @Override
                public void onSuccess() {
                    signUpView.saveCredentials(new Credentials(email, password, ""));
                    signUpView.navigateToMainActivity();
                }

                @Override
                public void onWrongCredentials() {

                    signUpView.showWrongCredentialMessage();
                    signUpView.hideProgressBar();

                }

                @Override
                public void onError() {

                    signUpView.showAlertDialog();

//                    signUpView.hideProgressBar();
//                    signUpView.navigateToErrorActivity();
                }
            });
        }


    }


    @Override
    public boolean isEmailCorrect(String email)
    {
        if(email.isEmpty()){

            signUpView.setEmptyEmailError();
            return false;
        }
        else if(isEmailValid(email)) {

            signUpView.setInvalidEmailError();
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public boolean isEmailValid(String email) {

        return !EMAIL.matcher(email).matches();
    }




    @Override
    public boolean isPasswordCorrect(String password)
    {
        if(password.isEmpty()){

            signUpView.setEmptyPasswordError();
            return false;
        }
        else if(password.length() < 8){

            signUpView.setPasswordTooShortError();
            return false;
        }
        else{
            return true;
        }

    }

}
