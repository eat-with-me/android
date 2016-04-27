package com.example.win7.restapitest.screens.order_screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.Order;


/**
 * Created by Mateusz on 2016-04-12.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public Order order;
    private Context mContext;


    public OrderAdapter(Order order, Context mContext) {
        this.order = order;
        this.mContext = mContext;
    }


    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = order.getMeals().get(position);

        holder.dish.setText(meal.getName());
        holder.price.setText(String.format("%.2f", meal.getPrice()));
    }

    @Override
    public int getItemCount() {

        return order.getMeals().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView dish;
        public TextView price;
        public ImageButton cross;


        public ViewHolder(View view) {
            super(view);
            dish = (TextView) view.findViewById(R.id.dish);
            price = (TextView) view.findViewById(R.id.price);
            cross = (ImageButton) view.findViewById(R.id.cross);
            cross.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.equals(cross)) {
                Meal meal = order.getMeals().get(getAdapterPosition());
                order.delete(meal);
                notifyDataSetChanged();
                if (mContext instanceof OrderFragmentActivity && order.getMeals().isEmpty()) {
                    ((OrderFragmentActivity) mContext).setEmptyMyOrderView();
                }
            }
        }
    }
}
