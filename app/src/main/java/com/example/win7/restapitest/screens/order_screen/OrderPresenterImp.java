package com.example.win7.restapitest.screens.order_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by Mateusz on 2016-04-12.
 */
public class OrderPresenterImp implements OrderPresenter {

    private OrderView orderView;
    private ApiConnection apiConnection;

    public OrderPresenterImp(OrderView orderView) {
        this.orderView = orderView;
        this.apiConnection = Factory.getApiConnection();

    }
    @Override
    public void onClickMeal(int position) {

    }

    @Override
    public void getGroupOrder(String groupId) {

    }

    @Override
    public void onClickCleanMyOrder() {

    }
}
