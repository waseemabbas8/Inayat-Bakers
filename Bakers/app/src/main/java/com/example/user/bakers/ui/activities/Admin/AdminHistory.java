package com.example.user.bakers.ui.activities.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.OrderHistoryAdapter;
import com.example.user.bakers.models.OrderHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminHistory extends AppCompatActivity {
    RecyclerView recyclerView;


    ArrayList<OrderHistory> orderHistories;
    com.example.user.bakers.models.OrderHistory orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_history);
        recyclerView = findViewById(R.id.recyclerView);
        orderHistory = new com.example.user.bakers.models.OrderHistory();
        orderHistories = new ArrayList<>();

//        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Orders History");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    for (int i = 1; i <= dataSnapshot1.getChildrenCount(); i++) {
                        if ((String) dataSnapshot1.child("Product Name").getValue() != null) {
                            orderHistory = new com.example.user.bakers.models.OrderHistory();
                            String name = (String) dataSnapshot1.child("Product Name").getValue();
                            orderHistory.setName(name);
                            String price = (String) dataSnapshot1.child("price").getValue();
                            orderHistory.setPrice(Long.valueOf(price));
                            int Quantity = Integer.parseInt((String) dataSnapshot1.child("quantity").getValue());
                            orderHistory.setQuantity(Quantity);
                            Long totalPrice = Long.parseLong((String) dataSnapshot1.child("Total Price").getValue());
                            orderHistory.setTotalprice(totalPrice);
//                            Long Longitude = Long.parseLong((String) dataSnapshot1.child("Longitude").getValue());
//                            orderHistory.setLongitude(Longitude);
//                            Long Latitude = Long.parseLong((String) dataSnapshot1.child("Latitude").getValue());
//                            orderHistory.setLongitude(Latitude);
                            orderHistories.add(orderHistory);
                        }

                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(AdminHistory.this));
                OrderHistoryAdapter adapter = new OrderHistoryAdapter(AdminHistory.this, orderHistories);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}