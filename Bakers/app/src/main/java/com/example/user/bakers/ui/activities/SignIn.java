package com.example.user.bakers.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.ui.activities.Admin.AdminDrawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword;
    private String name;
    private String password;
    private Button btnLogin,btnLoginAdmin;
    private TextView tvSignUp;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
            startActivity(new Intent(this, Inayat_Bakers.class));
           finish();
        }
        etName = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        tvSignUp = findViewById(R.id.tvAccountSignUp);

        btnLogin = findViewById(R.id.btnLoginCustomer);
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();

                name = etName.getText().toString();
                password = etPassword.getText().toString();
                if (password.length() < 6 || !name.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    if (password.length() < 6)
                        etPassword.setError("Must be 6 characters");
                    if (!name.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                        etName.setError("Invalid Email Address");

                    // Toast.makeText(SignUp.this,"Must be 6 characters",Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                                dialog.setMessage("Loading");
                                dialog.show();
                                startActivity(new Intent(SignIn.this, Inayat_Bakers.class));
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Invalid User!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (etName.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin123")){
                        startActivity(new Intent(SignIn.this, AdminDrawer.class));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Todo:Add Admin Verification Here
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));

                finish();
            }
        });



    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
