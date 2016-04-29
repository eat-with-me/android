package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.Factory;

import java.text.DecimalFormat;

/**
 * Created by win7 on 02/04/2016.
 */
public class RestaurantMenuPresenterImp implements RestaurantMenuPresenter {


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

        Meal meal = new Meal(menuResult.getMeals().get(position));
        boolean jest = false;
        if (!order.getMeals().isEmpty()) {


                for (int i = 0; i < order.getMeals().size(); i++) {
                    if (order.getMeals().get(i).getId().equals(meal.getId())) {
                        order.getMeals().get(i).incAmount();
                        order.incTotalPrice(meal.getPrice());
                        order.incNumberOfProducts();
                        restaurantMenuView.showToast("Dodano do koszyka " + menuResult.getMeals().get(position).getId());
                        jest = true;
                    }
                }
                if(!jest)
                {
                    order.add(meal);
                    restaurantMenuView.showToast("Dodano do koszyka " + menuResult.getMeals().get(position).getId());
                }




        } else {

            order.add(meal);
            restaurantMenuView.showToast("Dodano do koszyka " + menuResult.getMeals().get(position).getId());
        }

        setTotalPriceAndNumberOfProducts();


    }

    public void setTotalPriceAndNumberOfProducts() {
        Double totalPrice = order.getTotalPrice();
        Integer totalProducts = order.getNumberOfProducts();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String totalPriceStr = decimalFormat.format(totalPrice);

        restaurantMenuView.setTotalPrice(totalPriceStr);
        restaurantMenuView.setTotalProducts(totalProducts.toString());
    }

    @Override
    public void getMenu(String restaurantId) {

        apiConnection.getRestaurantMenu(restaurantId, new OnDownloadFinishedListener<RestaurantMenu>() {
            @Override
            public void onSuccess(RestaurantMenu arg) {
                menuResult = arg;
                restaurantMenuView.hideProgress();

                if (menuResult.isEmpty()) {
                    restaurantMenuView.setEmptyView();
                } else {

                    restaurantMenuView.loadMenu(menuResult);
                }
            }

            @Override
            public void onError() {
                restaurantMenuView.showError();
            }
        });


    }

    @Override
    public void onClickGoToCart() {

        restaurantMenuView.navigateToOrderActivity();

    }

    @Override
    public void onDestroy() {
        restaurantMenuView = null;
    }

    @Override
    public void onResume() {

        setTotalPriceAndNumberOfProducts();

    }

    @Override
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
