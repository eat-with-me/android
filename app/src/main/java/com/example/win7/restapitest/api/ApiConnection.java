package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public interface ApiConnection {

    void getGroups(final OnDownloadFinishedListener listener);

    void getOrdersInGroup(String groupNumber, final OnDownloadFinishedListener listener);

    void getRestaurantMenu(String restaurantId, final OnDownloadFinishedListener listener);

    void getAllRestaurantsMenu();

    void login(String email, String password);
}
