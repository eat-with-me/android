package com.example.win7.restapitest.screens.orders_in_group_screen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.api.ApiConnection;
import com.example.win7.restapitest.api.ApiConnectionImp;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.new_order_in_group_screen.NewOrderInGroupActivity;
import com.example.win7.restapitest.screens.persons_in_group.PersonsInGroupActivity;
import com.example.win7.restapitest.screens.restaurant_menu_screen.RestaurantMenuActivity;

import java.util.List;

public class OrdersInGroupActivity extends MyActivity implements OrdersInGroupView{


    public static final String GROUP_ID = "groupId" ;
    public static final String RESTAURANT_NAME = "restaurantName";
    public static final String RESTAURANT_ID = "restaurantId" ;
    public static final String GROUP = "group" ;

 
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private Button button;
    private ProgressBar progressBar;
    private Group group;
    private OrdersInGroupPresenter ordersInGroupPresenter;

    private AlertDialog linkDialog;


    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_in_group);

        messageTextView = (TextView) findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_orders);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);

        ordersInGroupPresenter = new OrdersInGroupPresenterImp(this);

        Intent intent = getIntent();
        group = (Group)intent.getSerializableExtra(MainActivity.GROUP);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(group.getName());
        setSupportActionBar(myToolbar);

        initLinkDialog();

        showProgress();
        recycleViewInit();
        ordersInGroupPresenter.getOrders(group.getId());

    }

    public void onClickNewOrder(View v)
    {
        Intent intent = new Intent(this, NewOrderInGroupActivity.class);
        Log.d("GROUPID", group.getId());
        intent.putExtra(GROUP_ID, group.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_group, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_group:

                ordersInGroupPresenter.onClickPersonsInGroup();
                return true;

            case R.id.action_add_to_group:

                ordersInGroupPresenter.onClickAddPersonToGroup();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
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

        showProgress();
        ordersInGroupPresenter.getOrders(group.getId());
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
    public void goToRestaurantMenuActivity(String restaurantId, String restaurantName) {

        Intent intent = new Intent(this, RestaurantMenuActivity.class);
        intent.putExtra(OrdersInGroupActivity.GROUP_ID,group.getId());
        intent.putExtra(RESTAURANT_ID, restaurantId);
        intent.putExtra(RESTAURANT_NAME,restaurantName);
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


    private void initLinkDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String token = group.getToken();
        String baseUrl = ApiConnectionImp.BASE_URL;
        final String link = baseUrl + token;

        builder.setTitle(R.string.link_dialog_title)
                .setMessage(link);

        builder.setNegativeButton(R.string.copy, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                copyToClipboard(link);
                showToast(getString(R.string.copy_to_clipboard));
            }
        });


        linkDialog = builder.create();

    }

    private void copyToClipboard(String somethingToCopy) {

        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        Uri copyUri = Uri.parse(somethingToCopy);
        ClipData clip = ClipData.newUri(getContentResolver(),"URI",copyUri);
        clipboard.setPrimaryClip(clip);

    }


    @Override
    public void navigateToPersonsInGroupActivity() {
        Intent intent = new Intent(this, PersonsInGroupActivity.class);
        intent.putExtra(GROUP, group);
        startActivity(intent);
    }

    @Override
    public void showLinkDialog() {
        linkDialog.show();
    }

}
