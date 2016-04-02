package com.example.win7.restapitest.main_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



//import butterknife.Bind;
//import butterknife.ButterKnife;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.orders_in_group_screen.OrdersInGroupActivity;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView emptyView;

    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyView = (TextView) findViewById(R.id.empty_view);

        mainPresenter = new MainPresenterImp(this);

       

        //recyclerview******************************************************************************
        recyclerView = (RecyclerView) findViewById(R.id.list_of_groups);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                mainPresenter.onClickGroup(position);
                //Group group = groupsResult.get(position);


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
        emptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadGourps(List<Group> groupsResult)
    {
        adapter = new GroupsAdapter(groupsResult);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
