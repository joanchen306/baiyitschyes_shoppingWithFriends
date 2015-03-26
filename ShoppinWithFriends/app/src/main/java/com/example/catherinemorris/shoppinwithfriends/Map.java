package com.example.catherinemorris.shoppinwithfriends;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Map extends FragmentActivity {

    GoogleMap googleMap;
    ArrayList<Double> myPositionList = new ArrayList<Double>();
    LatLng myPos;
    final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);


        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        googleMap = fm.getMap();

        googleMap.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String provider = locationManager.getBestProvider(criteria, true);

        Location location = locationManager.getLastKnownLocation(provider);

        double latitude;
        double longitude;

//        if(location != null) {
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            myPos = new LatLng(latitude, longitude);
//
//        } else {
//            myPos = ATLANTA;
//            latitude = 33.7550;
//            longitude = 84.3900;
//        }

        myPos = ATLANTA;
        latitude = 33.7550;
        longitude = -84.3900;

        Marker myLoc = googleMap.addMarker(new MarkerOptions().position(myPos).title("Here").draggable(true));
        myPositionList.add(latitude);
        myPositionList.add(longitude);
        myLoc.setVisible(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 15));

    }

    public void backToSale(View view) {
        Intent i = new Intent("android.BackSaleItem");
        startActivity(i);
    }
}