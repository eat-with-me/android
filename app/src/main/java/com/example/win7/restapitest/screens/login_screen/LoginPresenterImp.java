package com.example.win7.restapitest.screens.login_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.api.OnLoginListener;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.User;
import com.example.win7.restapitest.others.Factory;


public class LoginPresenterImp implements LoginPresenter{

    private LoginView loginView;
    private ApiConnection apiConnection;



    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        apiConnection = Factory.getApiConnection();
    }

    @Override
    public void onClickLogin() {

        final String email = loginView.getEmail();
        final String password = loginView.getPassword();

        loginView.resetErrors();

        if(!isEmailCorrect(email)){
            return;
        }
        else if(!isPasswordCorrect(password)){
            return;
        }
        else
        {
            loginView.showProgressBar();
            apiConnection.login(new Credentials(email, password, ""), new OnLoginListener() {
                @Override
                public void onSuccess() {

                    loginView.saveCredentials(new Credentials(email,password,""));
                    loginView.navigateToMainActivity();
                }

                @Override
                public void onWrongCredentials() {
                    loginView.showWrongCredentialMessage();
                    loginView.hideProgressBar();
                }

                @Override
                public void onError() {
                    loginView.hideProgressBar();
                    loginView.navigateToErrorScreen();
                }
            });
        }


    }

    @Override
    public void onCLickSignUp() {
        loginView.navigateToSignUpActivity();
    }

    @Override
    public boolean isEmailCorrect(String email)
    {
        if(email.isEmpty()){

            loginView.setEmptyEmailError();
            return false;
        }
        else if(!isEmailValid(email)) {

            loginView.setInvalidEmailError();
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public boolean isEmailValid(String email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }




    @Override
    public boolean isPasswordCorrect(String password)
    {
            if(password.isEmpty()){

                loginView.setEmptyPasswordError();
                return false;
            }
            else if(password.length() < 8){

                loginView.setEmailTooShortError();
                return false;
            }
            else{
                return true;
            }

    }

    @Override
    public void tryLogin() {
        loginView.showProgressBar();
        Credentials credentials = loginView.getCredentials();

        if(credentials == null) {
            loginView.hideProgressBar();
            return;
        }
        else {
            apiConnection.login(credentials, new OnLoginListener() {
                @Override
                public void onSuccess() {
                    loginView.navigateToMainActivity();
                }

                @Override
                public void onWrongCredentials() {
                    loginView.hideProgressBar();
                }

                @Override
                public void onError() {
                    loginView.hideProgressBar();
                }
            });
        }
    }

}
