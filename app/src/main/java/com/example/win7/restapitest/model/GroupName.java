package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by win7 on 01/05/2016.
 */
public class GroupName {


    @SerializedName("name")
    String name;

    public GroupName(String name){
        this.name = name;
    }

}
