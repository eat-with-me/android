package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.view.View;

import com.example.win7.restapitest.model.RestaurantMenu;

/**
 * Created by win7 on 02/04/2016.
 */
public interface RestaurantMenuView {
    void setEmptyView();

    void loadMenu(RestaurantMenu menuResult);

    void showToast(String message);

    void onClickGoToCart(View view);

    void setTotalPrice(String price);

    void setTotalProducts(String products);

    void showProgress();

    void hideProgress();

    void showError();

    void navigateToOrderActivity();
}
