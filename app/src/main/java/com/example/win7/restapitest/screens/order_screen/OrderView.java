package com.example.win7.restapitest.screens.order_screen;

import android.view.View;

/**
 * Created by Mateusz on 2016-04-12.
 */
public interface OrderView {

    void setEmptyMyOrderView();
    void setEmptyOtherOrderView();
    void onClickCleanMyOrder(View view);

    void showToast(String message);
}
