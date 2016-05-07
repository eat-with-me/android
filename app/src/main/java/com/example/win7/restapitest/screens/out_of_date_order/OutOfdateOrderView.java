package com.example.win7.restapitest.screens.out_of_date_order;

import com.example.win7.restapitest.model.OrderInGroup;

/**
 * Created by win7 on 07/05/2016.
 */
public interface OutOfDateOrderView {


    void showProgress();

    void hideProgress();

    void showAlertDialog();

    void loadOrder(OrderInGroup order);
}
