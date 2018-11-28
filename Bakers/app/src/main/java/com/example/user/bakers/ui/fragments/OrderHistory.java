package com.example.user.bakers.ui.fragments;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.OrderHistoryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class OrderHistory extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    Location location;


    ArrayList<com.example.user.bakers.models.OrderHistory> orderHistories;
    com.example.user.bakers.models.OrderHistory orderHistory;

    public OrderHistory() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        orderHistory = new com.example.user.bakers.models.OrderHistory();
        orderHistories = new ArrayList<>();


        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Orders History").child(user_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                    if ((String) dataSnapshot.child("Product Name").getValue() != null) {
                        orderHistory = new com.example.user.bakers.models.OrderHistory();
                        String name = (String) dataSnapshot.child( "Product Name").getValue();
                        orderHistory.setName(name);
                        String price = (String) dataSnapshot.child("price").getValue();
                        orderHistory.setPrice(Long.valueOf(price));
                        int Quantity = Integer.parseInt((String) dataSnapshot.child("quantity").getValue());
                        orderHistory.setQuantity(Quantity);
                        Long totalPrice = Long.parseLong((String) dataSnapshot.child("Total Price").getValue());
                        orderHistory.setTotalprice(totalPrice);
                        String Thumbnail = (String) dataSnapshot.child("Thumbnail").getValue();
                        orderHistory.setThumbnail(Thumbnail);
                        orderHistories.add(orderHistory);

                    }

                }


                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                OrderHistoryAdapter adapter = new OrderHistoryAdapter(getContext(), orderHistories);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }


}
