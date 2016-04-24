package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Credentials;
import com.example.win7.restapitest.model.Purchase;

/**
 * Created by win7 on 28/03/2016.
 */
public interface ApiConnection {

    void getGroups(final OnDownloadFinishedListener listener);

    void getOrdersInGroup(String groupNumber, final OnDownloadFinishedListener listener);

    void getRestaurantMenu(String restaurantId, final OnDownloadFinishedListener listener);

    void getAllRestaurantsMenu(final OnDownloadFinishedListener listener);

    void sendPurchase(Purchase purchase, String groupId);

    void getPurchasers(String group_id, String order_id, final OnDownloadFinishedListener listener);

    void login(Credentials credentials, final OnLoginListener listener);

    void signUp(Credentials credentials, final OnLoginListener listener);
}
