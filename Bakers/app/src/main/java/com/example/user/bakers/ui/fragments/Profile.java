package com.example.user.bakers.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.ui.activities.Inayat_Bakers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.

 */

public class Profile extends android.support.v4.app.Fragment {
    EditText etName1, etEmail1, etPhone1, etPassword1;
    ImageButton btnUpdate, btnCancel;
    private FirebaseAuth mAuth;
    private static final String TAG = "UpdateProfile";
    String user_id;

    public Profile() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        etName1 = view.findViewById(R.id.etName1);
        etEmail1 = view.findViewById(R.id.etEmail1);
        etPhone1 = view.findViewById(R.id.etPhoneNo1);
        etPassword1 = view.findViewById(R.id.etPassword1);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnCancel = view.findViewById(R.id.imgBtnCancel);

        registerListener();


        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("Name").getValue();
                etName1.setText(name);
                String email = (String) dataSnapshot.child("Email").getValue();
                etEmail1.setText(email);
                String phone = (String) dataSnapshot.child("Phone No").getValue();
                etPhone1.setText(phone);
                String password = (String) dataSnapshot.child("Password").getValue();
                etPassword1.setText(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }


    public void registerListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName1.getText().toString();
                String email = etEmail1.getText().toString();
                String phone = etPhone1.getText().toString();
                String password = etPassword1.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("Users").child(user_id);

                HashMap<String, String> userData = new HashMap<>();

                userData.put("Name", name);
                userData.put("Email", email);
                userData.put("Phone No", phone);
                userData.put("Password", password);

                myRef.setValue(userData);

                Toast.makeText(getActivity(), "Successful!", Toast.LENGTH_LONG).show();


            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Nothing Update", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), Inayat_Bakers.class));


                // Intent intent=new Intent(Profile.this,O);
                //startActivity(intent);




            }
        });


    }

    //    private void readData() {
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
//        // Log.d(TAG, "UserID is " + id);
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//               fetchdata(dataSnapshot);
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                fetchdata(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
    private void fetchdata(DataSnapshot dataSnapshot) {
        String value = dataSnapshot.getValue(String.class);
        //Log.d(TAG, "Value is " + value);
        // mylist.add(value);
        // Log.d(TAG, "Size :   " + mylist.size());
    }
    private void setvalues(){
        etName1.setText(etName1.getText());
        etEmail1.setText(etEmail1.getText());
        etPassword1.setText(etPassword1.getText());
        etPhone1.setText(etPhone1.getText());

    }

}
