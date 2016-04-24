package com.example.win7.restapitest.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


// /groups/<id>/orders

public class OrderInGroup {


    @SerializedName("id")
    private String id;

    @SerializedName("closing_time")
    private String closingTime;

    @SerializedName("restaurant_id")
    private String restaurantId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("group_id")
    private Integer groupId;

    @SerializedName("restaurant")
    private Restaurant restaurant;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClosingTime() {
        return convertClosingTime();
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {  this.createdAt = createdAt; }

    public Restaurant getRestaurant() { return restaurant; }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getClosingTimeShort() {

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String closingTime = convertClosingTime();
        Log.d("currentTime",currentTime);
        Log.d("closingTime",closingTime);



        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date crTime = null;
        Date clTime = null;

        try {
            crTime = df.parse(currentTime);
            clTime = df.parse(closingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = clTime.getTime() - crTime.getTime();

        Log.d("diff", String.valueOf(diff));

        return countTimeDifference(diff);
    }

    private String convertClosingTime(){
        String result =  closingTime.substring(0,19);
        result = result.replace('T', ' ');
        return result;
    }

    private String countTimeDifference(long diff){

        String result = "";


        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays != 0) {
            result += String.valueOf(diffDays) + " dni ";
        }
        if (diffHours != 0) {
            result += String.valueOf(diffHours) + " godzin ";
        }
        if (diffMinutes != 0) {
            result += String.valueOf(diffMinutes) + "minut";
        }
        return result;
    }

}



