package com.example.win7.restapitest.main_screen;

import android.widget.Toast;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.ApiConnectionImp;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.others.Factory;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
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

        groupsResult = apiConnection.getGroups();


        if(groupsResult.isEmpty()){
            mainView.setEmptyView();
        }
        else{
            mainView.loadGourps(groupsResult);
        }


    }

    @Override
    public void onClickGroup(int position){

        Group group = groupsResult.get(position);
        mainView.showToast(group.getName() + " is selected!");

    }

    @Override
    public void onClickNewGroup(){
        mainView.showToast("New group is selected");
    }

}

