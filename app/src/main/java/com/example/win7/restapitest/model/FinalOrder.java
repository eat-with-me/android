package com.example.win7.restapitest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mateusz on 2016-04-26.
 */
public class FinalOrder {

    @SerializedName("order")
    Purchase order;

    public FinalOrder(Purchase order){
        this.order = order;
    }
}
