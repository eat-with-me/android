package com.example.win7.restapitest.screens.new_order_in_group_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.Factory;

import java.util.List;

/**
 * Created by Mateusz on 2016-05-02.
 */
public class NewOrderPresenterImpl implements NewOrderPresenter {

    ApiConnection apiConnection;
    NewOrderInGroupActivity newOrderInGroupActivity;
    List<RestaurantMenu> restaurantsResult = null;

    public NewOrderPresenterImpl(NewOrderInGroupActivity newOrderInGroupActivity) {
        this.newOrderInGroupActivity = newOrderInGroupActivity;
        this.apiConnection = Factory.getApiConnection();
    }

    @Override
    public void OnClickAccept(String groupId, int restaurantId, String time) {
        OrderInGroup newOrder = new OrderInGroup();
        newOrder.setGroupId(Integer.parseInt(groupId));
        newOrder.setRestaurantId(String.format("%d",restaurantId));
        newOrder.setClosingTime(time);
        apiConnection.createNewOrder(newOrder);


    }

    @Override
    public void getRestaurants() {

        apiConnection.getAllRestaurantsMenu(new OnDownloadFinishedListener<List<RestaurantMenu>>() {
            @Override
            public void onSuccess(List<RestaurantMenu> list) {
                restaurantsResult = list;
                newOrderInGroupActivity.hideProgress();

                if(restaurantsResult.isEmpty()){
                    newOrderInGroupActivity.setEmptyView();
                }
                else{

                    newOrderInGroupActivity.loadRestaurants(restaurantsResult);
                }
            }

            @Override
            public void onError() {
                newOrderInGroupActivity.showError();
            }
        });
    }
}
