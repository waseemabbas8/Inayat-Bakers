package com.example.user.bakers.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.user.bakers.R;
import com.example.user.bakers.models.Cart_items;
import com.example.user.bakers.ui.fragments.Contact_Us;
import com.example.user.bakers.ui.fragments.Home;
import com.example.user.bakers.ui.fragments.Location;
import com.example.user.bakers.ui.fragments.LogOut;
import com.example.user.bakers.ui.fragments.OrderHistory;
import com.example.user.bakers.ui.fragments.OrderNow;
import com.example.user.bakers.ui.fragments.Profile;

import java.util.ArrayList;

public class Inayat_Bakers extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static ArrayList<Cart_items> cart = new ArrayList<>();
    ViewFlipper viewFlipper;
    private TextView Call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        int images[] = {R.drawable.slide4, R.drawable.slide2, R.drawable.slide3, R.drawable.slide5, R.drawable.slide6, R.drawable.slide7, R.drawable.slide8, R.drawable.slide9};
        viewFlipper = findViewById(R.id.viewFlipper);
        Call = findViewById(R.id.tvCall);


        for (int image : images) {
            flipperImages(image);
        }

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(052)4582452)"));
                if (ActivityCompat.checkSelfPermission(Inayat_Bakers.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }

                startActivity(intent);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    Fragment fragment = null;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id==R.id.action_Profile){
            fragment = new Profile();

        }if (id == R.id.action_settings){
            fragment = new LogOut();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

        } else if (id == R.id.nav_orderhistory) {
            fragment = new OrderHistory();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
        } else if (id == R.id.nav_ordernow) {
            fragment = new OrderNow();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

        } else if (id == R.id.nav_profile) {
            fragment = new Profile();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();}
        else if (id == R.id.nav_Location){
            Intent intent=new Intent(Inayat_Bakers.this,Location.class);
            startActivity(intent);
        }else if (id == R.id.nav_Contact) {
            fragment = new Contact_Us();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();}



//            else if (id == R.id.action_settings) {
//            fragment = new Settings();
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

         else if (id == R.id.action_logout) {
            fragment = new LogOut();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void flipperImages(int image){
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }


}
