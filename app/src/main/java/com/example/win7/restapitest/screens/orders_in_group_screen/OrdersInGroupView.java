package com.example.win7.restapitest.screens.orders_in_group_screen;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Orders;

import java.util.List;


public interface OrdersInGroupView {

    Group getGroup();

    void setGroup(Group group);

    void setEmptyView();

    void showToast(String message);

    void loadOrders(Orders orders);

    void goToRestaurantMenuActivity(String restaurantId, String restaurantName, String orderId);

    void showProgress();

    void hideProgress();

    void showError();

    void recycleViewInit();

    void showAlertDialog();

    void navigateToPersonsInGroupActivity();

    void showLinkDialog();

    void navigateToOutOfDateOrderAcivity();
}
