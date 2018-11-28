package com.example.user.bakers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 29/04/18.
 */

public class ChildFoodAdapter extends RecyclerView.Adapter<ChildFoodAdapter.ChildFoodViewHolder> {

    Context context;


    @Override
    public ChildFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // return new ChildFoodViewHolder(LayoutInflater.from());
        return null;
    }

    @Override
    public void onBindViewHolder(ChildFoodViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChildFoodViewHolder extends RecyclerView.ViewHolder{
        public ChildFoodViewHolder(View itemView) {
            super(itemView);
        }
    }
}
