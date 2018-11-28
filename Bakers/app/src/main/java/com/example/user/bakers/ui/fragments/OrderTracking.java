
package com.example.user.bakers.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.TrackingAdapter;
import com.example.user.bakers.models.TrackingModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OrderTracking extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<com.example.user.bakers.models.TrackingModel> orderTrackings;
    com.example.user.bakers.models.TrackingModel trackingModel;


    public OrderTracking() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        trackingModel = new TrackingModel(OrderTracking.this);
        orderTrackings = new ArrayList<>();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
         databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders Tracking");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                 for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                                                                     if ((String) dataSnapshot.child(i + ") Product Name").getValue() != null) {
                                                                         trackingModel = new TrackingModel(OrderTracking.this);
                                                                         String name = (String) dataSnapshot.child(i + ") Product Name").getValue();
                                                                         trackingModel.setName(name);
                                                                         String price = (String) dataSnapshot.child(i + ") price").getValue();
                                                                         trackingModel.setPrice(String.valueOf(Long.valueOf(price)));
                                                                         int Quantity = Integer.parseInt((String) dataSnapshot.child(i + ") quantity").getValue());
                                                                         trackingModel.setQuantity(String.valueOf(Quantity));
                                                                         String Thumbnail = (String) dataSnapshot.child("Thumbnail").getValue();
                                                                        trackingModel.setThumnbnail(Thumbnail);
                                                                         orderTrackings.add(trackingModel);



                                                                     }
                                                                 }

                                                             }

                                                             @Override
                                                             public void onCancelled(@NonNull DatabaseError databaseError) {

                                                             }
                                                         });


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Order_Tracking");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                }

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


        return  inflater.inflate(R.layout.fragment_order_tracking, container, false);

    }



}