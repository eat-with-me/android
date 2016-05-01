package com.example.win7.restapitest.screens.new_group_screen;


public interface NewGroupView {


    String getGroupName();

    void showProgress();

    void hideProgress();

    void showError();

    void setEmptyGroupNameError();

    void resetErrors();

    void showToast(String message);

    void cleanTextField();
}
