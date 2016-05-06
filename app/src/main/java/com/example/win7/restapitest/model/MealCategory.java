package com.example.win7.restapitest.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by win7 on 06/05/2016.
 */
public class MealCategory implements ParentListItem {

    private Integer id;
    private String name;
    private List<Meal> meals = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<?> getChildItemList() {
        return meals;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}
