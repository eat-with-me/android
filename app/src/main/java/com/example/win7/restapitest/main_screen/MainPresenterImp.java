package com.example.win7.restapitest.main_screen;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.ApiConnectionImp;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.orders_in_group_screen.OrdersInGroupActivity;
import com.example.win7.restapitest.others.Factory;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public class MainPresenterImp implements MainPresenter {

    public static final String GROUP_ID = "groupId" ;
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

        //TODO  byćmoże to powinno się znaleźć w aktywności

        Group group = groupsResult.get(position);
        String groupId = group.getId();
        Intent intent = new Intent((MainActivity)mainView, OrdersInGroupActivity.class);
        intent.putExtra(GROUP_ID, groupId);
        ((MainActivity) mainView).startActivity(intent);
    }

    @Override
    public void onClickNewGroup(){
        mainView.showToast("New group is selected");
    }

}

