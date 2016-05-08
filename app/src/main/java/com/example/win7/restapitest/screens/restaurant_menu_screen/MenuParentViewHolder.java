package com.example.win7.restapitest.screens.restaurant_menu_screen;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.win7.restapitest.R;

/**
 * Created by win7 on 06/05/2016.
 */
public class MenuParentViewHolder extends ParentViewHolder {

    public TextView categoryName;
    public ImageButton button;


    public MenuParentViewHolder(View itemView) {
        super(itemView);

        categoryName = (TextView) itemView.findViewById(R.id.category_name);
        button = (ImageButton) itemView.findViewById(R.id.expand_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                }
                else {
                    expandView();
                }
            }
        });


    }


    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }

    @Override
    public void onExpansionToggled(boolean  expanded){

    }



}
