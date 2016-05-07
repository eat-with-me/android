package com.example.win7.restapitest.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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

    @SerializedName("purchasers")
    private List<Purchaser> purchasers;




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

    public String getNormalClsingTime(){
        return closingTime;
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

    public String getClosingHour(){
        getClosingDate();
        return closingTime.substring(11,16);
    }

    public String getClosingDate(){


        String day = closingTime.substring(8,10);
        Integer datInt = Integer.parseInt(day);
        day = Integer.toString(datInt);

        String month = closingTime.substring(5,7);
        Integer monthInt = Integer.parseInt(month);

        switch (monthInt) {

            case 1 :    month = "stycznia,";
                        break;
            case 2 :    month = "lutego,";
                        break;
            case 3 :    month = "marca,";
                        break;
            case 4 :    month = "kwietnia,";
                         break;
            case 5 :    month = "maja,";
                        break;
            case 6 :    month = "czerwca,";
                        break;
            case 7 :    month = "lipca,";
                        break;
            case 8 :    month = "sierpnia,";
                        break;
            case 9 :    month = "września,";
                        break;
            case 10 :   month = "października,";
                        break;
            case 11 :   month = "listopada,";
                        break;
            case 12 :   month = "grudnia,";
                        break;

        }


        return day + " " + month + " ";
    }

    public String getClosingTimeFormated(){
        return getClosingDate() + getClosingHour();
    }

    public String getTimeToClose() {

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

    public List<Purchaser> getPurchasers() {
        return purchasers;
    }

    public void setPurchasers(List<Purchaser> purchasers) {
        this.purchasers = purchasers;
    }

    public String getNumberOfPurchasers(){

       return String.valueOf(purchasers.size());
    }
}



