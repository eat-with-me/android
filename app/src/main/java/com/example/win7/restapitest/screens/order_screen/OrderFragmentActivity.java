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
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.Purchase;
import com.example.win7.restapitest.model.Purchasers;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

;import java.util.List;

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

    private Order order;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Intent intent = getIntent();
        order = intent.getParcelableExtra("order");
        restaurantId = intent.getStringExtra(OrdersInGroupActivity.RESTAURANT_ID);
        groupId = intent.getStringExtra(MainActivity.GROUP_ID);

        adapter = new OrderAdapter(order,getBaseContext());
        myOrderIsEmpty = (TextView) findViewById(R.id.my_empty_basket_text);
        otherOrderIsEmpty = (TextView) findViewById(R.id.other_empty_basket_text);
        bOtherOrders = (Button) findViewById(R.id.bOtherOrders);
        bAccept = (Button) findViewById(R.id.bAccept);

        recyclerViewOtherOrder = (RecyclerView) findViewById(R.id.other_meals_in_order);

        FragmentManager fm2 = getSupportFragmentManager();
//        Fragment myOrderFragment = fm2.findFragmentById(R.id.my_order_fragment);
        Fragment otherOrdersFragment = fm2.findFragmentById(R.id.other_orders_fragment);
        FragmentTransaction trans = fm2.beginTransaction();
//        trans.replace(R.id.my_order_fragment, myOrderFragment);
        trans.hide(otherOrdersFragment).commit();

        addShowHideListener(R.id.bMyOrder, R.id.bOtherOrders, fm2.findFragmentById(R.id.my_order_fragment), fm2.findFragmentById(R.id.other_orders_fragment));

        orderPresenter = new OrderPresenterImp(this);
        recyclerViewInit();
        getPurchasers();
//        if(order.getMeals().isEmpty()){
//            setEmptyMyOrderView();
//        }


    }

    public Order getOrder()
    {
        return this.order;
    }

    @Override
    public void setEmptyMyOrderView() {
//        recyclerViewMyOrder.setVisibility(View.GONE);
        myOrderIsEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyOtherOrderView() {

        otherOrderIsEmpty.setVisibility(View.VISIBLE);

    }
    public void getPurchasers(){
        orderPresenter.getPurchasers(groupId,restaurantId);

    }


    @Override
    public void onClickAcceptOrder(View view) {
        Integer[] tablica = new Integer[order.getMeals().size()];
        for (int i = 0; i < order.getMeals().size(); i++)
        {
            tablica[i] = order.getMeals().get(i).getId();
        }
        Purchase purchase = new Purchase(restaurantId,tablica);
        orderPresenter.onClickAccept(purchase,groupId);

    }

    @Override
    public void setPurchasers(List<Purchasers> purchasers) {

otherOrdersAdapter = new PurchaserAdapter(purchasers);
        showToast(purchasers.get(0).getUser().getEmail());
        recyclerViewOtherOrder.setAdapter(otherOrdersAdapter);
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


//                        button.setForeground(getDrawable(R.drawable.ic_keyboard_arrow_up_white_18dp));
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

//                        button.setForeground(getDrawable(R.drawable.ic_keyboard_arrow_up_white_18dp));
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
