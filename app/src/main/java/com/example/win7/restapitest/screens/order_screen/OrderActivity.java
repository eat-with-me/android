package com.example.win7.restapitest.screens.order_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Mateusz on 2016-04-12.
 */
public class OrderActivity extends AppCompatActivity implements OrderView {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private OrderPresenter orderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        messageTextView = (TextView) findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_meals_in_order);

        orderPresenter = new OrderPresenterImp(this);
        Intent intent = getIntent();
        ArrayList<Meal> meals = intent.getParcelableArrayListExtra("meals");

        adapter = new OrderAdapter(meals);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                orderPresenter.onClickMeal(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        setEmptyView();
    }

    @Override
    public void setEmptyView(){
//        recyclerView.setVisibility(View.GONE);
        messageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickCleanMyOrder() {

    }

    public void onClickCleanMyOrder(View view) {
        orderPresenter.onClickCleanMyOrder();
    }
}
