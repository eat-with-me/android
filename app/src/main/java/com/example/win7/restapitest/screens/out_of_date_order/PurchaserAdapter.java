package com.example.win7.restapitest.screens.out_of_date_order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Purchaser;

import java.util.List;

/**
 * Created by win7 on 07/05/2016.
 */
public class PurchaserAdapter extends RecyclerView.Adapter<PurchaserAdapter.ViewHolder> {

    private List<Purchaser> purchasers;


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
        Double amount = 0.0;
        for(int i=0; i<purchaser.getMeals_list().size(); i++) {
            amount += purchaser.getMeals_list().get(i).getMeal().getPrice()*purchaser.getMeals_list().get(i).getAmount();

            holder.meals.setText(holder.meals.getText() +
                    String.format("%d",purchaser.getMeals_list().get(i).getAmount()) + "x\t\t" +
                    purchaser.getMeals_list().get(i).getMeal().getName() +
                    "\n");



        }

        holder.charge.setText(String.format("%.2f",amount));

    }

    @Override
    public int getItemCount() {
        return purchasers.size();
    }


}