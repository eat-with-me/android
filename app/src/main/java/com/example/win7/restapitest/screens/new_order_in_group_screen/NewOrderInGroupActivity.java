package com.example.win7.restapitest.screens.new_order_in_group_screen;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.win7.restapitest.screens.restaurant_menu_screen.RestaurantMenuActivity;

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
    private ImageButton arrow;
    List<RestaurantMenu> restaurantsResult = null;
    private EditText time;
    private NewOrderPresenter newOrderPresenter;
    private String groupId;
    private RestaurantMenu selectedRestaurant;
    private NewOrderInGroup newOrder;
    private Fragment restaurantMenuFragment;
    private AlertDialog dialog;


    public final static String RESTAURANT_ID = "restaurantId";
    public final static String DISABLED_MENU = "disabledMenu" ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_in_group);

        this.apiConnection = Factory.getApiConnection();

        messageTextView = (TextView) findViewById(R.id.empty_view);
        time = (EditText) findViewById(R.id.timeEditText);
        recyclerView = (RecyclerView) findViewById(R.id.restaurants_recycler);
        restaurantMenuRecycler = (RecyclerView) findViewById(R.id.restaurants_menu_recycler);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        arrow = (ImageButton) findViewById(R.id.arrow_button);
        newOrderPresenter = new NewOrderPresenterImpl(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.include);
        myToolbar.setTitle(getString(R.string.new_order));
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        groupId = intent.getStringExtra(OrdersInGroupActivity.GROUP_ID);
        showProgress();

        recycleViewInit();

        getRestaurants();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        restaurantMenuFragment = fragmentManager.findFragmentById(R.id.restaurants_menu_fragment);
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
        adapter = new RestaurantsAdapter(restaurantsResult,this);
        recyclerView.setAdapter(adapter);

    }
    public void loadRestaurantMenu(RestaurantMenu restaurantMenu){
        //adapter2 = new MenuAdapter(restaurantMenu);
        restaurantMenuRecycler.setAdapter(adapter2);
    }

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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
            showToast("Wybierz godzinę");
            return;
        }
        else  if(selectedRestaurant==null)
        {
            showToast("Wybierz restauracje");
            return;
        }
        else {
            initDialog();
            dialog.show();
        }
    }
    public void onClickShowDetails() {



//        if (getSupportFragmentManager().findFragmentById(R.id.restaurants_menu_fragment).isHidden()) {
//            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentById(R.id.restaurants_menu_fragment)).addToBackStack(null).commit();
//        }
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
    private void initDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String choice = "Wybrałeś: \n\n   " + time.getText().toString() + "\n   " + selectedRestaurant.getName();

        builder.setTitle(R.string.new_order)
                .setMessage(choice);

        builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setPositiveButton(R.string.akceptuj, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newOrder = new NewOrderInGroup(selectedRestaurant.getId(), time.getText().toString());
                newOrderPresenter.OnClickAccept(groupId, newOrder);
            }
        });


        dialog = builder.create();

    }

    public void navigateToDisabledMenu(String id) {
        Intent intent = new Intent(this, RestaurantMenuActivity.class);
        intent.putExtra(RESTAURANT_ID, id);
        intent.putExtra(DISABLED_MENU, true);
        startActivity(intent);

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
