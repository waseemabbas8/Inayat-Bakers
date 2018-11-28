package com.example.user.bakers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.models.Cart_items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 05/05/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
  ArrayList<Cart_items> cart_items;
    public CartAdapter(Context context,ArrayList<Cart_items> cart_items) {
       this.context=context;
       this.cart_items=cart_items;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_items,parent,false));
    }

    @Override
    public void onBindViewHolder(final CartAdapter.CartViewHolder holder, int position) {
        holder.name.setText(cart_items.get(position).getName());
        holder.quantity.setText(String.valueOf(cart_items.get(position).getQuantity()));
        holder.totalprice.setText(String.valueOf(cart_items.get(position).getTotalprice()));
        holder.Price.setText(String.valueOf(cart_items.get(position).getPrice()));

        Picasso.get().load(cart_items.get(position).getThumbnail()).into(holder.ImageView);


    }

    @Override
    public int getItemCount () {
        return cart_items.size();

    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        public android.widget.ImageView ImageView;
        public TextView name, Price, totalprice, quantity;


        public CartViewHolder(View itemView) {
            super(itemView);
            ImageView = itemView.findViewById(R.id.imageView7);
            name = itemView.findViewById(R.id.tvName7);
            Price = itemView.findViewById(R.id.tvPrice7);
            totalprice = itemView.findViewById(R.id.tvTotal7);
            quantity = itemView.findViewById(R.id.tvQuantity7);


        }

    }
}
