package com.example.win7.restapitest.orders_in_group_screen;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.win7.restapitest.main_screen.GroupsAdapter;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;

/**
 * Created by win7 on 01/04/2016.
 */
public class OrdersAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder>{
    public OrdersAdapter(List<OrderInGroup> ordersResult) {
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GroupsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
