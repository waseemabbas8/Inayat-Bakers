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
import android.widget.Button;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.DealsAdapter;
import com.example.user.bakers.adapter.OrderNowAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Deals extends Fragment {
    Button btnPlus,btnMinus;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("Deals");
    ArrayList<String> item1,item2,item3,thumbnail;
    ArrayList<Long>price;
    private RecyclerView recyclerView;
    FloatingActionButton faBtn;
    DealsAdapter dealsAdapter;
    public Deals() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_food_items, container, false);
        recyclerView= view.findViewById(R.id.recyclerView);
        faBtn=view.findViewById(R.id.faBtn);
      //  btnPlus=view.findViewById(R.id.btnPlus);
      //  btnMinus=view.findViewById(R.id.btnMinus);
     //   btnPlus.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {

          //  }
      //  });



        item1=  new ArrayList<>();
        item2=  new ArrayList<>();
        item3=  new ArrayList<>();
        price= new ArrayList<>();
        thumbnail=new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    // String Category = data.getKey();
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        // String ItemNo=dataSnapshot1.getKey();
                        String Item1 =(String) dataSnapshot1.child("item 1").getValue();
                        String Item2 =(String) dataSnapshot1.child("item 2").getValue();
                        String Item3 =(String) dataSnapshot1.child("item 3").getValue();
                        Long Price =(Long) dataSnapshot1.child("Price").getValue();
                        String Thumbnail=(String)dataSnapshot1.child("Thumbnail").getValue();
                        item1.add(Item1);
                        item2.add(Item2);
                        item3.add(Item3);
                        price.add(Price);
                        thumbnail.add(Thumbnail);
                         dealsAdapter=new DealsAdapter(getActivity(),item1,item2,item3,price,thumbnail);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(dealsAdapter);
                        //Toast.makeText(getActivity(),Name,Toast.LENGTH_LONG).show();

                    }
                }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        faBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Working", Toast.LENGTH_SHORT).show();
                dealsAdapter.showCart();

            }
        });
        return view;





}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
