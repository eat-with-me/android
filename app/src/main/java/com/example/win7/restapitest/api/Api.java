package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface Api {

    @GET("/groups")
    public void getGroups(Callback<List<Group>> callback);

    @GET("/groups/{id}/orders")
    public void getOrdersInGroups(@Path("id") String user,Callback<List<OrderInGroup>> callback);

}
