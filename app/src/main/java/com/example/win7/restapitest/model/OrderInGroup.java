package com.example.win7.restapitest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


// /groups/<id>/orders


public class OrderInGroup implements Serializable {


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
    private String groupId;

    @SerializedName("restaurant")
    private Restaurant restaurant;

    @SerializedName("purchasers")
    private List<Purchaser> purchasers ;

    @SerializedName("owner_id")
    private Integer ownerId;

    @SerializedName("owner")
    private Owner owner;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt; }

    public Restaurant getRestaurant() {
        return restaurant; }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



    public boolean isActual(){

        return ((getTimeToCloseInMilliseconds() - 60000) > 0);
    }

    public String getClosingHour(){
        String time  = getClosingTime2hPlus();
        return time.substring(11,16);
    }

    public String getClosingDate(){

        String time  = getClosingTime2hPlus();
        //Log.d("time",time);

        String day = time.substring(8,10);
        Integer datInt = Integer.parseInt(day);
        day = Integer.toString(datInt);

        String month = time.substring(5,7);
        Integer monthInt = Integer.parseInt(month);
        //Log.d("time","*"+time.substring(4,7)+"*");


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

    private String getClosingTime2hPlus() {

        String time = convertClosingTime();

        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date clTime = null;

        try {

            clTime = df.parse(time);

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(clTime); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 2); // adds one hour
            clTime = cal.getTime(); // returns new date object, one hour in the future

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return df.format(clTime);

    }


    public String getClosingTimeFormated(){
        return getClosingDate() + getClosingHour();
    }



    public long getTime(){
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String closingTime = convertClosingTime();
        Date clTime = null;

        try {
            clTime = df.parse(closingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return clTime.getTime();
    }

    public String getTimeToClose() {


        long diff = getTimeToCloseInMilliseconds();

        return countTimeDifference(diff);
    }

    private long getTimeToCloseInMilliseconds (){

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

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(clTime); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 2); // adds one hour
            clTime = cal.getTime(); // returns new date object, one hour in the future

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return clTime.getTime()  - crTime.getTime();
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


    public String getTotalPrice() {

        double totalPrice = 0;

        for (Purchaser purchaser : purchasers) {

            totalPrice += purchaser.getTotalPrice();

        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String price = decimalFormat.format(totalPrice);

        return price;
    }
}



