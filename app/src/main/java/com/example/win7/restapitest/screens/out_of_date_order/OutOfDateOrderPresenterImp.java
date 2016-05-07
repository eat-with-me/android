package com.example.win7.restapitest.screens.out_of_date_order;

import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.Factory;

/**
 * Created by win7 on 07/05/2016.
 */
public class OutOfDateOrderPresenterImp implements OutOfDateOrderPresenter {

    private OutOfDateOrderView outOfDateOrderView;
    private ApiConnection apiConnection = Factory.getApiConnection();

    public OutOfDateOrderPresenterImp(OutOfDateOrderView outOfDateOrderView) {

        this.outOfDateOrderView = outOfDateOrderView;
    }

    @Override
    public void getOrderInfo(OrderInGroup orderInGroup) {

        apiConnection.getPurchasers(orderInGroup.getGroupId(),orderInGroup.getId(),new OnDownloadFinishedListener<OrderInGroup>() {
            @Override
            public void onSuccess(OrderInGroup order) {

                outOfDateOrderView.loadOrder(order);

            }

            @Override
            public void onError() {
                outOfDateOrderView.showAlertDialog();
            }
        });

    }
}
