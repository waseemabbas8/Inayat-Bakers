package com.example.user.bakers.adapter;

import android.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.bakers.ui.fragments.Deals;
import com.example.user.bakers.ui.fragments.FoodItems;
import com.example.user.bakers.ui.fragments.Sweets;

/**
 * Created by user on 28/04/18.
 */

public class OrderViewPagerAdapter extends FragmentStatePagerAdapter {
    public OrderViewPagerAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
            return "Deals";
            case 1:
            return "Food Items";
            case 2:
                return "Sweets";


        }
        return "";

    }
    @Override
    public android.support.v4.app.Fragment getItem(int position){
        switch (position){
            case 0:
                return new Deals();
            case 1:
                return new FoodItems();
            case 2:
                return new Sweets();
        }
        return null;

    }
    @Override
    public int getCount(){
        return 3;
    }
}

