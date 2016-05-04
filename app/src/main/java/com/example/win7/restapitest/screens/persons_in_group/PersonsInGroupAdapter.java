package com.example.win7.restapitest.screens.persons_in_group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.R;
import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.model.User;

import java.util.List;


public class PersonsInGroupAdapter extends RecyclerView.Adapter<PersonsInGroupAdapter.ViewHolder> {



    private List<User> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView personsName;


        public ViewHolder(View view) {
            super(view);
            personsName = (TextView) view.findViewById(R.id.persons_name);
        }

    }


    public PersonsInGroupAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public PersonsInGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_person_in_group, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.personsName.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {

        return users.size();
    }

}
