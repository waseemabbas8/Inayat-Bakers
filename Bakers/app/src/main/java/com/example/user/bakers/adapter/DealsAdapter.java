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

import com.example.user.bakers.ui.activities.Cart_activity;
import com.example.user.bakers.R;
import com.example.user.bakers.models.Cart_items;
import com.example.user.bakers.ui.activities.Inayat_Bakers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 28/04/18.
 */

public class DealsAdapter extends  RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {
    Context context;
    ArrayList<String> item1,item2,item3,thumbnail;
    ArrayList<Long>price;
    public DealsAdapter(Context context, ArrayList<String> item1,ArrayList<String> item2,ArrayList<String> item3, ArrayList<Long> price,ArrayList<String> thumbnail){
        this.item1=item1;
        this.item2=item2;
        this.item3=item3;
        this.price=price;
        this.thumbnail=thumbnail;
        this.context=context;


    }

    @Override
    public DealsAdapter.DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DealsViewHolder(LayoutInflater.from(context).inflate(R.layout.deals,parent,false));


    }

    @Override
    public void onBindViewHolder(final DealsViewHolder holder,final int position) {


        holder.item1.setText(item1.get(position));
        holder.item2.setText(item2.get(position));
        holder.item3.setText(item3.get(position));
        holder.TvPrice1.setText(""+price.get(position));
        if(thumbnail.get(position)!=null){
            Picasso.get().load(thumbnail.get(position)).into(holder.ImageView2);

        }
        final int[] counter = {0};
       holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[0]++;
               holder.quantity.setText(String.valueOf(counter[0]));
                {

                    if(counter[0]!=0){
                        long totalprice = counter[0] * price.get(position);

                        String foodItem = item1.get(position)+", "+item2.get(position)+", "+item3.get(position);
                        Cart_items cart_items =new Cart_items(foodItem,price.get(position),counter[0],totalprice,thumbnail.get(position));

                        Inayat_Bakers.cart.add(cart_items);

                    }
                    else{
                        Toast.makeText(context,"NO item Selected",Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
       holder.btnMinus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (counter[0]!=0){
                   counter[0]--;
                   Long totalprice=counter[0]*price.get(position);

                   String foodItems=item1.get(position)+", "+item2.get(position)+" ,"+item3.get(position);
                   Cart_items cart_items=new Cart_items(foodItems,price.get(position),counter[0],totalprice,thumbnail.get(position));
                   Inayat_Bakers.cart.remove(cart_items);
                   holder.quantity.setText(String.valueOf(counter[0]));

               }

           }
       });
       }


    @Override
    public int getItemCount() {
        return item1.size();
    }

    public class DealsViewHolder extends RecyclerView.ViewHolder {
        public android.widget.ImageView ImageView2;
        public TextView item1,item2,item3,TvPrice1,quantity;
        public Button btnPlus,btnMinus,btnCart;


        public DealsViewHolder(View itemView) {
            super(itemView);
            ImageView2=itemView.findViewById(R.id.imageView2);
            item1=itemView.findViewById(R.id.Item1);
            item2=itemView.findViewById(R.id.Item2);
            item3=itemView.findViewById(R.id.Item3);
            TvPrice1=itemView.findViewById(R.id.Price1);
            btnPlus=itemView.findViewById(R.id.btnPlus);
            btnMinus=itemView.findViewById(R.id.btnMinus);
           // btnCart=itemView.findViewById(R.id.btnCart);
            quantity = itemView.findViewById(R.id.TvQuantity);





        }

    }


    public void showCart(){

        for (int i = 0; i < Inayat_Bakers.cart.size(); i++) {
            try {

                if (Inayat_Bakers.cart.get(i).getName().equals(Inayat_Bakers.cart.get(i+1).getName())) {
                    Inayat_Bakers.cart.remove(i);
                }

            } catch (Exception e) {

            }


        }
        context.startActivity(new Intent(context, Cart_activity.class));
    }
}
