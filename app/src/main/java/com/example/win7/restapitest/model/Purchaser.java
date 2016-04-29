package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class Purchaser {

    @SerializedName("id")
    String id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;
    @SerializedName("meals_lists")
    List<MealsList> meals_lists;
    @SerializedName("user")
    User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<MealsList> getMeals_list() {
        return meals_lists;
    }

    public void setMeals_list(List<MealsList> meals_list) {
        this.meals_lists = meals_list;
    }
}
