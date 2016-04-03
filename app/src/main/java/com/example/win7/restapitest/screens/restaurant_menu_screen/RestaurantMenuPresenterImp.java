package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.Factory;

import java.text.DecimalFormat;

/**
 * Created by win7 on 02/04/2016.
 */
public class RestaurantMenuPresenterImp implements RestaurantMenuPresenter{


    private RestaurantMenuView restaurantMenuView;
    private ApiConnection apiConnection;
    private Order order;
    private RestaurantMenu menuResult = null;

    public RestaurantMenuPresenterImp(RestaurantMenuView restaurantMenuView) {
        this.restaurantMenuView = restaurantMenuView;
        this.apiConnection = Factory.getApiConnection();
        //TODO wyrzucić stąd kompozycje
        this.order = new Order();
    }

    @Override
    public void onClickMeal(int position) {

        Meal meal = menuResult.getMeals().get(position);
        order.add(meal);
        Double totalPrice = order.getTotalPrice();
        Integer totalProducts = order.getNumberOfOrderedMeals();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String totalPriceStr = decimalFormat.format(totalPrice);

        restaurantMenuView.setTotalPrice(totalPriceStr);
        restaurantMenuView.setTotalProducts(totalProducts.toString());
        restaurantMenuView.showToast("Dodano do koszyka");


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
