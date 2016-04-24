package com.example.win7.restapitest.screens.orders_in_group_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.restaurant_menu_screen.RestaurantMenuActivity;

import java.util.List;

public class OrdersInGroupActivity extends MyActivity implements OrdersInGroupView{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private Button button;
    private ProgressBar progressBar;

    private OrdersInGroupPresenter ordersInGroupPresenter;

    public static final String RESTAURANT_ID = "restaurantId" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_in_group);

        messageTextView = (TextView) findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_orders);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ordersInGroupPresenter = new OrdersInGroupPresenterImp(this);

        Intent intent = getIntent();
        String groupId = intent.getStringExtra(MainActivity.GROUP_ID);

        showProgress();
        recycleViewInit();
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
        messageTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadOrders(List<OrderInGroup> ordersResult)
    {

        adapter = new OrdersAdapter(ordersResult);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void goToRestaurantMenuActivity(String restaurantId) {

        Intent intent = new Intent(this, RestaurantMenuActivity.class);
        intent.putExtra(RESTAURANT_ID, restaurantId);
        startActivity(intent);

    }

    @Override
    public void showProgress() {

        //TODO rozwiązać to jakoś inaczej (przeładowanie całego widoku)

        progressBar.setVisibility(View.VISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError() {

        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);


        String noInternet = getString(R.string.no_internet);
        messageTextView.setText(noInternet);
        messageTextView.setVisibility(View.VISIBLE);

    }


    public void recycleViewInit(){

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
    }

}
