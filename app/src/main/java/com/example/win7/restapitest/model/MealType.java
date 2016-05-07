package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-05-06.
 */
public class MealType {

    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
