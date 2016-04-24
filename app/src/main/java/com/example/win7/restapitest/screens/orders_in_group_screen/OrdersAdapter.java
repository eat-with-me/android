package com.example.win7.restapitest.screens.orders_in_group_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.OrderInGroup;

import java.util.List;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{


    private List<OrderInGroup> orders;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName;
        public TextView closingTime;
        public View view;

        public ViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            closingTime = (TextView) view.findViewById((R.id.closing_time));
            this.view = view;

        }

    }


    public OrdersAdapter(List<OrderInGroup> orders) {this.orders = orders;  }

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        OrderInGroup order = orders.get(position);
        holder.restaurantName.setText(order.getRestaurant().getName());
        holder.closingTime.setText(order.getClosingTimeShort());
        holder.view.setClickable(false);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
