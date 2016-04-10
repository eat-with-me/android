package com.example.win7.restapitest.screens.login_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by win7 on 10/04/2016.
 */
public class LoginPresenterImp implements LoginPresenter{

    private LoginView loginView;
    private ApiConnection apiConnection;



    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        apiConnection = Factory.getApiConnection();
    }

    @Override
    public void onClickLogin() {

        String email = loginView.getEmail();
        String password = loginView.getPassword();

        loginView.resetErrors();

        if(!isEmailCorrect(email)){
            return;
        }
        else if(!isPasswordCorrect(password)){
            return;
        }
        else
        {
            //loginView.showProgressBar();
            loginView.navigateToMainActivity();
            //apiConnection.login(email, password);
        }


    }

    @Override
    public void onCLickCreateAccount() {
        loginView.showToast("Create Account is selected");
    }

    @Override
    public boolean isEmailCorrect(String email)
    {
        if(email.isEmpty()){

            loginView.setEmptyEmailError();
            return false;
        }
        else if(isEmailValid(email)) {

            loginView.setInvalidEmailError();
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

}
