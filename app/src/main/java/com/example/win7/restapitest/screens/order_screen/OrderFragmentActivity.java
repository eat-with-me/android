package com.example.win7.restapitest.screens.order_screen;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.Purchase;
import com.example.win7.restapitest.model.Purchaser;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.List;

;

/**
 * Created by Mateusz on 2016-04-19.
 */
public class OrderFragmentActivity extends AppCompatActivity implements OrderView {

    private RecyclerView recyclerViewMyOrder;
    private RecyclerView recyclerViewOtherOrder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter otherOrdersAdapter;
    private TextView myOrderIsEmpty;
    private TextView otherOrderIsEmpty;
    private OrderPresenter orderPresenter;
    private Button bMyOrder;
    private Button bOtherOrders;
    private Button bAccept;
    private String groupId;
    private String restaurantId;
    private String orderId;
    private Order order;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("order");
        restaurantId = intent.getStringExtra(OrdersInGroupActivity.RESTAURANT_ID);
        groupId = intent.getStringExtra(OrdersInGroupActivity.GROUP_ID);
        orderId = intent.getStringExtra(OrdersInGroupActivity.ORDER_ID);

        orderPresenter = new OrderPresenterImp(this);
        adapter = new OrderAdapter(order,getBaseContext());
        myOrderIsEmpty = (TextView) findViewById(R.id.my_empty_basket_text);
        otherOrderIsEmpty = (TextView) findViewById(R.id.other_empty_basket_text);
        bOtherOrders = (Button) findViewById(R.id.bOtherOrders);
        bAccept = (Button) findViewById(R.id.bAccept);

        recyclerViewOtherOrder = (RecyclerView) findViewById(R.id.other_meals_in_order);

        FragmentManager fm2 = getSupportFragmentManager();
        Fragment otherOrdersFragment = fm2.findFragmentById(R.id.other_orders_fragment);
        FragmentTransaction trans = fm2.beginTransaction();
        trans.hide(otherOrdersFragment).commit();

        addShowHideListener(R.id.bMyOrder, R.id.bOtherOrders, fm2.findFragmentById(R.id.my_order_fragment), fm2.findFragmentById(R.id.other_orders_fragment));

        recyclerViewInit();
        getPurchasers();
    }

    public Order getOrder()
    {
        return this.order;
    }

    @Override
    public void setEmptyMyOrderView() {
        recyclerViewMyOrder.setVisibility(View.GONE);
        myOrderIsEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyOtherOrderView() {
        otherOrderIsEmpty.setVisibility(View.VISIBLE);
        recyclerViewOtherOrder.setVisibility(View.GONE);

    }
    public void getPurchasers(){
        orderPresenter.getPurchasers(groupId,orderId);

    }


    @Override
    public void onClickAcceptOrder(View view) {


        Purchase purchase = new Purchase(Integer.parseInt(orderId),order.getMeals());
        FinalOrder finalOrder = new FinalOrder(purchase);
        orderPresenter.onClickAccept(finalOrder,groupId);
        getPurchasers();
    }

    @Override
    public void setPurchasers(List<Purchaser> purchasers) {

        otherOrdersAdapter = new PurchaserAdapter(purchasers);
        recyclerViewOtherOrder.setAdapter(otherOrdersAdapter);

        otherOrderIsEmpty.setVisibility(View.GONE);
        recyclerViewOtherOrder.setVisibility(View.VISIBLE);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void finish() {
        String response = "resp";
        Intent resultIntent = new Intent();
        resultIntent.putExtra(response,order);
        setResult(RESULT_OK, resultIntent);
        super.finish();
    }
    void addShowHideListener(int buttonMyOrder, int buttonOtherOrder, final Fragment myOrderFragment, final Fragment otherOrdersFragment) {
        final Button button = (Button)findViewById(buttonMyOrder);
        final Button button2 = (Button)findViewById(buttonOtherOrder);
        if (button != null && button2 != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                    ft.hide(otherOrdersFragment);
                    if (myOrderFragment.isHidden()) {
                        ft.show(myOrderFragment);
                    }

                    ft.commit();
                }
            });
            button2.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                    ft.hide(myOrderFragment);
                    if (otherOrdersFragment.isHidden()) {
                        ft.show(otherOrdersFragment);
                    }
                    ft.commit();

                }
            });
        }
    }
    public void recyclerViewInit(){
        recyclerViewMyOrder = (RecyclerView) findViewById(R.id.my_meals_in_order);
        recyclerViewMyOrder.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMyOrder.setLayoutManager(layoutManager);
        recyclerViewMyOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMyOrder.setAdapter(adapter);
    }
}
