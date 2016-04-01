package com.example.win7.restapitest.api;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public class ApiConnectionImp implements ApiConnection {

    private final String GROUPS_ENQUIRY = "http://d7205d3d.ngrok.io/";
    private List<Group> groupsResult = null;

    public List<Group> getGroups()
    {
//        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(GROUPS_ENQUIRY).build();
//
//        Groups groups = adapter.create(Groups.class);
//
//
//        groups.getGroups(new Callback<List<Group>>() {
//            @Override
//            public void success(List<Group> groupResponse, Response response) {
//                groupsResult = groupResponse;
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                //TODO obsłużyć brak połączenia z internetem
//            }
//        });

        mockGroups();
        return groupsResult;
    }

    @Override
    public List<OrderInGroup> getOrdersInGroup() {
        return null;
    }

    @Override
    public void requestForOrdersList() {

    }


    private void mockGroups(){

        groupsResult = new ArrayList<Group>();

        Group group = new Group();
        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

    }

    private void mockOrders(){

    }
}
