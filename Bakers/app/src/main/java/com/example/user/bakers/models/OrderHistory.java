package com.example.user.bakers.models;

import android.os.Parcel;
import android.os.Parcelable;


 public class OrderHistory implements Parcelable {
    String Name;
    Long Price;
    int Quantity;
    Long Totalprice;
    String Thumbnail;
    public String getThumbnail() {
        return Thumbnail;
    }

    public OrderHistory(){

    }
    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
//
    protected OrderHistory(Parcel in) {
        Name = in.readString();
        if (in.readByte() == 0) {
            Price = null;
        } else {
            Price = in.readLong();
        }
        Quantity = in.readInt();
        if (in.readByte() == 0) {
            Totalprice = null;
        } else {
            Totalprice = in.readLong();
        }
        Thumbnail = in.readString();
    }
    public static final Creator<OrderHistory> CREATOR = new Creator<OrderHistory>() {
        @Override
        public OrderHistory createFromParcel(Parcel in) {
            return new OrderHistory(in);
        }

        @Override
        public OrderHistory[] newArray(int size) {
            return new OrderHistory[size];
        }
    };
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Long getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(Long totalprice) {
        Totalprice = totalprice;
    }

    public OrderHistory(String name,Long price,int quantity,Long totalprice,String thumbnail) {
        this.Name = name;
        this.Price = price;
        this.Quantity = quantity;
        this.Totalprice = totalprice;
        this.Thumbnail = thumbnail;

    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        if (Price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(Price);
        }
        parcel.writeInt(Quantity);
        if (Totalprice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(Totalprice);
        }
        parcel.writeString(Thumbnail);


    }
}
