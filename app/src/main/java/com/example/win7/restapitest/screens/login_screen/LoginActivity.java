package com.example.win7.restapitest.screens.login_screen;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.others.MyBaseActivity;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.sign_up_screen.SignUpActivity;


public class LoginActivity extends MyBaseActivity implements LoginView{


    private ProgressBar progressBar;
    private TextView messageView;
    private RelativeLayout loginForm;
    private EditText emailText;
    private EditText passwordText;
    private Button signUpButton;
    private TextView loginGoesWrongView;

    private LoginPresenter loginPresenter;
    private boolean appOpenedWithLink;
    private String linkToAddToGroup;
    public final static String APP_OPENED_WITH_LINK = "appOpenedWithLink";
    public final static String LINK_TO_ADD_TO_GROUP = "linkToAddGroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText= (EditText) findViewById(R.id.login_view);
        passwordText = (EditText) findViewById(R.id.password_view);
        messageView = (TextView) findViewById(R.id.message_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        loginForm = (RelativeLayout) findViewById(R.id.login_layout);
        loginGoesWrongView = (TextView) findViewById(R.id.login_goes_wrong);

        loginPresenter = new LoginPresenterImp(this);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        if(Intent.ACTION_VIEW.equals(action)) {
            appOpenedWithLink = true;
            linkToAddToGroup = intent.getDataString();
        }

        loginPresenter.tryLogin();

    }

    @Override
    public void refresh() {
        loginPresenter.onClickLogin();
    }

    @Override
    public void close() {
        moveTaskToBack(true);
        hideProgressBar();
    }

    public void onClickLogin(View v) {

        loginPresenter.onClickLogin();
    }


    public void onClickCreateAccount(View v){
        loginPresenter.onCLickSignUp();
    }

    @Override
    public String getEmail(){
         return emailText.getText().toString();
    }

    @Override
    public String getPassword(){
        return passwordText.getText().toString();
    }

    @Override
    public void setEmptyEmailError(){

        String emptyEmail = getString(R.string.error_empty_field);
        emailText.setError(emptyEmail);
    }

    @Override
    public void setEmptyPasswordError(){
        String emptyPassword = getString(R.string.error_empty_field);
        passwordText.setError(emptyPassword);
    }

    @Override
    public void setInvalidEmailError() {

        String invalidEmail = getString(R.string.error_invalid_email);
        emailText.setError(invalidEmail);

    }

    @Override
    public void setPasswordTooShortError() {

        String passwordTooShort = getString(R.string.error_short_password);
        passwordText.setError(passwordTooShort);

    }

    @Override
    public void showProgressBar(){

        messageView.setVisibility(View.INVISIBLE);
        loginForm.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(){

        messageView.setVisibility(View.INVISIBLE);
        loginForm.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoginGoesWrongMessage(){

        loginGoesWrongView.setText(R.string.login_goes_wrong);
        loginGoesWrongView.setVisibility(View.VISIBLE);

    }

    @Override
    public void navigateToMainActivity(){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(APP_OPENED_WITH_LINK,appOpenedWithLink);
        intent.putExtra(LINK_TO_ADD_TO_GROUP, linkToAddToGroup);

        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToSignUpActivity(){
        startActivity(new Intent(this, SignUpActivity.class));
    }



    @Override
    public void resetErrors() {
        passwordText.setError(null);
        emailText.setError(null);
    }

    @Override
    public void showWrongCredentialMessage(){

        loginGoesWrongView.setVisibility(View.VISIBLE);
    }

    @Override
    public void saveCredentials(Credentials credentials){
        SharedPreferences sharedPref = this.getSharedPreferences("CREDENTIALS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", credentials.getEmail());
        editor.putString("password", credentials.getPassword());
        editor.apply();
    }

    @Override
    public Credentials getCredentials(){
        SharedPreferences sharedPref = this.getSharedPreferences("CREDENTIALS",Context.MODE_PRIVATE);
        String password = sharedPref.getString("password", "");
        String email = sharedPref.getString("email", "");

        if(password.equals("") || email.equals(""))
            return null;
        else
            return new Credentials(email, password, "");

    }


}

