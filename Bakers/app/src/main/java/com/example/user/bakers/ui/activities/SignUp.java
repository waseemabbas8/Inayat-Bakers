package com.example.user.bakers.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String user_id;
    EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    Button btnSignUP;
    PhoneAuthProvider mCallbacks;
    int v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUP = findViewById(R.id.btnRegister);

        btnSignUP.setOnClickListener(new View.OnClickListener() {


            @Override
            public void
            onClick(View view) {

                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String phone = etPhone.getText().toString();
                final String password = etPassword.getText().toString();
                final String confirmpassword = etConfirmPassword.getText().toString();


                if (name.length() < 3  || !name.matches("[a-zA-z]+")) {
                    etName.setError("Name should between 3 and 8 characters not number");
                    v=1;
                }  if (email.length() <0) {
                    etEmail.setError("Email is Empty");
                    v=1;

                }

                 if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etEmail.setError("Invalid Email Address");
                    v=1;
                }



                 if (phone.length()==0 || phone.length()<6 || phone.length()>13 || !phone.matches("^03[0-9]+$") ) {
                    etPhone.setError("Phone No is invalid");
                    v=1;
                }
                 if (password.length() < 6) {
                    etPassword.setError("Must be 6 characters");
                    v=1;
                }
                if (!confirmpassword.equals(password)) {
                    etConfirmPassword.setError("Password not matchs");
                    v=1;

                }
                if (!password.equals(confirmpassword)) {
                    etConfirmPassword.setError("Password do not Match");
                    v=1;
                }
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmpassword)) {

                    Toast.makeText(SignUp.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
                    v=1;

                }
                if (v==0){
                    mAuth = FirebaseAuth.getInstance();
                   // user_id = mAuth.getCurrentUser().getUid();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference().child("Users").child(task.getResult().getUser().getUid());

                                HashMap<String, String> userData = new HashMap<>();

                                userData.put("Name", name);
                                userData.put("Email", email);
                                userData.put("Phone No", phone);
                                userData.put("Password", password);
                                userData.put("Confirm password", confirmpassword);


                                myRef.setValue(userData);
                                final ProgressDialog dialog=new ProgressDialog(SignUp.this);
                                dialog.setMessage("Loading");
                                dialog.show();
                                startActivity(new Intent(SignUp.this, Inayat_Bakers.class));
                            } else {
                                Toast.makeText(SignUp.this, "Registration Failed or Already Registerd", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(SignUp.this, task.getResult().toString(), Toast.LENGTH_SHORT).show();
                            }

                        }


            });
                }
            }


        });
    }

}
