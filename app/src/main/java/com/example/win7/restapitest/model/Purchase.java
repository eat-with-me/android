package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class Purchase {

    @SerializedName("id")
    Integer id;

    @SerializedName("meals")
    List<Meal> meals;

    public Purchase(Integer id, List<Meal> meals){
        this.id=id;
        this.meals=meals;
    }

}
