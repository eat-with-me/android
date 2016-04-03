package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by win7 on 02/04/2016.
 */
public interface RestaurantMenuView {
    void setEmptyView();

    public void loadMenu(RestaurantMenu menuResult);

    void showToast(String message);


    void setTotalPrice(String price);

    void setTotalProducts(String products);

    void showProgress();

    void hideProgress();

    void showError();
}
