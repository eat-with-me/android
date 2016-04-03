package com.example.win7.restapitest.api;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by win7 on 28/03/2016.
 */
public interface ApiConnection {

    List<Group> getGroups();

    List<OrderInGroup> getOrdersInGroup(String groupNumber);

    RestaurantMenu getRestaurantMenu(String restaurantId);

    List<RestaurantMenu>getAllRestaurantsMenu();
}
