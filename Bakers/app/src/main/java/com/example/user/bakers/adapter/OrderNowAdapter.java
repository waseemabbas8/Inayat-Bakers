package com.example.user.bakers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.models.Cart_items;
import com.example.user.bakers.ui.activities.Inayat_Bakers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 22/04/18.
 */

public class OrderNowAdapter extends RecyclerView.Adapter<OrderNowAdapter.OrderNowViewHolder> {
    Context context;
    ArrayList<String> name, thumbnail;
    ArrayList<Long> price;


    public OrderNowAdapter(Context context, ArrayList<String> name, ArrayList<Long> price, ArrayList<String> thumbnail) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.context = context;
    }

    @Override
    public OrderNowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderNowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_now, parent, false));


    }

    @Override
    public void onBindViewHolder(final OrderNowViewHolder holder, final int position) {
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

                            Cart_items cart_items = new Cart_items(


                                    name.get(position), price.get(position), counter[0], totalprice, thumbnail.get(position));

                            Inayat_Bakers.cart.add(cart_items);

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
               /*  else {
                    for(int i = counter.size()-1; i >= 0; i--) {
                        if(quantity.get(i).selected) {
                            quantity.remove(i);
                        }
                    }*/
                    

//                    else (counter[1] !=0){
//                        counter[1]--;
//                        holder.quantity.setText(String.valueOf(counter));
//
//
//                    }
                }
            });
           /* holder.btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (counter[0] != 0) {
                        long totalprice = counter[0] * price.get(position);

                        Cart_items cart_items = new Cart_items(


                                name.get(position), price.get(position), counter[0], totalprice, thumbnail.get(position));

                        Inayat_Bakers.cart.add(cart_items);

                    } else {
                        Toast.makeText(context, "NO item Selected", Toast.LENGTH_LONG).show();
                    }

                }

            });*/


        } catch (Exception e) {


        }


    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class OrderNowViewHolder extends RecyclerView.ViewHolder {
        public ImageView ImageView;
        public TextView TvName, TvPrice, quantity;
        public Button btnPlus, btnMinus, btnCart;

        public OrderNowViewHolder(View itemView) {
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



}
