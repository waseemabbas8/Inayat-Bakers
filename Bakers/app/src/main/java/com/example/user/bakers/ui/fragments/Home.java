package com.example.user.bakers.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.user.bakers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends android.support.v4.app.Fragment {
    ViewFlipper viewFlipper;
    private TextView Call;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        int images[] = {R.drawable.slide4, R.drawable.slide2, R.drawable.slide3, R.drawable.slide5, R.drawable.slide6, R.drawable.slide7, R.drawable.slide8, R.drawable.slide9};
        viewFlipper = view.findViewById(R.id.viewFlipper);
        Call = view.findViewById(R.id.tvCall);
        for (int image : images) {
            flipperImages(image);
        }
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(052)4582452)"));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


                }
                startActivity(intent);
            }
        });
        return view;
    }
    public void flipperImages(int image){
        ImageView imageView= new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
    }

}
