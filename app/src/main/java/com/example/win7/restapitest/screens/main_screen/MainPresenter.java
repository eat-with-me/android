package com.example.win7.restapitest.screens.main_screen;

/**
 * Created by win7 on 28/03/2016.
 */
public interface MainPresenter {
    void onDestroy();
    void onResume();
    void getGroups();
    void onClickGroup(int position);
    void onClickNewGroup();


}
