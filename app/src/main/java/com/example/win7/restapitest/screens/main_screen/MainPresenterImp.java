package com.example.win7.restapitest.screens.main_screen;

import android.util.Log;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.others.Factory;

import java.util.List;


public class MainPresenterImp implements MainPresenter {


    private MainView mainView;
    private ApiConnection apiConnection;

    List<Group> groupsResult = null;

    public MainPresenterImp(MainView mainView) {
        this.mainView = mainView;
        this.apiConnection = Factory.getApiConnection();

    }

    @Override
    public void onDestroy() { mainView = null;}

    @Override
    public void onResume() {

    }

    @Override
    public void getGroups() {

        apiConnection.getGroups(new OnDownloadFinishedListener<List<Group>>() {
            @Override
            public void onSuccess(List<Group> list) {
                groupsResult = list;
                mainView.hideProgress();


                if(groupsResult.isEmpty()){
                    mainView.setEmptyView();

                }
                else{

                    mainView.loadGroups(groupsResult);
                }
            }

            @Override
            public void onError() {

                mainView.showError();
            }
        });

    }

    @Override
    public void onClickGroup(int position){

        Group group = groupsResult.get(position);
        String groupId = group.getId();
        mainView.goToOrdersInGroupActivity(groupId);

    }

    @Override
    public void onClickNewGroup(){
        mainView.showToast("New group is selected");
    }

 }

