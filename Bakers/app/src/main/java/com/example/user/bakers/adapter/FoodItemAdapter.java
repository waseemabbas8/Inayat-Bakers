package com.example.user.bakers.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.models.Cart_items;
import com.example.user.bakers.ui.activities.Cart_activity;
import com.example.user.bakers.ui.activities.Inayat_Bakers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 29/04/18.
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    static Context context;
    ArrayList<String> name, thumbnail;
    ArrayList<Long> price;


    public FoodItemAdapter(Context context, ArrayList<String> name, ArrayList<Long> price, ArrayList<String> thumbnail) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.context = context;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final FoodItemViewHolder holder, final int position) {


        try {
            holder.TvName.setText(name.get(position));
            holder.TvPrice.setText("" + price.get(position));
            Picasso.get().load(thumbnail.get(position)).into(holder.ImageView);
            final int[] counter = {0};
            holder.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter[0]++;
                    holder.quantity.setText(String.valueOf(counter[0]));
                    {
                        if (counter[0] != 0) {
                            long totalprice = counter[0] * price.get(position);

                            for (Cart_items cartItem:Inayat_Bakers.cart) {
                                if (cartItem.getName().equals(name.get(position)))
                                {
                                    cartItem.setQuantity(counter[0]);
                                    break;
                                }else {
                                    Cart_items cart_items = new Cart_items(

                                            name.get(position), price.get(position), counter[0], totalprice, thumbnail.get(position));

                                    Inayat_Bakers.cart.add(cart_items);
                                }
                            }

//                            Cart_items cart_items = new Cart_items(
//
//                                    name.get(position), price.get(position), counter[0], totalprice, thumbnail.get(position));
//
//                            Inayat_Bakers.cart.add(cart_items);

                        } else {
                            Toast.makeText(context, "NO item Selected", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
            holder.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (counter[0] != 0) {
                        counter[0]--;
                        holder.quantity.setText(String.valueOf(counter[0]));
                    }

                }
            });


        } catch (Exception e) {


        }


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder {

        public android.widget.ImageView ImageView;
        public TextView TvName, TvPrice, quantity;
        public Button btnPlus, btnMinus, btnCart;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            ImageView = itemView.findViewById(R.id.imageView);
            TvName = itemView.findViewById(R.id.TvName);
            TvPrice = itemView.findViewById(R.id.TvPrice);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            // btnCart = itemView.findViewById(R.id.btnCart);
            quantity = itemView.findViewById(R.id.TvQuantity);


        }
    }

    public static void showCart() {

        for (int i = 0; i < Inayat_Bakers.cart.size(); i++) {
            try {
                if (Inayat_Bakers.cart.get(i+1).getName().equals(Inayat_Bakers.cart.get(i).getName())) {
                    Inayat_Bakers.cart.remove(i);
                }

            } catch (Exception e) {

            }


        }
        context.startActivity(new Intent(context, Cart_activity.class));
    }
}
