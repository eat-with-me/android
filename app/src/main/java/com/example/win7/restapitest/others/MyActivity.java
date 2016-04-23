package com.example.win7.restapitest.others;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.screens.error_screen.ErrorActivity;
import com.example.win7.restapitest.screens.login_screen.LoginActivity;


public abstract class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_login:

                signOut();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void signOut(){

        //TODO finish session on server
        navigateToLoginActivity();
        removeAllCredentials();


    }


    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void navigateToErrorActivity(){
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);

    }

    private void navigateToLoginActivity(){

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void removeAllCredentials(){
        SharedPreferences settings = this.getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

}