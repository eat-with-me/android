package com.example.win7.restapitest.screens.sign_up_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.others.Factory;
import com.example.win7.restapitest.screens.login_screen.LoginView;

/**
 * Created by win7 on 10/04/2016.
 */
public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView signUpView;
    private ApiConnection apiConnection;


    public SignUpPresenterImp(SignUpView signUpView){

        this.signUpView = signUpView;
        apiConnection = Factory.getApiConnection();
    }



    @Override
    public void onClickSignUp() {

        String email = signUpView.getEmail();
        String password = signUpView.getPassword();
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
            //loginView.showProgressBar();
            signUpView.navigateToMainActivity();
            //apiConnection.login(email, password);
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
