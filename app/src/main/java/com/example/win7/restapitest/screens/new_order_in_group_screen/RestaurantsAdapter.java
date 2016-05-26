package com.example.win7.restapitest.screens.new_order_in_group_screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-20.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>{

    private List<RestaurantMenu> restaurants;
    private Context context;


    public RestaurantsAdapter(List<RestaurantMenu> restaurants,Context context) {
        this.restaurants = restaurants;
        this.context=context;

    }
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RestaurantsAdapter.ViewHolder holder, final int position) {
        final RestaurantMenu restaurant = restaurants.get(position);
        holder.restaurantName.setText(restaurant.getName());

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName;
        public ImageButton arrow;



        public ViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
                 }

    }
}

