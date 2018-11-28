
package com.example.user.bakers.ui.activities.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.OrderHistoryAdapter;
import com.example.user.bakers.adapter.TrackingAdapter;
import com.example.user.bakers.models.OrderHistory;
import com.example.user.bakers.models.TrackingModel;
import com.example.user.bakers.ui.fragments.OrderTracking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminTracking extends AppCompatActivity {
    ListView recyclerView;


    ArrayList<OrderTracking> orderTrackings;
    com.example.user.bakers.models.TrackingModel trackingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tracking);
        recyclerView = findViewById(R.id.recyclerView);
        trackingModel=new com.example.user.bakers.models.TrackingModel();

        orderTrackings = new ArrayList<>();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders Location");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())

                    for (int i = 1; i <= dataSnapshot1.getChildrenCount(); i++) {
                        if ((String) dataSnapshot1.child(i + ") Product Name").getValue() != null) {
                            orderTrackings = new ArrayList<>();
                            String name = (String) dataSnapshot1.child(i + ") Product Name").getValue();
                            trackingModel.setName(name);
                            String price = (String) dataSnapshot1.child(i + ") price").getValue();
                            trackingModel.setPrice(price);
                            String quantity = String.valueOf(Integer.parseInt((String) dataSnapshot1.child(i + ") quantity").getValue()));
                            trackingModel.setQuantity(quantity);
                            String Longitude = String.valueOf(Integer.parseInt((String) dataSnapshot1.child(i + ") quantity").getValue()));
                            trackingModel.setLongitude(Longitude);
                            String latitude = String.valueOf(Integer.parseInt((String) dataSnapshot1.child(i + ") quantity").getValue()));
                            trackingModel.setLatitude(latitude);
                            orderTrackings.add(trackingModel);
                        }
                    }

               //recyclerView.setLayoutManager(new LinearLayoutManager(AdminTracking.this));
                TrackingAdapter adapter = new TrackingAdapter(AdminTracking.this,  orderTrackings);
              recyclerView.setAdapter(adapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );
    }
    }
