package com.example.win7.restapitest.screens.new_group_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.api.OnLoginListener;
import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.GroupName;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by win7 on 01/05/2016.
 */
public class NewGroupPresenterImp implements NewGroupPresenter {

    private NewGroupView newGroupView;
    private ApiConnection apiConnection;


    public NewGroupPresenterImp(NewGroupView newGroupView) {
        this.newGroupView = newGroupView;
        this.apiConnection = Factory.getApiConnection();

    }

    @Override
    public void onDestroy() { newGroupView = null;}

    @Override
    public void onResume() {

    }


    @Override
    public void onClickOk(){

        String groupName = newGroupView.getGroupName();

        newGroupView.resetErrors();


        if(groupName.isEmpty()){

            newGroupView.setEmptyGroupNameError();
        }
        else{

            newGroupView.showProgress();
            apiConnection.createNewGroup(new GroupName(groupName), new OnDownloadFinishedListener<Group>() {

                @Override
                public void onSuccess(Group group) {

                    newGroupView.cleanTextField();
                    newGroupView.hideProgress();
                    newGroupView.showToast("Dodano nową grupę");
                }


                @Override
                public void onError() {

                    newGroupView.showAlertDialog();
                }
            });
        }

    }



}
