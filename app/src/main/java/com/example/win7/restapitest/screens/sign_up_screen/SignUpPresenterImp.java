package com.example.win7.restapitest.screens.sign_up_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnLoginListener;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.User;
import com.example.win7.restapitest.others.Factory;
import com.example.win7.restapitest.screens.login_screen.LoginView;


public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView signUpView;
    private ApiConnection apiConnection;


    public SignUpPresenterImp(SignUpView signUpView){

        this.signUpView = signUpView;
        apiConnection = Factory.getApiConnection();
    }



    @Override
    public void onClickSignUp() {

        final String email = signUpView.getEmail();
        final String password = signUpView.getPassword();
        String secondPassword = signUpView.getPasswordAgain();


        signUpView.resetErrors();

        if(!isEmailCorrect(email)){
            return;
        }
        else if(!isPasswordCorrect(password)){
            return;
        }
        else if(!secondPassword.equals(password)){
            signUpView.setDefferentPasswordError();
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
                    signUpView.hideProgressBar();
                    signUpView.navigateToErrorActivity();
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

        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }




    @Override
    public boolean isPasswordCorrect(String password)
    {
        if(password.isEmpty()){

            signUpView.setEmptyPasswordError();
            return false;
        }
        else if(password.length() < 8){

            signUpView.setEmailTooShortError();
            return false;
        }
        else{
            return true;
        }

    }

}
