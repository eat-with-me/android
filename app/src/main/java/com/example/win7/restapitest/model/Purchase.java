package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class Purchase {

    @SerializedName("id")
    String id;

    @SerializedName("meals")
    List<Meal> meals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Purchase(String id, List<Meal> meals){
        this.id=id;
        this.meals=meals;
    }

}
