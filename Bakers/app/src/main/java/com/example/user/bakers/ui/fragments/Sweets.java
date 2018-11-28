package com.example.user.bakers.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.FoodItemAdapter;
import com.example.user.bakers.adapter.OrderNowAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Sweets extends Fragment {

    DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("Sweets");
    ArrayList<String> name,thumbnail;
    ArrayList<Long>price;
    private RecyclerView recyclerView;
    FloatingActionButton faBtn;
    FoodItemAdapter foodItemAdapter;




    public Sweets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sweets, container, false);
        recyclerView= view.findViewById(R.id.recyclerView);
        faBtn=view.findViewById(R.id.faBtn);


        name=  new ArrayList<>();
        price= new ArrayList<>();
        thumbnail=new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                    // String Category = data.getKey();
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        // String ItemNo=dataSnapshot1.getKey();
                        String Name =(String) dataSnapshot1.child("Name").getValue();
                        Long Price =(Long) dataSnapshot1.child("Price").getValue();
                        String Thumbnail=(String)dataSnapshot1.child("Thumbnail").getValue();

                        name.add(Name);
                        price.add(Price);
                        thumbnail.add(Thumbnail);



                    }


                OrderNowAdapter orderNowAdapter=new OrderNowAdapter(getActivity(),name,price,thumbnail);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(orderNowAdapter);
                // Toast.makeText(getActivity(),Name,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        faBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)
                {
                FoodItemAdapter.showCart();
            }
        });


        return view;
    }
}
