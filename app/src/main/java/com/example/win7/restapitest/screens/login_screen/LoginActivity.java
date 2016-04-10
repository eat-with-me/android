package com.example.win7.restapitest.screens.login_screen;


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



import com.example.win7.restapitest.R;


class LoginActivity extends AppCompatActivity  implements LoginView{


    private ProgressBar progressBar;
    private TextView messageView;
    private RelativeLayout loginForm;
    private EditText emailText;
    private EditText passwordText;
    private Button signUpButton;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText= (EditText) findViewById(R.id.login_view);
        passwordText = (EditText) findViewById(R.id.password_view);
        messageView = (TextView) findViewById(R.id.message_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        loginForm = (RelativeLayout) findViewById(R.id.relative_layout);

        loginPresenter = new LoginPresenterImp(this);


        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

    }

    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }
    }


    public void onClickLogin() {
        loginPresenter.onClickLogin();
    }


    public void onClickCreateAccount(){
        loginPresenter.onCLickCreateAccount();
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
    public void setEmptyEmailError{

        String emptyEmail = getString(R.string.no_internet);
        emailText.setError("");
    }

}

