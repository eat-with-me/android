package com.example.win7.restapitest.screens.order_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Order;

/**
 * Created by Mateusz on 2016-04-19.
 */
public class OtherOrderFragment extends Fragment {

    RecyclerView recyclerViewMyOrder;
    Order order;
    OrderAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.other_orders_view, container, false);
        //TODO
//        order = new Order();
//        for(int i=0; i<7; i++){
//            String imie = "TODO";
//
//            Meal meal = new Meal(imie,10.50);
//            order.add(meal);
//        }
//
//        adapter = new OrderAdapter(order,getActivity());

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewMyOrder = (RecyclerView) getView().findViewById(R.id.other_meals_in_order);
        recyclerViewMyOrder.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMyOrder.setLayoutManager(layoutManager);
        recyclerViewMyOrder.setItemAnimator(new DefaultItemAnimator());



    }
}