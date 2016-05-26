package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.GroupName;
import com.example.win7.restapitest.model.LoginAnswer;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Endpoints {

    @GET("/groups")
    Call<List<Group>> getGroups();

    @GET("/groups/{id}/orders")
    Call<List<OrderInGroup>> getOrdersInGroups(@Path("id") String user);

    @GET("/restaurants")
    Call<List<RestaurantMenu>> getAllRestaurantsMenu();

    @GET("/restaurants/{id}")
    Call<RestaurantMenu> getRestaurantMenu(@Path("id") String user);

    @GET("/groups/{group_id}/orders/{order_id}")
    Call<OrderInGroup> getPurchasers(@Path("group_id") int group_id, @Path("order_id") int order_id);

    @POST("/users/sign_in.json")
    Call<LoginAnswer> login(@Body Credentials credentials);

    @POST("/users.json")
    Call<LoginAnswer> signUp(@Body Credentials credentials);

    @POST("/groups/{group_id}/purchasers")
    Call<FinalOrder> purchase(@Path("group_id") int group_id, @Body FinalOrder finalOrder);

    @POST("/groups")
    Call<Group> createNewGroup(@Body GroupName groupName);

    @POST("/groups/{group_id}/orders")
    Call<OrderInGroup> createNewOrder(@Body OrderInGroup orderInGroup, @Path("group_id") Integer group_id);

    @GET("/join/{token}")
    Call<Void> addToGroup(@Path("token") String token);
}
