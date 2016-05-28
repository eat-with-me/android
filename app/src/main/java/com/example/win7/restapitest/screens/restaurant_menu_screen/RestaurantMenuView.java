package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.view.View;

import com.example.win7.restapitest.model.MealCategory;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by win7 on 02/04/2016.
 */
public interface RestaurantMenuView {

    void setEmptyView();

    void loadMenu(List<MealCategory> menu);

    void showToast(String message);

    void onClickGoToCart(View view);

    void setTotalPrice(String price);

    void setTotalProducts(String products);

    void showProgress();

    void hideProgress();

    void showError();

    void navigateToOrderActivity();

    void showAlertDialog();

    void hideButton();

    void showPictureDialog(String mealsName);

    void loadPicture(String url);
}
