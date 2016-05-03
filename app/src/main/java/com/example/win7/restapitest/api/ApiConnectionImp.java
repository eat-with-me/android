package com.example.win7.restapitest.api;


import android.util.Log;

import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.GroupName;
import com.example.win7.restapitest.model.LoginAnswer;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Purchaser;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiConnectionImp implements ApiConnection {

    private String cookie = "";


    //TODO zrobić coś z listami

    public static final String BASE_URL = "http://eat24.herokuapp.com/";

    private List<Group> groupsResult = new ArrayList<Group>();
    private List<OrderInGroup> ordersResult = new ArrayList<OrderInGroup>();
    private RestaurantMenu menuResult = new RestaurantMenu();
    private List<RestaurantMenu>  allRestaurantsMenuResult = new ArrayList<RestaurantMenu>();
    private List<Purchaser> purchasers = new ArrayList<Purchaser>();
    private Retrofit retrofit;
    private Endpoints api;

    public ApiConnectionImp(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.interceptors().add(interceptor);

        OkHttpClient client = builder.build();


         retrofit = new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(client)
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

                Log.d("Message from error",t.getMessage());
                listener.onError();

            }

        });

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
    public void getAllRestaurantsMenu(final OnDownloadFinishedListener listener) {
        Call<List<RestaurantMenu>> call = api.getAllRestaurantsMenu();
        call.enqueue(new Callback<List<RestaurantMenu>>(){
            @Override
            public void onResponse(Call<List<RestaurantMenu>> call, Response<List<RestaurantMenu>> response) {
                int statusCode = response.code();

                List<RestaurantMenu> allRestaurantResult = response.body();
                listener.onSuccess(allRestaurantResult);
            }

            @Override
            public void onFailure(Call<List<RestaurantMenu>> call, Throwable t) {
                listener.onError();
            }
        });

    }

    @Override
    public void sendPurchase(FinalOrder finalOrder, String groupId) {

        Call<FinalOrder> call = api.purchase(Integer.parseInt(groupId),finalOrder);
        call.enqueue((new Callback<FinalOrder>() {
            @Override
            public void onResponse(Call<FinalOrder> call, Response<FinalOrder> response) {

            }

            @Override
            public void onFailure(Call<FinalOrder> call, Throwable t) {

            }
        }));

    }

    @Override
    public void getPurchasers(String group_id, String order_id, final OnDownloadFinishedListener listener) {
        Call<OrderInGroup> call = api.getPurchasers(Integer.parseInt(group_id),Integer.parseInt(order_id));
        call.enqueue(new Callback<OrderInGroup>() {
            @Override
            public void onResponse(Call<OrderInGroup> call, Response<OrderInGroup> response) {

                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OrderInGroup> call, Throwable t) {
                listener.onError();
            }
        });



    }


    @Override
    public void login(final Credentials credentials, final OnLoginListener listener) {

        Call<LoginAnswer> call= api.login(credentials);
        call.enqueue(new Callback<LoginAnswer>() {

            @Override

            public void onResponse(Call<LoginAnswer> call, Response<LoginAnswer> response) {

                int statusCode = response.code();

                if (statusCode == 201) {

                    cookie = response.headers().get("Set-Cookie");

                    listener.onSuccess();
                } else if (statusCode == 401) {
                    listener.onWrongCredentials();
                } else {
                    //TODO for sure here should be something
                }

            }

            @Override

            public void onFailure(Call<LoginAnswer> call, Throwable t) {

                listener.onError();

            }

        });

    }

    @Override
    public void signUp(Credentials credentials, final OnLoginListener listener) {
        Call<LoginAnswer> call= api.signUp(credentials);
        call.enqueue(new Callback<LoginAnswer>() {

            @Override

            public void onResponse(Call<LoginAnswer> call, Response<LoginAnswer> response) {

                int statusCode = response.code();

                if (statusCode == 201) {

                    cookie = response.headers().get("Set-Cookie");
                    listener.onSuccess();

                } else if (statusCode == 422) {
                    listener.onWrongCredentials();

                } else {
                    //TODO for sure here should be something
                }

            }

            @Override

            public void onFailure(Call<LoginAnswer> call, Throwable t) {

                listener.onError();

            }

        });

    }


    @Override
    public void createNewGroup(GroupName groupName, final OnDownloadFinishedListener listener) {
        Call<Group> call= api.createNewGroup(groupName);
        call.enqueue(new Callback<Group>() {

            @Override

            public void onResponse(Call<Group> call, Response<Group> response) {

                    Group group = response.body();
                    listener.onSuccess(group);
            }

            @Override

            public void onFailure(Call<Group> call, Throwable t) {

                listener.onError();

            }

        });

    }

    @Override
    public void createNewOrder(OrderInGroup orderInGroup) {
        api.createNewOrder(orderInGroup,orderInGroup.getGroupId());
    }


    @Override
    public void closeSession() {
        //TODO close session on server
        cookie = "";
    }


    Interceptor interceptor = new Interceptor() {

        @Override

        public okhttp3.Response intercept(Chain chain) throws IOException {

            Request newRequest = chain.request().newBuilder().addHeader("cookie", cookie).build();

            return chain.proceed(newRequest);

        }

    };
}
