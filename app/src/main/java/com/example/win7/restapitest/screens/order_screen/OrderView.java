package com.example.win7.restapitest.screens.order_screen;

import android.view.View;

import com.example.win7.restapitest.model.Purchaser;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-12.
 */
public interface OrderView {

    void setEmptyMyOrderView();
    void setEmptyOtherOrderView();
    void onClickAcceptOrder(View view);
    public void setPurchasers(List<Purchaser> purchasers);
    void showToast(String message);
}
