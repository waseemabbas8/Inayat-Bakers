package com.example.user.bakers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bakers.R;
import com.example.user.bakers.models.TrackingModel;
import com.example.user.bakers.ui.activities.Admin.AdminTracking;
import com.example.user.bakers.ui.fragments.OrderTracking;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TrackingAdapter extends BaseAdapter {

    Context context;
    ArrayList<TrackingModel> tracking_models;


    public TrackingAdapter (Context context, ArrayList<TrackingModel> tracking_models) {
        this.context = context;
        this.tracking_models = tracking_models;
    }

    public TrackingAdapter(AdminTracking context, ArrayList<OrderTracking> orderTrackings) {

    }

    @Override
    public int getCount() {
        return tracking_models.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        TrackingAdapter.MyviewHolder holder = null;

        View view;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.order_tracking, null);

        TrackingAdapter.MyviewHolder myviewHolder1 = new TrackingAdapter.MyviewHolder();


        holder.Name = view.findViewById(R.id.tvName);
        holder.Name.setText(tracking_models.get(position).getName());
        holder.Price = view.findViewById(R.id.tvPrice2);
        holder.Price.setText(tracking_models.get(position).getPrice());
        holder.Quantity = view.findViewById(R.id.tvQuantity7);
        holder.Quantity.setText(String.valueOf(tracking_models.get(position).getQuantity()));
//        myviewHolder1.textView3 = view.findViewById(R.id.tpot);
//        myviewHolder1.textView3.setText(tracking_models.get(position).getTotalprice());
        holder.imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        Picasso.get().load(tracking_models.get(position).getThumnbnail()).into(holder.imageView3);



        return view;
    }

    public static class MyviewHolder {

        ImageView imageView3;
        TextView Name,Price,Quantity;
    }
}