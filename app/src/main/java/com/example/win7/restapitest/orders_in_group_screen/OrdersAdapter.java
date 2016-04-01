package com.example.win7.restapitest.orders_in_group_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.main_screen.GroupsAdapter;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{


    private List<OrderInGroup> orders;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName;
        public TextView closingTime;

        public ViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            closingTime = (TextView) view.findViewById((R.id.closing_time));
        }

    }


    public OrdersAdapter(List<OrderInGroup> orders) {this.orders = orders;  }

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        OrderInGroup order = orders.get(position);
        holder.restaurantName.setText(order.getRestaurant().getName());
        holder.restaurantName.setText(order.getClosingTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
