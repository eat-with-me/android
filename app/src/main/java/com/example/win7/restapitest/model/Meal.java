package com.example.win7.restapitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Meal implements Parcelable {

    @SerializedName("meal_id")
    private Integer meal_id;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("restaurant_id")
    private Integer restaurantId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("amount")
    private Integer amount;

    public Meal(String name, Double price)
    {
        this.name=name;
        this.price = price;
        this.amount=0;
    }
    public Meal(Meal otherMeal)
    {
        this.id = otherMeal.getId();
        this.name = otherMeal.getName();
        this.price = otherMeal.getPrice();
        this.restaurantId = otherMeal.getRestaurantId();
        this.createdAt = otherMeal.getCreatedAt();
        this.updatedAt = otherMeal.getUpdatedAt();
        this.amount = 1;
        this.meal_id = otherMeal.getId();
    }
    protected Meal(Parcel in) {

        name = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        price = in.readDouble();
        id = in.readInt();
        amount = in.readInt();
        meal_id = in.readInt();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeDouble(price);
        dest.writeInt(id);
        dest.writeInt(amount);
        dest.writeInt(meal_id);
    }

    public Integer getAmount() {
        return amount;
    }
    public void incAmount(){
        this.amount+=1;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(Integer meal_id) {
        this.meal_id = meal_id;
    }
}
