package com.example.user.bakers.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 06/05/18.
 */

public class Cart_items implements Parcelable {
    String Name;
    Long Price;
    int Quantity;
    Long Totalprice;
    String Thumbnail;

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    protected Cart_items(Parcel in) {
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

    public static final Creator<Cart_items> CREATOR = new Creator<Cart_items>() {
        @Override
        public Cart_items createFromParcel(Parcel in) {
            return new Cart_items(in);
        }

        @Override
        public Cart_items[] newArray(int size) {
            return new Cart_items[size];
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
    public Cart_items(String name, Long price, int quantity, Long totalprice, String thumbnail){
        this.Name=name;
        this.Price=price;
        this.Quantity=quantity;
        this.Totalprice=totalprice;
        this.Thumbnail=thumbnail;
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
