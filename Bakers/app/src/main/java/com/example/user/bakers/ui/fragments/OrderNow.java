package com.example.user.bakers.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.OrderNowAdapter;
import com.example.user.bakers.adapter.OrderViewPagerAdapter;
import com.example.user.bakers.models.Cart_items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderNow extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;

    ViewPager viewPager;
    TabLayout tabLayout;



    public OrderNow() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order_now, container, false);


        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        OrderViewPagerAdapter orderViewPagerAdapter = new OrderViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(orderViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;



        }




}
