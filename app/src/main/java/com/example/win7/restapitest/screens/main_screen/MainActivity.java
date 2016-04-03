package com.example.win7.restapitest.screens.main_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {


    public static final String GROUP_ID = "groupId" ;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView messageTextView;
    private ProgressBar progressBar;
    private Button button;
    private String noInternet;

    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = (TextView) findViewById(R.id.empty_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_groups);


        mainPresenter = new MainPresenterImp(this);

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

                mainPresenter.onClickGroup(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //******************************************************************************************

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
        adapter = new GroupsAdapter(groupsResult);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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


        messageTextView.setText("Brak połączenia z internetem");
        messageTextView.setVisibility(View.VISIBLE);

    }


}



//TODO zorbić z tych trzech aktywnośći jakiś szablon albo zastosowac dziedziczenie