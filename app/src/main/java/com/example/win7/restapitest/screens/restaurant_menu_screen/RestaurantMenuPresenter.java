package com.example.win7.restapitest.screens.restaurant_menu_screen;

/**
 * Created by win7 on 02/04/2016.
 */
public interface RestaurantMenuPresenter {
    void onClickMeal(int position);

    void getMenu(String restaurantId);

    void onClickGoToCart();

    void onDestroy();

    void onResume();
}
