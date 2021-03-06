package com.example.win7.restapitest.screens.orders_in_group_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Orders;
import com.example.win7.restapitest.model.User;
import com.example.win7.restapitest.others.Factory;

import java.util.List;


public class OrdersInGroupPresenterImp implements OrdersInGroupPresenter {

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

        if(order.isActual()){

            ordersInGroupView.goToRestaurantMenuActivity(order);
        }
        else{
            ordersInGroupView.navigateToOutOfDateOrderActivity(order);
        }





    }

    @Override
    public void getOrders(String groupId) {

        apiConnection.getOrdersInGroup(groupId, new OnDownloadFinishedListener<List<OrderInGroup>>() {
            @Override
            public void onSuccess(List<OrderInGroup> list) {
                ordersResult = list;

                if(ordersResult.isEmpty()){
                    ordersInGroupView.setEmptyView();

                }
                else{

                    Orders orders = new Orders(ordersResult);
                    orders.sortDate();
                    ordersInGroupView.loadOrders(orders);
                }
                ordersInGroupView.hideProgress();
            }

            @Override
            public void onError() {
                ordersInGroupView.showAlertDialog();
            }
        });
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
    public void onClickPersonsInGroup() {
        Group group = ordersInGroupView.getGroup();
        List<User> users = group.getUsers();

        if(users.size() == 1){

            ordersInGroupView.showToast("Jesteś jednyną osobą w tej grupie");
        }
        else{

            ordersInGroupView.navigateToPersonsInGroupActivity();
        }

    }

    @Override
    public void onClickAddPersonToGroup() {

        ordersInGroupView.showLinkDialog();

    }

}
