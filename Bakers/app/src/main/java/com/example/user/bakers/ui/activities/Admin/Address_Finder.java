package com.example.user.bakers.ui.activities.Admin;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.bakers.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Address_Finder extends AppCompatActivity {
    TextView textView;
    Geocoder geocoder;
    ArrayList<Address>addresses;

    Double longitude=74.5494872;
    Double latitude=32.5196173;
    String address,area,city, country,Pcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__finder);
        textView=findViewById(R.id.tvAddress);


        geocoder=new Geocoder(this, Locale.getDefault());
        try {
            addresses = (ArrayList<Address>) geocoder.getFromLocation(latitude,longitude,1);

            String address=addresses.get(0).getAddressLine(0);
            String area=addresses.get(0).getLocality();
            String city=addresses.get(0).getAdminArea();
            String country=addresses.get(0).getCountryName();
            String Pcode=addresses.get(0).getCountryCode();

            String FullAddress=address+" "+area+" "+city+""+country+""+Pcode;
            textView.setText(FullAddress);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
