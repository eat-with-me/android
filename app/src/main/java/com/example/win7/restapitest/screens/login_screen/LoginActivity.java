package com.example.win7.restapitest.screens.login_screen;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.sign_up_screen.SignUpActivity;


public class LoginActivity extends AppCompatActivity  implements LoginView{


    private ProgressBar progressBar;
    private TextView messageView;
    private RelativeLayout loginForm;
    private EditText emailText;
    private EditText passwordText;
    private Button signUpButton;
    private TextView loginGoesWrongView;

    private LoginPresenter loginPresenter;

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
        loginPresenter.tryLogin();

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

        loginGoesWrongView.setText(R.string.login_goes_wrong);
        loginGoesWrongView.setVisibility(View.VISIBLE);

    }

    @Override
    public void navigateToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToSignUpActivity(){
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
    public void navigateToErrorScreen(){
        startActivity(new Intent(this, ErrorActivity.class));
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

