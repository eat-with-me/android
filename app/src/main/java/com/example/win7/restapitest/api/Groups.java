package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Group;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface Groups {

    @GET("/groups")
    public void getGroups(Callback<List<Group>> callback);
}
