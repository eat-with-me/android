package com.example.win7.restapitest.screens.restaurant_menu_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.MealCategory;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.Factory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
        meal.setMeal_id(menuResult.getMeals().get(position).getId());
        boolean jest = false;
        if (!order.getMeals().isEmpty()) {


                for (int i = 0; i < order.getMeals().size(); i++) {
                    if (order.getMeals().get(i).getId().equals(meal.getId())) {
                        order.getMeals().get(i).incAmount();
                        order.incTotalPrice(meal.getPrice());

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
                    ArrayList<MealCategory> menu = createCorrectMenu(menuResult);
                    restaurantMenuView.loadMenu(menu);
                }
            }

            @Override
            public void onError() {
                restaurantMenuView.showAlertDialog();
            }
        });


    }

    //TODO move this function from here
    private ArrayList<MealCategory> createCorrectMenu(RestaurantMenu menuResult) {

        List<MealCategory> correctMenu = new ArrayList<>();
        List<Meal> meals = menuResult.getMeals();
        boolean found = false;

        for(Meal meal : meals){

           int categoryId = meal.getMealType().getId();

            for(MealCategory mealCategory : correctMenu){

                found = false;

                if(mealCategory.getId() == categoryId){

                    mealCategory.getMeals().add(meal);
                    found = true;
                    break;
                }

            }

            if(!found){

                MealCategory category = new MealCategory();
                category.setId(categoryId);
                category.setName(meal.getMealType().getName());
                category.getMeals().add(meal);
                correctMenu.add(category);
            }

        }

        return (ArrayList<MealCategory>) correctMenu;

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
