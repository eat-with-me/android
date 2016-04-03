package com.example.win7.restapitest.screens.restaurant_menu_screen;

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
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.main_screen.GroupsAdapter;
import com.example.win7.restapitest.screens.main_screen.MainPresenter;
import com.example.win7.restapitest.screens.main_screen.MainPresenterImp;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity  implements RestaurantMenuView{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView emptyView;
    private TextView totalPrice;
    private TextView totalProducts;

    private RestaurantMenuPresenter restaurantMenuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);


        emptyView = (TextView) findViewById(R.id.empty_view);

        restaurantMenuPresenter = new RestaurantMenuPresenterImp(this);


        //recyclerview******************************************************************************
        recyclerView = (RecyclerView) findViewById(R.id.menu_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                restaurantMenuPresenter.onClickMeal(position);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //******************************************************************************************

        restaurantMenuPresenter.getMenu("1");



    }

    public void onClickGoToCart(View v)
    {
        restaurantMenuPresenter.onClickGoToCart();
    }


    @Override
    protected void onDestroy(){
        restaurantMenuPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        restaurantMenuPresenter.onResume();

    }

    @Override
    public void setEmptyView(){
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadMenu(RestaurantMenu menuResult)
    {
        adapter = new MenuAdapter(menuResult);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void goToOrdersInGroupActivity(String groupId) {
//
//        Intent intent = new Intent(this, OrdersInGroupActivity.class);
//        intent.putExtra(GROUP_ID, groupId);
//        startActivity(intent);
//    }

}
