package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by win7 on 02/04/2016.
 */
public class RestaurantMenuPresenterImp implements RestaurantMenuPresenter{


    private RestaurantMenuView restaurantMenuView;
    private ApiConnection apiConnection;
    private RestaurantMenu menuResult = null;

    public RestaurantMenuPresenterImp(RestaurantMenuView restaurantMenuView) {
        this.restaurantMenuView = restaurantMenuView;
        this.apiConnection = Factory.getApiConnection();
    }

    @Override
    public void onClickMeal(int position) {

        Meal meal = menuResult.getMeals().get(position);
        String mealName = meal.getName();
        restaurantMenuView.showToast(mealName);

    }

    @Override
    public void getMenu(String restaurantId) {

        menuResult = apiConnection.getRestaurantMenu(restaurantId);


        if(menuResult.isEmpty()){
            restaurantMenuView.setEmptyView();
        }
        else{

            restaurantMenuView.loadMenu(menuResult);
        }
    }

    @Override
    public void onClickGoToCart() {

        restaurantMenuView.showToast("Go to cart is selected");

    }

    @Override
    public void onDestroy() {
        restaurantMenuView = null;
    }

    @Override
    public void onResume() {

    }
}
