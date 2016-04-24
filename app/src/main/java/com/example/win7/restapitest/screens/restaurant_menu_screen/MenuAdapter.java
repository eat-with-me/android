package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.RestaurantMenu;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    private RestaurantMenu menu;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dish;
        public TextView price;


        public ViewHolder(View view) {
            super(view);
            dish = (TextView) view.findViewById(R.id.dish);
            price = (TextView) view.findViewById(R.id.price);
        }

    }


    public MenuAdapter(RestaurantMenu menu) {
        this.menu = menu;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = menu.getMeals().get(position);

        holder.dish.setText(meal.getName());
        holder.price.setText(meal.getPrice().toString());

    }

    @Override
    public int getItemCount() {

        return menu.getMeals().size();
    }

}
