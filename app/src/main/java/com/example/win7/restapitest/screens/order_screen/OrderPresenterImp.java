package com.example.win7.restapitest.screens.order_screen;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.OrderInGroup;
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
    public void onClickAccept(FinalOrder finalOrder, String groupId) {

        apiConnection.sendPurchase(finalOrder, groupId, new OnDownloadFinishedListener<FinalOrder>() {
            @Override
            public void onSuccess(FinalOrder arg) {
                orderView.getPurchasers();

                orderView.initDialog();
            }

            @Override
            public void onError() {

                orderView.showAlertDialog();

            }
        });
    }

    @Override
    public void getPurchasers(String group_ip, String order_id) {
        apiConnection.getPurchasers(group_ip,order_id,new OnDownloadFinishedListener<OrderInGroup>() {
            @Override
            public void onSuccess(OrderInGroup arg) {
                if(!arg.getPurchasers().isEmpty())
                {
                    orderView.setPurchasers(arg.getPurchasers());
                }
                else {
                    orderView.setEmptyOtherOrderView();

                }

            }

            @Override
            public void onError() {
                orderView.showAlertDialog();
            }
        });
    }
}
