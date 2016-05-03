package com.example.win7.restapitest.screens.persons_in_group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.User;
import com.example.win7.restapitest.others.ClickListener;
import com.example.win7.restapitest.others.MyActivity;
import com.example.win7.restapitest.others.RecyclerTouchListener;
import com.example.win7.restapitest.screens.main_screen.GroupsAdapter;
import com.example.win7.restapitest.screens.main_screen.MainActivity;
import com.example.win7.restapitest.screens.orders_in_group_screen.OrdersInGroupActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonsInGroupActivity extends MyActivity {


    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_in_group);

        recyclerView = (RecyclerView) findViewById(R.id.list_of_persons_in_group);

        Intent intent = getIntent();
        Group group = (Group)intent.getSerializableExtra(OrdersInGroupActivity.GROUP);
        String groupName = group.getName();
        List<User> users = group.getUsers();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(groupName);
        setSupportActionBar(myToolbar);

        RecyclerView.Adapter adapter = new PersonsInGroupAdapter(users);
        recyclerView.setAdapter(adapter);


        recycleViewInit();


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


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
