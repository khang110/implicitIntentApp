package com.example.implicitintent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class xacnhanhdiachi extends AppCompatActivity {

    TextInputEditText tvCity, tvQuanHuyen, tvPhuongXa, tvDiaChiCuThe;
    Button buttonLayViTri, buttonLayViTriIntent, buttonXacNhan;
    FusedLocationProviderClient fusedLocationProviderClient;
    Double getLongtitude, getLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhanhdiachi);
//        Add back button at toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonLayViTriIntent = findViewById(R.id.button_layvitri_intent);
        buttonXacNhan = findViewById(R.id.btnXacNhan);

        buttonLayViTri = findViewById(R.id.button_layvitri);
        tvCity = findViewById(R.id.textview_city);
        tvQuanHuyen = findViewById(R.id.textview_quanhuyen);
        tvPhuongXa = findViewById(R.id.textview_phuongxa);
        tvDiaChiCuThe = findViewById(R.id.textview_vitricuthe);

        //        initialize fusedLocationProviderClient

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        buttonLayViTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                check permission
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();

                } else {
                    ActivityCompat.requestPermissions(xacnhanhdiachi.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44
                    );
                }
            }
        });

        buttonLayViTriIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(Intent.ACTION_VIEW);
                String geo = "geo: " + getLatitude + ", " + getLongtitude;
                intent.setData(Uri.parse(geo));
                startActivity(intent);
            }
        });

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(xacnhanhdiachi.this, uploadimage.class);
                startActivity(intent);
            }
        });


    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

//                initialize location
                Location location = task.getResult();
                getLatitude = location.getLatitude();
                getLongtitude = location.getLongitude();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(xacnhanhdiachi.this, Locale.getDefault());

//                    initialaze address list
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        String vitri = addresses.get(0).getAddressLine(0);
                        String quocgia = "", thanhpho = "", tendiachi = "", quan = "";
                        quocgia = addresses.get(0).getCountryName();
                        thanhpho = addresses.get(0).getAdminArea();
                        quan = addresses.get(0).getSubAdminArea();
                        tendiachi = addresses.get(0).getFeatureName();
                        String[] diachi = vitri.split(",");
                        tvCity.setText(diachi[diachi.length-2]);
                        tvQuanHuyen.setText(diachi[diachi.length-3]);
                        tvPhuongXa.setText(diachi[diachi.length-4]);
                        tvDiaChiCuThe.setText(tendiachi);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}