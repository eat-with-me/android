package com.example.win7.restapitest.screens.out_of_date_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

public class OutOfDateOrderActivity extends MyActivity implements OutOfDateOrderView {

    private OutOfDateOrderPresenter outOfDateOrderPresenter;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private OrderInGroup orderInGroup;

    private TextView restaurantName;
    private TextView closingTime;
    private TextView numberOfOrders;
    private TextView restaurantNumber;
    private TextView allOrdersCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_of_date_order);

        Intent intent = getIntent();
        orderInGroup = (OrderInGroup)intent.getSerializableExtra(OrdersInGroupActivity.ORDER);

        recyclerView = (RecyclerView) findViewById(R.id.list_of_purchasers);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        outOfDateOrderPresenter = new OutOfDateOrderPresenterImp(this);

        restaurantName = (TextView) findViewById(R.id.restaurant_name);

        closingTime = (TextView) findViewById(R.id.closing_time);

        numberOfOrders = (TextView) findViewById(R.id.number_of_orders);

        restaurantNumber = (TextView) findViewById(R.id.restaurant_number);

        allOrdersCost = (TextView) findViewById(R.id.total_price_all_orders);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.order_closed));
        setSupportActionBar(myToolbar);

        recycleViewInit();

        showProgress();

        outOfDateOrderPresenter.getOrderInfo(orderInGroup);



    }

    private void recycleViewInit(){

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public void showProgress() {

        relativeLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        relativeLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadOrder(OrderInGroup order) {


        RecyclerView.Adapter adapter = new PurchaserAdapter(order.getPurchasers());
        recyclerView.setAdapter(adapter);

        restaurantName.setText(order.getRestaurant().getName());
        closingTime.setText(order.getClosingTimeFormated());
        numberOfOrders.setText(order.getNumberOfPurchasers());
        restaurantNumber.setText(order.getRestaurant().getPhoneNumber());
        allOrdersCost.setText(order.getTotalPrice());
        //TODO owner
        //delivery cost

    }
}
