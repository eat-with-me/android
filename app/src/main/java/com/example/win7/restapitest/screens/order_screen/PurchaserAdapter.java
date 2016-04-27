package com.example.win7.restapitest.screens.order_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Purchaser;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class PurchaserAdapter extends RecyclerView.Adapter<PurchaserAdapter.ViewHolder> {

    private List<Purchaser> purchasers;
    Double amount;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView email;
        public TextView meals;
        public TextView charge;
        public ViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.purchaser_email);
            meals = (TextView) itemView.findViewById(R.id.purchaser_meals);
            charge = (TextView) itemView.findViewById(R.id.purchaser_charge);
        }
    }
    public PurchaserAdapter(List<Purchaser> purchasers){
        this.purchasers=purchasers;
        this.amount = 0.0;

    }
    @Override
    public PurchaserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_purchaser, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PurchaserAdapter.ViewHolder holder, int position) {
        Purchaser purchaser = purchasers.get(position);
        holder.email.setText(purchaser.getUser().getEmail());
        for(int i=0; i<purchaser.getMeals().size(); i++) {
            amount += purchaser.getMeals().get(i).getPrice();
            holder.meals.setText(holder.meals.getText() + purchaser.getMeals().get(i).getName() + "\n");
        }

        holder.charge.setText(String.format("%.2f",amount));

    }

    @Override
    public int getItemCount() {
        return purchasers.size();
    }


}
