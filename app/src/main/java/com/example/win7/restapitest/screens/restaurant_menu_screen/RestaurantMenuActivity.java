package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.MealCategory;
import com.example.win7.restapitest.model.Order;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.screens.new_order_in_group_screen.NewOrderInGroupActivity;
import com.example.win7.restapitest.screens.order_screen.OrderFragmentActivity;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantMenuActivity extends MyActivity implements RestaurantMenuView{

    private ImageView imageView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private TextView totalPrice;
    private TextView totalProducts;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private String restaurantId;
    private String groupId;
    private String restaurantName;
    private String orderId;
    private OrderInGroup orderInGroup;

    private RestaurantMenuPresenter restaurantMenuPresenter;
    private static  final  int ORDER_ACTIVITY_REQUEST_CODE = 1;
    public static final String ORDER = "orderInGroup";


    AlertDialog pictureDialog;
    private boolean disabledMenu;



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

        restaurantMenuPresenter = new RestaurantMenuPresenterImp(this);


        getDataFromPreviousActivity();

        toolbarInit();

        showProgress();//http://eat24.herokuapp.com/restaurants/1

        recycleViewInit();

        pictureDialogInit();

        if(disabledMenu)
            restaurantMenuPresenter.disableMenu();
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

        showProgress();
        restaurantMenuPresenter.getMenu(restaurantId);
    }

    @Override
    public void setEmptyView(){
        recyclerView.setVisibility(View.GONE);
        messageTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadMenu(List<MealCategory> menu)
    {
        adapter = new MenuAdapter(this,menu,restaurantMenuPresenter);

        recyclerView.setAdapter(adapter);
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


        String noInternet = getString(R.string.no_internet);
        messageTextView.setText(noInternet);
        messageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToOrderActivity() {

        Intent intent = new Intent(this, OrderFragmentActivity.class);

        intent.putExtra(OrdersInGroupActivity.GROUP_ID,groupId);
        intent.putExtra(ORDER,orderInGroup);
        intent.putExtra("order", restaurantMenuPresenter.getOrder());
        startActivityForResult(intent,ORDER_ACTIVITY_REQUEST_CODE);
    }


    @Override

    public void hideButton() {
        relativeLayout.setVisibility(View.GONE);
    }



    private void recycleViewInit() {

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    private void getDataFromPreviousActivity(){
        Intent intent = getIntent();
        disabledMenu = intent.getExtras().getBoolean(NewOrderInGroupActivity.DISABLED_MENU);

        if(disabledMenu){

            restaurantId = intent.getStringExtra(NewOrderInGroupActivity.RESTAURANT_ID);

        }
        else{
            groupId = intent.getStringExtra(OrdersInGroupActivity.GROUP_ID);
            orderInGroup = (OrderInGroup) intent.getSerializableExtra(OrdersInGroupActivity.ORDER);
            restaurantId = orderInGroup.getRestaurantId();
            orderId = orderInGroup.getId();
            restaurantName = intent.getStringExtra(OrdersInGroupActivity.RESTAURANT_NAME);
        }
    }


    private void toolbarInit(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(restaurantName);
        setSupportActionBar(myToolbar);
    }


    private void pictureDialogInit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                pictureDialog.dismiss();
            }
        });

        pictureDialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_picture,null);
        pictureDialog.setView(dialogLayout);

    }

    @Override
    public void showPictureDialog(String mealsName) {
        pictureDialog.setTitle(mealsName);
        pictureDialog.show();
    }

    @Override
    public void loadPicture(String url){
        imageView = (ImageView) pictureDialog.findViewById(R.id.mealsPicture);
        if(pictureDialog.isShowing()) {
            Picasso
                    .with(this)
                    .load(url)
                    .into(imageView);

        }
    }
}
