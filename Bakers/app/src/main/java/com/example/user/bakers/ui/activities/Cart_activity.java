package com.example.user.bakers.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.bakers.R;
import com.example.user.bakers.adapter.CartAdapter;
import com.example.user.bakers.models.Cart_items;
import com.example.user.bakers.ui.fragments.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnCheckout;
    EditText product,price,quantity,total;
    double lat, lng;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_activity);

        btnCheckout = findViewById(R.id.btnCheckout);
        recyclerView=findViewById(R.id.rvCart);
        final ArrayList<Cart_items> list = Inayat_Bakers.cart;
        final CartAdapter cartAdapter = new CartAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);



        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {


                        locationListener = new LocationListener() {

                    @Override
                    public void onLocationChanged(android.location.Location location) {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
//                        if(cartAdapter.getItemCount()==0){
//                            Toast.makeText(Cart_activity.this, "No item Selected", Toast.LENGTH_SHORT).show();
//                        }else{

                        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Orders History").child(user_id);
                        HashMap<String, String> Orders = new HashMap<>();
//                     for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++)
//
                      /*int size = list.size()-1;*/

                      for(int i=1;i<list.size();i++){
                          Orders.put( "Product Name", list.get(i).getName());
                          Orders.put( "price", String.valueOf(list.get(i).getPrice()));
                          Orders.put( "quantity",String.valueOf(list.get(i).getQuantity()));
                          Orders.put( "Thumbnail",String.valueOf(list.get(i).getThumbnail()));
                          Orders.put( "Total Price",String.valueOf(list.get(i).getTotalprice()));
//                            Orders.put(size +")Latitude", String.valueOf(lat));
//                            Orders.put(size +")Longitude", String.valueOf(lng));

                      }
                        reference.setValue(Orders);

                        Toast.makeText(Cart_activity.this, "Order Received for Location", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Cart_activity.this, Location.class);
                        startActivity(intent);
                        Inayat_Bakers.cart = new ArrayList<>();

                        locationManager.removeUpdates(locationListener);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(Cart_activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Cart_activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
//                Toast.makeText(Cart_activity.this,"Order PLaced",Toast.LENGTH_LONG).show();

            }
        });




    }

    @Override
    public void onBackPressed() {
        finish();

    }
}
