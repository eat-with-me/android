package com.example.win7.restapitest.model;

import java.util.ArrayList;

/**
 * Created by win7 on 03/04/2016.
 */
public class Order {

    private ArrayList<Meal> meals = new ArrayList<Meal>();
    private double totalPrice = 0;


    public void add(Meal meal){

        meals.add(meal);
        totalPrice += meal.getPrice();
    }

    public void delete(Meal meal){

        if(meals.remove(meal)){
            totalPrice -= meal.getPrice();
        }

    }

    public double getTotalPrice(){return totalPrice;  }

    public int getNumberOfOrderedMeals(){return meals.size();}

    public ArrayList<Meal> getMeals(){return meals;}
}
