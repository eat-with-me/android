package com.example.win7.restapitest.screens.new_order_in_group_screen;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.model.NewOrderInGroup;
import com.example.win7.restapitest.model.RestaurantMenu;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.Factory;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;
import com.example.win7.restapitest.screens.restaurant_menu_screen.MenuAdapter;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mateusz on 2016-04-20.
 */
public class NewOrderInGroupActivity extends MyActivity implements NewOrderInGroupView {

    private RecyclerView recyclerView;
    private RecyclerView restaurantMenuRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private TextView messageTextView;
    private ProgressBar progressBar;
    private ApiConnection apiConnection;
    private Button button;
    List<RestaurantMenu> restaurantsResult = null;
    private EditText time;
    private NewOrderPresenter newOrderPresenter;
    private String groupId;
    private RestaurantMenu selectedRestaurant;
    private NewOrderInGroup newOrder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_in_group);

        this.apiConnection = Factory.getApiConnection();
        button = (Button) findViewById(R.id.button2);
        messageTextView = (TextView) findViewById(R.id.empty_view);
        time = (EditText) findViewById(R.id.timeEditText);
        recyclerView = (RecyclerView) findViewById(R.id.restaurants_recycler);
        restaurantMenuRecycler = (RecyclerView) findViewById(R.id.restaurants_menu_recycler);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        newOrderPresenter = new NewOrderPresenterImpl(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.include);
        myToolbar.setTitle(getString(R.string.new_order));
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        groupId = intent.getStringExtra(OrdersInGroupActivity.GROUP_ID);
        Log.d("groupID", groupId);
        showProgress();

//        restaurantMenuRecyclerInit();
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
        this.restaurantsResult = restaurantsResult;
        adapter = new RestaurantsAdapter(restaurantsResult);
        recyclerView.setAdapter(adapter);

    }
    public void loadRestaurantMenu(RestaurantMenu restaurantMenu){
        //adapter2 = new MenuAdapter(restaurantMenu);
        restaurantMenuRecycler.setAdapter(adapter2);
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
    public void getRestaurants()
    {
        newOrderPresenter.getRestaurants();
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

                selectedRestaurant = restaurantsResult.get(position);
                loadRestaurantMenu(selectedRestaurant);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    public void onClickAccept(View view)
    {
        if(!isTimeCorrect())
        {
            return;
        }
        else  if(selectedRestaurant==null)
        {
            return;
        }
        else {
            newOrder = new NewOrderInGroup(selectedRestaurant.getId(), time.getText().toString());
            Log.d("onClickAccept", "groupId:" + groupId + " selectedRestaurant:" + selectedRestaurant.getId() + " Time:" + time.getText().toString());
            newOrderPresenter.OnClickAccept(groupId, newOrder);
        }
    }
    public void OnClickShowDetails(View view) {
        if (getSupportFragmentManager().findFragmentById(R.id.restaurants_menu_fragment).isHidden()) {

            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentById(R.id.restaurants_menu_fragment)).commit();
            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentById(R.id.restaurants_fragment)).commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentById(R.id.restaurants_fragment)).commit();
            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentById(R.id.restaurants_menu_fragment)).commit();
        }
    }
    public void showTimePickerDialog(View v) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,TimePickerDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText("" + hourOfDay + ":" + minute);
                time.setError(null);
            }
        },hour,minutes,true);
        timePickerDialog.show();

    }
    public boolean isTimeCorrect() {
        if(time.getText().toString().isEmpty()){
            time.setError(getString(R.string.choose_time));
            return false;
        }
        else {
            return true;
        }
    }
    public void clearNewOrder()
    {
        time.setText("");
        newOrder = null;
        time.setError(null);

    }

//    public void restaurantMenuRecyclerInit()
//    {
//        restaurantMenuRecycler.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//    }
}
