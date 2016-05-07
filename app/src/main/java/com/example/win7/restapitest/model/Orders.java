package com.example.win7.restapitest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by win7 on 07/05/2016.
 */
public class Orders {

    List<OrderInGroup> orders;

    public Orders(List<OrderInGroup> orders){
        this.orders = orders;
    }

    public List<OrderInGroup> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInGroup> orders) {
        this.orders = orders;
    }

    public int size(){
        return orders.size();
    }

    public void sortDate(){

        Collections.sort(orders, new Comparator<OrderInGroup>() {
            @Override
            public int compare(OrderInGroup lhs, OrderInGroup rhs) {
                return (int) (rhs.getTime() - lhs.getTime());
            }
        });
    }



}
