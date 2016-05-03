package com.example.win7.restapitest.screens.main_screen;

import com.example.win7.restapitest.model.Group;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public interface MainView {

    void setEmptyView();

    void loadGroups(List<Group> groupsResult);

    void showToast(String message);

    void goToOrdersInGroupActivity(Group group);

    void showProgress();

    void hideProgress();

    void showError();

    void navigateToNewGroupActivity();

    void showAlertDialog();
}
