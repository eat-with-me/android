package com.example.win7.restapitest.main_screen;

import com.example.win7.restapitest.model.Group;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public interface MainView {

    public void setEmptyView();
    public void loadGourps(List<Group> groupsResult);
    public void showToast(String message);
}
