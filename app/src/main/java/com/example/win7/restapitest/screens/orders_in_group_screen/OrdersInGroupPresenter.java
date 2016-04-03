package com.example.win7.restapitest.screens.orders_in_group_screen;


public interface OrdersInGroupPresenter {

    void onClickOrder(int position);

    void getOrders(String groupId);

    void onClickNewOrder();

    void onDestroy();

    void onResume();
}
