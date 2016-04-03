package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

public class RestaurantMenuActivity extends AppCompatActivity  implements RestaurantMenuView{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private TextView totalPrice;
    private TextView totalProducts;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;

    private RestaurantMenuPresenter restaurantMenuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);


        messageTextView = (TextView) findViewById(R.id.empty_view);
        totalPrice = (TextView) findViewById(R.id.price);
        totalProducts = (TextView) findViewById(R.id.number_of_products);
        recyclerView = (RecyclerView) findViewById(R.id.menu_list);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);



        totalPrice.setText("0");
        totalPrice.setText("0");

        restaurantMenuPresenter = new RestaurantMenuPresenterImp(this);

        Intent intent = getIntent();
        String restaurantId = intent.getStringExtra(OrdersInGroupActivity.RESTAURANT_ID);

        showProgress();

        //recyclerview******************************************************************************

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

        restaurantMenuPresenter.getMenu(restaurantId);



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
        messageTextView.setVisibility(View.VISIBLE);
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


    @Override
    public void setTotalPrice(String price){
        totalPrice.setText(price);
    }

    @Override
    public void setTotalProducts(String products){
        totalProducts.setText(products);
    }

    @Override
    public void showProgress() {
        //TODO rozwiązać to jakoś inaczej (przeładowanie całego widoku)

        progressBar.setVisibility(View.VISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }



    @Override
    public void showError() {

        progressBar.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        //TODO przenieść tekst gdzieś indziej

        messageTextView.setText("Brak połączenia z internetem");
        messageTextView.setVisibility(View.VISIBLE);
    }

}
