package com.example.win7.restapitest.screens.main_screen;

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



//import butterknife.Bind;
//import butterknife.ButterKnife;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.List;

public  class MainActivity extends MyActivity implements MainView {


    public static final String GROUP_ID = "groupId" ;

    private RecyclerView recyclerView;
    private TextView messageTextView;
    private ProgressBar progressBar;
    private Button button;


    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = (TextView) findViewById(R.id.empty_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.bClearMyOrder);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_groups);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recycleViewInit();

        mainPresenter = new MainPresenterImp(this);

        showProgress();

        mainPresenter.getGroups();

    }


    public void onClickAddNewGroup(View v)
    {
        mainPresenter.onClickNewGroup();
    }




    @Override
    protected void onDestroy(){
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mainPresenter.onResume();

    }

    @Override
    public void setEmptyView(){
        recyclerView.setVisibility(View.GONE);
        messageTextView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadGroups(List<Group> groupsResult)
    {
        RecyclerView.Adapter adapter = new GroupsAdapter(groupsResult);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void goToOrdersInGroupActivity(String groupId) {

        Intent intent = new Intent(this, OrdersInGroupActivity.class);
        intent.putExtra(GROUP_ID, groupId);
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





    private void recycleViewInit(){

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                mainPresenter.onClickGroup(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}



//TODO zorbić z tych trzech aktywnośći jakiś szablon albo zastosowac dziedziczenie