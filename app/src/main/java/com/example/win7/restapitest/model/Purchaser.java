package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class Purchaser {

    @SerializedName("id")
    Integer id;
    @SerializedName("user_id")
    Integer user_id;
    @SerializedName("meals")
    Meal[] meals;
    @SerializedName("user")
    User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
