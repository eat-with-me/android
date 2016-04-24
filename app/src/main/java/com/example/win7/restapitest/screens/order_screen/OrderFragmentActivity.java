package com.example.win7.restapitest.screens.order_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.others.MyActivity;

;

/**
 * Created by Mateusz on 2016-04-19.
 */
public class OrderFragmentActivity extends MyActivity implements OrderView {

    private RecyclerView recyclerViewMyOrder;
    private RecyclerView.Adapter adapter;
    private TextView myOrderIsEmpty;
    private TextView otherOrderIsEmpty;
    private OrderPresenter orderPresenter;
    private Button bMyOrder;
    private Button bOtherOrders;

    private Order order;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        myOrderIsEmpty = (TextView) findViewById(R.id.my_empty_basket_text);
        otherOrderIsEmpty = (TextView) findViewById(R.id.other_empty_basket_text);
        recyclerViewMyOrder = (RecyclerView) findViewById(R.id.my_meals_in_order);
        bMyOrder = (Button) findViewById(R.id.bMyOrder);
        bOtherOrders = (Button) findViewById(R.id.bOtherOrders);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        FragmentManager fm1 = getSupportFragmentManager();
        addShowHideListener(R.id.bMyOrder, fm1.findFragmentById(R.id.my_order_fragment));

        FragmentManager fm2 = getSupportFragmentManager();
        addShowHideListener(R.id.bOtherOrders, fm2.findFragmentById(R.id.other_orders_fragment));

        orderPresenter = new OrderPresenterImp(this);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("order");

        adapter = new OrderAdapter(order);

        recyclerViewMyOrder.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMyOrder.setLayoutManager(layoutManager);
        recyclerViewMyOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMyOrder.setAdapter(adapter);


        if(order.getMeals().isEmpty()){
            setEmptyMyOrderView();
        }

    }

    @Override
    public void setEmptyMyOrderView() {
        recyclerViewMyOrder.setVisibility(View.GONE);
        myOrderIsEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyOtherOrderView() {

        otherOrderIsEmpty.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClickCleanMyOrder(View view) {

    }

    @Override
    public void showToast(String message) {

    }

    public void finish() {
        String response = "resp";
        Intent resultIntent = new Intent();
        resultIntent.putExtra(response,order);
        setResult(RESULT_OK, resultIntent);
        super.finish();
    }
    void addShowHideListener(int buttonId, final Fragment fragment) {
        final Button button = (Button)findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                ft.hide(fragment);
                if (fragment.isHidden()) {
                    ft.show(fragment);

                } else {
                    ft.hide(fragment);

                }
                ft.commit();
            }
        });
    }
}
