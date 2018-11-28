package com.example.user.bakers.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.FoodItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodItems extends Fragment {
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Food Items");
    ArrayList<String> name, thumbnail;
    ArrayList<Long> price;
    private RecyclerView recyclerView;
    FloatingActionButton faBtn;
    DataSnapshot[] dataSnapshots;


    FoodItemAdapter foodItemAdapter;

    public FoodItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_items, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        faBtn = view.findViewById(R.id.faBtn);


        name = new ArrayList<>();
        price = new ArrayList<>();
        thumbnail = new ArrayList<>();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                name.clear();
                price.clear();
                thumbnail.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        // String ItemNo=dataSnapshot1.getKey();
                        String Name = (String) dataSnapshot2.child("Name").getValue();
                        Long Price = (Long) dataSnapshot2.child("Price").getValue();
                        String Thumbnail = (String) dataSnapshot2.child("Thumbnail").getValue();


                        name.add(Name);
                        price.add(Price);
                        thumbnail.add(Thumbnail);

                    }

                }


                FoodItemAdapter adapter = new FoodItemAdapter(getActivity(), name, price, thumbnail);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                // Toast.makeText(getActivity(),Name,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        faBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Working", Toast.LENGTH_SHORT).show();
                FoodItemAdapter.showCart();
            }
        });

        return view;
    }

}
