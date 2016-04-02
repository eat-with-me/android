package com.example.win7.restapitest.orders_in_group_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.main_screen.MainPresenterImp;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;

import java.util.List;

public class OrdersInGroupActivity extends AppCompatActivity implements OrdersInGroupView{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView emptyView;
    OrdersInGroupPresenter ordersInGroupPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_in_group);

        emptyView = (TextView) findViewById(R.id.empty_view);

        ordersInGroupPresenter = new OrdersInGroupPresenterImp(this);

        Intent intent = getIntent();
        String groupId = intent.getStringExtra(MainPresenterImp.GROUP_ID);



        //recyclerview******************************************************************************
        recyclerView = (RecyclerView) findViewById(R.id.list_of_orders);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                ordersInGroupPresenter.onClickOrder(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //******************************************************************************************

        ordersInGroupPresenter.getOrders(groupId);

    }

    public void onClickNewOrder(View v)
    {
        ordersInGroupPresenter.onClickNewOrder();
    }


    @Override
    protected void onDestroy(){
        ordersInGroupPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ordersInGroupPresenter.onResume();

    }

    @Override
    public void setEmptyView(){
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadOrders(List<OrderInGroup> ordersResult)
    {

        adapter = new OrdersAdapter(ordersResult);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
