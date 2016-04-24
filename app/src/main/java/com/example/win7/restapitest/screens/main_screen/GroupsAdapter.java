package com.example.win7.restapitest.screens.main_screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win7.restapitest.model.Group;
import com.example.win7.restapitest.R;

import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private List<Group> groups;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groupName;


        public ViewHolder(View view) {
            super(view);
            groupName = (TextView) view.findViewById(R.id.groupName);
        }

    }


    public GroupsAdapter(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_group_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.groupName.setText(group.getName());

    }

    @Override
    public int getItemCount() {

        return groups.size();
    }
}