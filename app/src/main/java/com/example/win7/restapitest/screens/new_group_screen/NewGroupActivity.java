package com.example.win7.restapitest.screens.new_group_screen;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.others.MyActivity;

public class NewGroupActivity extends MyActivity implements NewGroupView {

    private NewGroupPresenter newGroupPresenter;
    private RelativeLayout newGroupForm;
    private ProgressBar progressBar;
    private TextView messageView;
    private EditText groupNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_group));
        setSupportActionBar(myToolbar);

        newGroupForm = (RelativeLayout) findViewById(R.id.new_group_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        messageView = (TextView) findViewById(R.id.message_view);
        groupNameText = (EditText) findViewById(R.id.login_view);

        newGroupPresenter = new NewGroupPresenterImp(this);
    }


    public void onClickOK(View v){
        newGroupPresenter.onClickOk();
    }

    @Override
    public String getGroupName(){

        return groupNameText.getText().toString();
    }

    @Override
    protected void onDestroy(){
        newGroupPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        newGroupPresenter.onResume();
        hideProgress();

    }


    @Override
    public void showProgress() {

        //TODO rozwiązać to jakoś inaczej (przeładowanie całego widoku)

        progressBar.setVisibility(View.VISIBLE);
        messageView.setVisibility(View.INVISIBLE);
        newGroupForm.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        messageView.setVisibility(View.INVISIBLE);
        newGroupForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.INVISIBLE);
        newGroupForm.setVisibility(View.INVISIBLE);

        String noInternet = getString(R.string.no_internet);
        messageView.setText(noInternet);
        messageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void setEmptyGroupNameError() {
        String emptyGroupName = getString(R.string.error_empty_field);
        groupNameText.setError(emptyGroupName);
    }

    @Override
    public void resetErrors() {
        groupNameText.setError(null);

    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void cleanTextField(){
        groupNameText.setText("");
    }
}
