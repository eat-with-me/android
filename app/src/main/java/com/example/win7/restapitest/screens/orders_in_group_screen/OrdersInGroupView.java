package com.example.win7.restapitest.screens.orders_in_group_screen;

import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;

/**
 * Created by win7 on 01/04/2016.
 */
public interface OrdersInGroupView {
    void setEmptyView();

    void loadOrders(List<OrderInGroup> ordersResult);

    void showToast(String message);

    void goToRestaurantMenuActivity(String restaurantId);

    void showProgress();

    void hideProgress();

    void showError();
}
