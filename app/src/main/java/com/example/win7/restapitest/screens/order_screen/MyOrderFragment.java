package com.example.win7.restapitest.screens.order_screen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Order;

/**
 * Created by Mateusz on 2016-04-19.
 */
public class MyOrderFragment extends Fragment {

    RecyclerView recyclerViewMyOrder;
    Order order;
    OrderAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_order_view, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}