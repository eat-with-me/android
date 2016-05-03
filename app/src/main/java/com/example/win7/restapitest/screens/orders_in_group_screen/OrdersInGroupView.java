package com.example.win7.restapitest.screens.orders_in_group_screen;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;


public interface OrdersInGroupView {

    Group getGroup();

    void setGroup(Group group);

    void setEmptyView();

    void loadOrders(List<OrderInGroup> ordersResult);

    void showToast(String message);

    void goToRestaurantMenuActivity(String restaurantId,String restaurantName);

    void showProgress();

    void hideProgress();

    void showError();

    void recycleViewInit();

    void showAlertDialog();

    void navigateToPersonsInGroupActivity();
}
