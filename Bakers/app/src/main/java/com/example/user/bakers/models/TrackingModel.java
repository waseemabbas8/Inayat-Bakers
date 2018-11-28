package com.example.user.bakers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.user.bakers.ui.fragments.OrderTracking;

public class TrackingModel extends OrderTracking {
    String Name;
    String Price;
    String Thumnbnail;
    String Longitude;
    String Latitude;
    String Quantity;

    public TrackingModel(String name, String price, String thumnbnail, String longitude, String latitude, String quantity) {
        Name = name;
        Price = price;
        Thumnbnail = thumnbnail;
        Longitude = longitude;
        Latitude = latitude;
        Quantity = quantity;
    }


    public TrackingModel() {

    }

    public TrackingModel(OrderTracking orderTracking) {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getThumnbnail() {
        return Thumnbnail;
    }

    public void setThumnbnail(String thumnbnail) {
        Thumnbnail = thumnbnail;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
