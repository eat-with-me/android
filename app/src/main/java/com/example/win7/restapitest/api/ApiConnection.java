package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Credentials;

/**
 * Created by win7 on 28/03/2016.
 */
public interface ApiConnection {

    void getGroups(final OnDownloadFinishedListener listener);

    void getOrdersInGroup(String groupNumber, final OnDownloadFinishedListener listener);

    void getRestaurantMenu(String restaurantId, final OnDownloadFinishedListener listener);

    void getAllRestaurantsMenu();

    void login(Credentials credentials, final OnLoginListener listener);

    void signUp(Credentials credentials, final OnLoginListener listener);
}
