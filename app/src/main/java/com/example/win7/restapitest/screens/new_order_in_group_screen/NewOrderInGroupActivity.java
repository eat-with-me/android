package com.example.win7.restapitest.screens.new_order_in_group_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.OnDownloadFinishedListener;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.Factory;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.main_screen.MainActivity;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-20.
 */
public class NewOrderInGroupActivity extends AppCompatActivity implements NewOrderInGroupView {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private ProgressBar progressBar;
    private ApiConnection apiConnection;
    List<RestaurantMenu> restaurantsResult = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_in_group);

        this.apiConnection = Factory.getApiConnection();
        messageTextView = (TextView) findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) findViewById(R.id.restaurants_recycler);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        String groupId = intent.getStringExtra(MainActivity.GROUP_ID);

        showProgress();

        recycleViewInit();
//        mockRestaurants();
        getRestaurants();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment restaurantMenuFragment = fragmentManager.findFragmentById(R.id.restaurants_menu_fragment);
        fragmentTransaction.hide(restaurantMenuFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setEmptyView() {
        recyclerView.setVisibility(View.GONE);
        messageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadRestaurants(List<RestaurantMenu> restaurantsResult) {
        adapter = new RestaurantsAdapter(restaurantsResult);
        recyclerView.setAdapter(adapter);

    }
//    public void mockRestaurants() {
//        List<Restaurant> mockedRes = new ArrayList<>();
//        for(int i=0; i<3;i++)
//        {
//            Restaurant res = new Restaurant();
//            res.setName("res"+i);
//            mockedRes.add(i,res);
//        }
//        adapter = new RestaurantsAdapter(mockedRes);
//        recyclerView.setAdapter(adapter);
//        hideProgress();
//    }

    public void showProgress() {

        //TODO rozwiązać to jakoś inaczej (przeładowanie całego widoku)

        progressBar.setVisibility(View.VISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
//        button.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
//        button.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError() {

    }
    public void getRestaurants() {

        apiConnection.getAllRestaurantsMenu(new OnDownloadFinishedListener<List<RestaurantMenu>>() {
            @Override
            public void onSuccess(List<RestaurantMenu> list) {
                restaurantsResult = list;
                hideProgress();

                if(restaurantsResult.isEmpty()){
                    setEmptyView();
                }
                else{

                    loadRestaurants(restaurantsResult);
                }
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    @Override
    public void recycleViewInit() {


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}
