package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class Purchase {

    @SerializedName("id")
    String id;

    @SerializedName("meals")
    Integer[] meals;

    public Purchase(String id, Integer[] meals){
        this.id=id;
        this.meals=meals;
    }

}
