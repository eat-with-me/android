package com.example.win7.restapitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by win7 on 03/04/2016.
 */
public class Order implements Parcelable{

    private ArrayList<Meal> meals;
    private double totalPrice;

    public Order()
    {
        meals = new ArrayList<Meal>();
        totalPrice = 0;
    }
    protected Order(Parcel in) {
        meals = in.createTypedArrayList(Meal.CREATOR);
        totalPrice = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public void add(Meal meal){

        meals.add(meal);
        totalPrice += meal.getPrice();
    }

    public void delete(Meal meal){

        if(meals.remove(meal)){
            totalPrice -= meal.getPrice();
        }

    }
    public void changeTotalPrice(double a){totalPrice -= a;}
    public double getTotalPrice(){return totalPrice;  }
    public void setTotalPrice(Double totalPrice){this.totalPrice = totalPrice;}

    public int getNumberOfOrderedMeals(){return meals.size();}

    public ArrayList<Meal> getMeals(){return meals;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(meals);
        dest.writeDouble(totalPrice);
    }
}
