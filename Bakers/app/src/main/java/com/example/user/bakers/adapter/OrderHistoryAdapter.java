package com.example.user.bakers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bakers.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    Context context;
    ArrayList<com.example.user.bakers.models.OrderHistory> OrderHistory;
    public OrderHistoryAdapter(Context context, ArrayList<com.example.user.bakers.models.OrderHistory> OrderHistory) {
        this.context=context;
        this.OrderHistory=OrderHistory;
    }
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.order_history,parent,false));
    }

    @Override
    public void onBindViewHolder(OrderHistoryAdapter.OrderHistoryViewHolder holder, int position) {
        holder.name.setText(OrderHistory.get(position).getName());
        holder.quantity.setText(String.valueOf(OrderHistory.get(position).getQuantity()));
        holder.totalprice.setText(String.valueOf(OrderHistory.get(position).getTotalprice()));
        holder.Price.setText(String.valueOf(OrderHistory.get(position).getPrice()));


        //Toast.makeText(context, OrderHistory.get(position).getThumbnail(), Toast.LENGTH_SHORT).show();
        Picasso.get().load(OrderHistory.get(position).getThumbnail()).into(holder.ImageView);


    }

    @Override
    public int getItemCount() {
        return OrderHistory.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        public android.widget.ImageView ImageView;
        public TextView name, Price, totalprice, quantity;


        public OrderHistoryViewHolder(View itemView) {
            super(itemView);
            ImageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.tvName7);
            Price = itemView.findViewById(R.id.tvPrice7);
            totalprice = itemView.findViewById(R.id.tvTotal7);
            quantity = itemView.findViewById(R.id.tvQuantity7);

        }

    }
}