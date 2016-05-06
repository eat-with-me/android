package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.win7.restapitest.R;

/**
 * Created by win7 on 06/05/2016.
 */
public class MenuParentViewHolder extends ParentViewHolder {

    public TextView categoryName;

    public MenuParentViewHolder(View itemView) {
        super(itemView);

        categoryName = (TextView) itemView.findViewById(R.id.category_name);
    }
}
