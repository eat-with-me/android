package com.example.win7.restapitest.screens.sign_up_screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.User;
import com.example.win7.restapitest.screens.error_screen.ErrorActivity;
import com.example.win7.restapitest.screens.login_screen.LoginPresenter;
import com.example.win7.restapitest.screens.main_screen.MainActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {


    private ProgressBar progressBar;
    private TextView messageView;
    private RelativeLayout loginForm;
    private EditText emailText;
    private EditText passwordText;
    private EditText passwordAgainText;
    private TextView somethingGoesWrong;

    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        messageView = (TextView) findViewById(R.id.message_view);
        loginForm = (RelativeLayout) findViewById(R.id.login_layout);
        emailText = (EditText) findViewById(R.id.login_view);
        passwordText = (EditText) findViewById(R.id.password_view);
        passwordAgainText = (EditText) findViewById(R.id.password_again_view);
        somethingGoesWrong = (TextView) findViewById(R.id.something_goes_wrong);

        signUpPresenter = new SignUpPresenterImp(this);


    }

    public void onClickSignUp(View v){
        signUpPresenter.onClickSignUp();
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
    public String getPasswordAgain(){
        return passwordAgainText.getText().toString();}

    @Override
    public void setEmptyEmailError(){
        String emptyEmail = getString(R.string.error_empty_field);
        emailText.setError(emptyEmail);
    }

    @Override
    public void setDefferentPasswordError() {
        String differentPasswords = getString(R.string.error_defferent_passwords);
        passwordText.setError(differentPasswords);
    }

    @Override
    public void setEmptyPasswordError(){
        String emptyPassword = getString(R.string.error_empty_field);
        passwordText.setError(emptyPassword);
    }

    @Override
    public void setEmptyPasswordAgainError(){
        String emptyPassword = getString(R.string.error_empty_field);
        passwordAgainText.setError(emptyPassword);
    }

    @Override
    public void setInvalidEmailError() {
        String invalidEmail = getString(R.string.error_invalid_email);
        emailText.setError(invalidEmail);

    }

    @Override
    public void setEmailTooShortError() {
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
        somethingGoesWrong.setText(R.string.something_goes_wrong);
        somethingGoesWrong.setVisibility(View.VISIBLE);

    }

    @Override
    public void navigateToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetErrors() {
        passwordText.setError(null);
        passwordAgainText.setError(null);
        emailText.setError(null);
    }

    @Override
    public void showWrongCredentialMessage() {
        somethingGoesWrong.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToErrorActivity(){
        startActivity(new Intent(this, ErrorActivity.class));
    }

    @Override
    public void saveCredentials(Credentials credentials){
        SharedPreferences sharedPref = this.getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", credentials.getEmail());
        editor.putString("password", credentials.getPassword());
        editor.apply();
    }
}
