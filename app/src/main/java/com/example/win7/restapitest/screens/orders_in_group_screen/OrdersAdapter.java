package com.example.win7.restapitest.screens.orders_in_group_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.OrderInGroup;
import com.example.win7.restapitest.model.Orders;

import java.util.List;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{


    private Orders orders;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName;
        public TextView closingTime;
        public TextView numberOfOrders;
        public View view;

        public ViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            closingTime = (TextView) view.findViewById((R.id.closing_time));
            numberOfOrders = (TextView) view.findViewById(R.id.number_of_orders);
            this.view = view;

        }

    }


    public OrdersAdapter(Orders orders) {this.orders = orders;  }

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
        OrderInGroup order = orders.getOrders().get(position);
        holder.restaurantName.setText(order.getRestaurant().getName());
        holder.closingTime.setText(order.getClosingTimeFormated());
        holder.numberOfOrders.setText(order.getNumberOfPurchasers());
        holder.view.setClickable(false);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
