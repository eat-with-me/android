package com.example.win7.restapitest.screens.order_screen;

/**
 * Created by Mateusz on 2016-04-12.
 */
public interface OrderPresenter {

    void onClickMeal(int position);

    void getGroupOrder(String groupId);

    void onClickCleanMyOrder();
}
