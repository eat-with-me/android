package com.example.win7.restapitest.screens.order_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.Purchaser;
import com.example.win7.restapitest.others.Factory;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-12.
 */
public class OrderPresenterImp implements OrderPresenter {

    private OrderView orderView;
    private ApiConnection apiConnection;
    private List<Purchaser> result;


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

    @Override
    public void onClickAccept(FinalOrder finalOrder, String groupId) {
        apiConnection.sendPurchase(finalOrder, groupId);
    }

    @Override
    public void getPurchasers(String group_ip, String order_id) {
        apiConnection.getPurchasers(group_ip,order_id,new OnDownloadFinishedListener<List<Purchaser>>() {
            @Override
            public void onSuccess(List<Purchaser> arg) {
//                result = arg;
                if(arg.isEmpty())
                {
                    orderView.setEmptyOtherOrderView();
                }
                else {
                    orderView.setEmptyOtherOrderView();
//                    orderView.setPurchasers(arg);
                }

            }

            @Override
            public void onError() {
                orderView.showToast("error");
            }
        });
    }
}
