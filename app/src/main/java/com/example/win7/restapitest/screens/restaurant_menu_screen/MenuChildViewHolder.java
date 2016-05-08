package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.win7.restapitest.R;

/**
 * Created by win7 on 06/05/2016.
 */
public class MenuChildViewHolder extends ChildViewHolder {


    public TextView dish;
    public TextView price;

    public MenuChildViewHolder(View itemView) {
        super(itemView);

        dish = (TextView) itemView.findViewById(R.id.dish);
        price = (TextView) itemView.findViewById(R.id.price);

    }
}
