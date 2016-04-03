package com.example.win7.restapitest.screens.orders_in_group_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.Factory;

import java.util.List;

/**
 * Created by win7 on 01/04/2016.
 */
public class OrdersInGroupPresenterImp implements OrdersInGroupPresenter,OnDownloadFinishedListener<List<OrderInGroup>> {

    private OrdersInGroupView ordersInGroupView;
    private ApiConnection apiConnection;

    List<OrderInGroup> ordersResult = null;

    public OrdersInGroupPresenterImp(OrdersInGroupView ordersInGroupView) {
        this.ordersInGroupView = ordersInGroupView;
        this.apiConnection = Factory.getApiConnection();

    }


    @Override
    public void onClickOrder(int position) {

        OrderInGroup order = ordersResult.get(position);
        String orderId = order.getId();
        ordersInGroupView.goToRestaurantMenuActivity(orderId);

    }

    @Override
    public void getOrders(String groupId) {

        apiConnection.getOrdersInGroup(groupId,this);
    }

    @Override
    public void onClickNewOrder() {
        ordersInGroupView.showToast("New Order is selected");

    }

    @Override
    public void onDestroy() { ordersInGroupView = null;   }

    @Override
    public void onResume() {

    }


    @Override
    public void onSuccess(List<OrderInGroup> list) {

        ordersResult = list;
        ordersInGroupView.hideProgress();

        if(ordersResult.isEmpty()){
            ordersInGroupView.setEmptyView();
        }
        else{

            ordersInGroupView.loadOrders(ordersResult);
        }

    }

    @Override
    public void onError() {
        ordersInGroupView.showError();
    }
}
