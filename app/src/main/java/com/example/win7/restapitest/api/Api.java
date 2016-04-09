package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Api {

    @GET("/groups")
    Call<List<Group>> getGroups();

    @GET("/groups/{id}/orders")
    Call<List<OrderInGroup>> getOrdersInGroups(@Path("id") String user);

    @GET("/restaurants")
    Call<List<RestaurantMenu>> getAllRestaurantsMenu();

    @GET("/restaurants/{id}")
    Call<RestaurantMenu> getRestaurantMenu(@Path("id") String user);

}
