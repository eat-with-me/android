package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-04-29.
 */
public class MealsList {

    @SerializedName("id")
    Integer id;

    @SerializedName("purchaser_id")
    Integer purchaser_id;

    @SerializedName("meal_id")
    Integer meal_id;

    @SerializedName("amount")
    Integer amount;

    @SerializedName("meal")
    Meal meal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaser_id() {
        return purchaser_id;
    }

    public void setPurchaser_id(Integer purchaser_id) {
        this.purchaser_id = purchaser_id;
    }

    public Integer getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(Integer meal_id) {
        this.meal_id = meal_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
