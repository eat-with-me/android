package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Meal;
import com.example.win7.restapitest.model.MealCategory;
import com.example.win7.restapitest.model.RestaurantMenu;

import java.util.List;


public class MenuAdapter extends ExpandableRecyclerAdapter<MenuParentViewHolder, MenuChildViewHolder> {


    private LayoutInflater mInflator;



    public MenuAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }


    @Override
    public MenuParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View parentView = mInflator.inflate(R.layout.row_menu_parent, parentViewGroup, false);
        return new MenuParentViewHolder(parentView);
    }


    @Override
    public MenuChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childView = mInflator.inflate(R.layout.row_menu_child, childViewGroup, false);




        return new MenuChildViewHolder(childView);
    }


    @Override
    public void onBindParentViewHolder(MenuParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        MealCategory category = (MealCategory) parentListItem;
        parentViewHolder.categoryName.setText(category.getName());
    }

    @Override
    public void onBindChildViewHolder(MenuChildViewHolder childViewHolder, int position, Object childListItem) {
        Meal meal = (Meal) childListItem;
        childViewHolder.dish.setText(meal.getName());
        childViewHolder.price.setText(meal.getPrice().toString());
    }





}
