package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.Order;

/**
 * Created by win7 on 02/04/2016.
 */
public interface RestaurantMenuPresenter {

    void onClickMeal(Meal otherMeal);

    void getMenu(String restaurantId);

    void hideButton();

    void onClickGoToCart();

    void onDestroy();

    void onResume();

    Order getOrder();

    void setOrder(Order response);

    void disableMenu();

    void enableMenu();
}
