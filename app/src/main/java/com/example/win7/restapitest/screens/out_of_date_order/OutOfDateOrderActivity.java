package com.example.win7.restapitest.screens.out_of_date_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Purchaser;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.main_screen.GroupsAdapter;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.List;

public class OutOfDateOrderActivity extends MyActivity implements OutOfDateOrderView {

    private OutOfDateOrderPresenter outOfDateOrderPresenter;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private OrderInGroup orderInGroup;

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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
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

        String restaurantName = order.getRestaurant().getName();
        String closingTime = order.getClosingTimeFormated();
        String numberOfOrders = order.getNumberOfPurchasers();
        String restaurantNumber = order.getRestaurant().getPhoneNumber();
        String totalPrice = order.getTotalPrice();
        //TODO owner
        //delivery cost





    }
}
