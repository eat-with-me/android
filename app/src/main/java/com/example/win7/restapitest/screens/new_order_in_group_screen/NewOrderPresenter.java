package com.example.win7.restapitest.screens.new_order_in_group_screen;

import com.example.win7.restapitest.model.NewOrderInGroup;

/**
 * Created by Mateusz on 2016-05-02.
 */
public interface NewOrderPresenter {

    public void OnClickAccept(String groupId, NewOrderInGroup newOrderInGroup);

    void getRestaurants();
}
