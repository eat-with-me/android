package com.example.win7.restapitest.screens.order_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Meal;

import java.util.ArrayList;


/**
 * Created by Mateusz on 2016-04-12.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private ArrayList<Meal> meals;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dish;
        public TextView price;


        public ViewHolder(View view) {
            super(view);
            dish = (TextView) view.findViewById(R.id.dish);
            price = (TextView) view.findViewById(R.id.price);
        }

    }


    public OrderAdapter(ArrayList<Meal> meals) {
        this.meals = meals;
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
        Meal meal = meals.get(position);

        holder.dish.setText(meal.getName());
        holder.price.setText("cena");

    }

    @Override
    public int getItemCount() {

        return meals.size();
    }
}
