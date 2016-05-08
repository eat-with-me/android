package com.example.win7.restapitest.screens.order_screen;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.FinalOrder;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Purchase;
import com.example.win7.restapitest.model.Purchaser;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;
import com.example.win7.restapitest.screens.out_of_date_order.OutOfDateOrderActivity;
import com.example.win7.restapitest.screens.restaurant_menu_screen.RestaurantMenuActivity;

import java.util.List;

;

/**
 * Created by Mateusz on 2016-04-19.
 */
public class OrderFragmentActivity extends MyActivity implements OrderView {

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
    private OrderInGroup orderInGroup;
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("order");

        orderInGroup = (OrderInGroup)intent.getSerializableExtra(RestaurantMenuActivity.ORDER);
        restaurantId = orderInGroup.getRestaurantId();
        groupId = intent.getStringExtra(OrdersInGroupActivity.GROUP_ID);
        orderId = orderInGroup.getId();

        orderPresenter = new OrderPresenterImp(this);
        adapter = new OrderAdapter(order,getBaseContext());
        myOrderIsEmpty = (TextView) findViewById(R.id.my_empty_basket_text);
        otherOrderIsEmpty = (TextView) findViewById(R.id.other_empty_basket_text);
        bOtherOrders = (Button) findViewById(R.id.bOtherOrders);
        bAccept = (Button) findViewById(R.id.bAccept);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
        Log.d("getPurchasers", "groupId: " + groupId + " orderId: " + orderId);
        orderPresenter.getPurchasers(groupId,orderId);

    }


    @Override
    public void onClickAcceptOrder(View view) {

        if(orderInGroup.isActual()){

            if(order.getNumberOfProducts()!=0 )
            {

                Purchase purchase = new Purchase(orderId,order.getMeals());
                FinalOrder finalOrder = new FinalOrder(purchase);
                orderPresenter.onClickAccept(finalOrder,groupId);
            }
            else
            {
                showToast("Najpierw wybierz posiłki");
            }
        }
        else
        {

            navigateToOutOfDateOrderActivity();
        }

    }
    public void navigateToOutOfDateOrderActivity() {
        Intent intent = new Intent(this, OutOfDateOrderActivity.class);
        intent.putExtra(OrdersInGroupActivity.ORDER,orderInGroup);
        startActivity(intent);
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
    @Override
    public void navigateToMainActivity()
    {

        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void initDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String message = "Wróć lub zakończ i przejdź do wyboru grupy\n";
        builder.setTitle("Twoje zamówienie zostało przyjęte")
                .setMessage(message);

        builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigateToMainActivity();
            }
        });


        dialog = builder.create();
        dialog.show();

    }
}
