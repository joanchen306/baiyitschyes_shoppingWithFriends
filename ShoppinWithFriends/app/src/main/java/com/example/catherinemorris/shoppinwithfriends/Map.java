package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Map extends FragmentActivity {

    GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng latlng;
    ArrayList<Double> myPositionList = new ArrayList<Double>();
    LatLng myPos;
    LatLng saleLatLong;
    final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);

    LinearLayout mapLinLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();

        // Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.find);

        // Defining button click event listener for the find button
        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.locsearch);

                // Getting user input location
                String location = etLocation.getText().toString();

                if(location!=null && !location.equals("")){
                    new GeocoderTask().execute(location);
                }
            }
        };

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);


//        googleMap.setMyLocationEnabled(true);
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String provider = locationManager.getBestProvider(criteria, true);
//        Location location = locationManager.getLastKnownLocation(provider);

//        double latitude;
//        double longitude;

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


        //use this for my location
//        myPos = ATLANTA;
//
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 15));
//        Marker myLoc = googleMap.addMarker(new MarkerOptions().position(myPos).title("Here").draggable(true));
//        LatLng dragPos = myLoc.getPosition();
//        latitude = dragPos.latitude;
//        longitude = dragPos.longitude;
//        myPositionList.add(latitude);
//        myPositionList.add(longitude);
//        myLoc.setVisible(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    /**
     * Creates a Geocoder of the input location and gets three location addresses
     * Uses the location addresses to place draggable markers on map
     */

    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latlng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latlng);
                markerOptions.title(addressText);
                markerOptions.draggable(true);
                googleMap.addMarker(markerOptions);
                saleLatLong = latlng;

                // Locate the first location
                if(i==0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
            }
        }
    }

    /**
     * Goes back to sale item page
     * @param view
     */

    public void doneWithMap(View view) {
        Intent i = new Intent("android.SaleItem");
        i.putExtra("saleLocation", saleLatLong);
        startActivity(i);
    }

    public void backToSale(View view) {
        Intent i = new Intent("android.SaleItem");
        startActivity(i);
    }
}