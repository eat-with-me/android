package com.example.win7.restapitest.screens.new_order_in_group_screen;

import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-20.
 */
public interface NewOrderInGroupView {

    void setEmptyView();

    void loadRestaurants(List<RestaurantMenu> restaurantsResult);

    void showProgress();

    void hideProgress();

    void showError();

    void recycleViewInit();

}
