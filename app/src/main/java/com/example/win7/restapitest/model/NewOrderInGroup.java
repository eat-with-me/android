package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-05-03.
 */
public class NewOrderInGroup {

    @SerializedName("restaurant_id")
    Integer restaurant_id;

    @SerializedName("closing_time")
    String closing_time;

    public NewOrderInGroup(Integer restaurant_id, String closing_time) {
        this.restaurant_id = restaurant_id;
        this.closing_time = closing_time;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }
}
