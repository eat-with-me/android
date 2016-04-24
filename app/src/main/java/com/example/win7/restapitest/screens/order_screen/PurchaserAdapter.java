package com.example.win7.restapitest.screens.order_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Purchasers;

import java.util.List;

/**
 * Created by Mateusz on 2016-04-24.
 */
public class PurchaserAdapter extends RecyclerView.Adapter<PurchaserAdapter.ViewHolder> {

    private List<Purchasers> purchasers;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView email;
        public ViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.purchaser_email);

        }
    }
    public PurchaserAdapter(List<Purchasers> purchasers){
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
        Purchasers purchaser = purchasers.get(position);
        holder.email.setText(purchaser.getUser().getEmail());

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}