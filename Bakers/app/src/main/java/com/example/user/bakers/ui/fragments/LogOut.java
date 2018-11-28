package com.example.user.bakers.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.bakers.R;
import com.example.user.bakers.ui.activities.Inayat_Bakers;
import com.example.user.bakers.ui.activities.SignIn;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogOut extends android.support.v4.app.Fragment {
   Button btnYes,btnNo;

    public LogOut() {
//        Intent intent=new Intent(LogOut.this,SignIn.class);
//        startActivity(intent);

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_out, container, false);
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);
        // Inflate the layout for this fragment


        btnYes.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               Intent intent = new Intent(getContext(), SignIn.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);



            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getContext(),Inayat_Bakers.class));

            }
        });


        return view;

    }
}
