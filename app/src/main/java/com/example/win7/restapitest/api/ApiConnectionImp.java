package com.example.win7.restapitest.api;


import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Restaurant;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win7 on 28/03/2016.
 */
public class ApiConnectionImp implements ApiConnection {




    //TODO zrobić coś z listami

    private final String BASE_URL = "http://eat24.herokuapp.com/";

    private List<Group> groupsResult = new ArrayList<Group>();
    private List<OrderInGroup> ordersResult = new ArrayList<OrderInGroup>();
    private RestaurantMenu menuResult = new RestaurantMenu();
    private List<RestaurantMenu>  allRestaurantsMenuResult = new ArrayList<RestaurantMenu>();

    private Retrofit retrofit;
    private Endpoints api;

    public ApiConnectionImp(){

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(Endpoints.class);
    }


    @Override
    public void getGroups(final OnDownloadFinishedListener listener)
    {
        Call<List<Group>> call = api.getGroups();
        call.enqueue(new Callback<List<Group>>() {

            @Override

            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {

                int statusCode = response.code();

                List<Group> groups = response.body();
                listener.onSuccess(groups);
            }


            @Override

            public void onFailure(Call<List<Group>> call, Throwable t) {

                listener.onError();

            }

        });



        //mockGroups();

    }

    @Override
    public void getOrdersInGroup(String groupNumber, final OnDownloadFinishedListener listener) {


        Call<List<OrderInGroup>> call = api.getOrdersInGroups(groupNumber);
        call.enqueue(new Callback<List<OrderInGroup>>() {

            @Override

            public void onResponse(Call<List<OrderInGroup>> call, Response<List<OrderInGroup>> response) {

                int statusCode = response.code();

                List<OrderInGroup> order = response.body();
                listener.onSuccess(order);
            }


            @Override

            public void onFailure(Call<List<OrderInGroup>> call, Throwable t) {

                listener.onError();

            }

        });

        //mockOrders();
    }

    @Override
    public void getRestaurantMenu(String restaurantId, final OnDownloadFinishedListener listener) {


        Call<RestaurantMenu> call= api.getRestaurantMenu(restaurantId);
        call.enqueue(new Callback<RestaurantMenu>() {

            @Override

            public void onResponse(Call<RestaurantMenu> call, Response<RestaurantMenu> response) {

                int statusCode = response.code();

                RestaurantMenu menu = response.body();
                listener.onSuccess(menu);
            }


            @Override

            public void onFailure(Call<RestaurantMenu> call, Throwable t) {

                listener.onError();

            }

        });


    }

    @Override
    public void getAllRestaurantsMenu() {

    }

    @Override
    public void login(String email, String password) {

    }


    //************************************************************************************************

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

//    private void mockMenu()
//    {
//        menuResult = new RestaurantMenu();
//        menuResult.setName("KFC");
//        menuResult.setCreatedAt("cerated time");
//        menuResult.setId(3);
//        menuResult.setPhoneNumber("345345");
//        menuResult.setUpdatedAt("updatedAt");
//
//
//        Meal meal = new Meal();
//        meal.setName("ziemniory");
//        meal.setPrice(3.45);
//        menuResult.getMeals().add(meal);
//
//        meal = new Meal();
//        meal.setName("Schabowy");
//        meal.setPrice(5.57);
//        menuResult.getMeals().add(meal);
//
//        meal = new Meal();
//        meal.setName("surówka");
//        meal.setPrice(9.56);
//        menuResult.getMeals().add(meal);
//
//    }

}
