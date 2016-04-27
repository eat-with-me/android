package com.example.win7.restapitest.screens.order_screen;

import com.example.win7.restapitest.model.FinalOrder;

/**
 * Created by Mateusz on 2016-04-12.
 */
public interface OrderPresenter {

    void onClickMeal(int position);

    void getGroupOrder(String groupId);

    void onClickCleanMyOrder();

    void onClickAccept(FinalOrder finalOrder, String groupId);

    void getPurchasers(String group_ip, String order_id);
}
