package com.example.win7.restapitest.api;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Restaurant;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public class ApiConnectionImp implements ApiConnection {

    //TODO zrobić coś z listami

    private final String URL = "http://56c79c98.ngrok.io";
    private List<Group> groupsResult = new ArrayList<Group>();
    private List<OrderInGroup> ordersResult = new ArrayList<OrderInGroup>();
    private RestaurantMenu menuResult = new RestaurantMenu();
    private List<RestaurantMenu>  allRestaurantsMenuResult = new ArrayList<RestaurantMenu>();

    private RestAdapter adapter;
    private Api api;

    public ApiConnectionImp(){
        adapter = new RestAdapter.Builder().setEndpoint(URL).build();
        api = adapter.create(Api.class);
    }


    @Override
    public List<Group> getGroups()
    {
        api.getGroups(new Callback<List<Group>>() {
            @Override
            public void success(List<Group> groupResponse, Response response) {
                groupsResult = groupResponse;
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO obsłużyć brak połączenia z internetem
            }
        });

        //mockGroups();
        return groupsResult;
    }

    @Override
    public List<OrderInGroup> getOrdersInGroup(String groupNumber) {
        api.getOrdersInGroups(groupNumber, new Callback<List<OrderInGroup>>() {

            @Override
            public void success(List<OrderInGroup> orderResponse, Response response) {
                ordersResult = orderResponse;
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO obsłużyć brak połączenia z internetem
            }
        });

        //mockOrders();
        return ordersResult;
    }

    @Override
    public RestaurantMenu getRestaurantMenu(String restaurantId) {
//        api.getRestaurantMenu(restaurantId, new Callback<RestaurantMenu>() {
//
//            @Override
//            public void success(RestaurantMenu menuResponse, Response response) {
//                menuResult = menuResponse;
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                //TODO obsłużyć brak połączenia z internetem
//            }
//        });
        mockMenu();
        return menuResult;
    }

    @Override
    public List<RestaurantMenu> getAllRestaurantsMenu() {
//        api.getAllRestaurantsMenu(new Callback<List<RestaurantMenu>>() {
//
//            @Override
//            public void success(List<RestaurantMenu> allRestaurantsMenuResponse, Response response) {
//                allRestaurantsMenuResult = allRestaurantsMenuResponse;
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                //TODO obsłużyć brak połączenia z internetem
//            }
//        });

        mockMenu();
        return allRestaurantsMenuResult;
    }


    private void mockGroups(){

        //groupsResult = new ArrayList<Group>();

        Group group = new Group();
        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

        group.setName("Praca");

        groupsResult.add(group);

        group = new Group();
        group.setName("Akademik");

        groupsResult.add(group);

        group = new Group();
        group.setName("Praca2");

        groupsResult.add(group);

        group = new Group();
        group.setName("Szkoła");

        groupsResult.add(group);

        group = new Group();
        group.setName("Biuro");

        groupsResult.add(group);

    }

    private void mockOrders(){

        ordersResult = new ArrayList<OrderInGroup>();

        Restaurant restaurant = new Restaurant();
        restaurant.setName("restauracja1");

        OrderInGroup orderInGroup = new OrderInGroup();
        orderInGroup.setRestaurant(restaurant);
        orderInGroup.setClosingTime("14:45");

        ordersResult.add(orderInGroup);


        restaurant = new Restaurant();
        restaurant.setName("restauracja2");

        orderInGroup = new OrderInGroup();
        orderInGroup.setRestaurant(restaurant);
        orderInGroup.setClosingTime("19:45");

        ordersResult.add(orderInGroup);


        restaurant = new Restaurant();
        restaurant.setName("restauracja2");

        orderInGroup = new OrderInGroup();
        orderInGroup.setRestaurant(restaurant);
        orderInGroup.setClosingTime("06:55");

        ordersResult.add(orderInGroup);

    }

    private void mockMenu()
    {
        menuResult = new RestaurantMenu();
        menuResult.setName("KFC");
        menuResult.setCreatedAt("cerated time");
        menuResult.setId(3);
        menuResult.setPhoneNumber("345345");
        menuResult.setUpdatedAt("updatedAt");


        Meal meal = new Meal();
        meal.setName("ziemniory");
        meal.setPrice(3.45);
        menuResult.getMeals().add(meal);

        meal = new Meal();
        meal.setName("Schabowy");
        meal.setPrice(5.57);
        menuResult.getMeals().add(meal);

        meal = new Meal();
        meal.setName("surówka");
        meal.setPrice(9.56);
        menuResult.getMeals().add(meal);

    }

}
