package com.example.win7.restapitest.screens.orders_in_group_screen;

/**
 * Created by win7 on 01/04/2016.
 */
public interface OrdersInGroupPresenter {

    void onClickOrder(int position);

    void getOrders(String groupId);

    void onClickNewOrder();

    void onDestroy();

    void onResume();
}