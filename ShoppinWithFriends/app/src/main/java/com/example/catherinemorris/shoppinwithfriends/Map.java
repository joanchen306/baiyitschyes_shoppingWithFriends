package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
//import java.util.Calendar;
import java.util.List;


public class Map extends FragmentActivity {

    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private LatLng latlng;

    final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);

    private ItemOnSale saleItem;

    private User myU;

    final private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        Firebase.setAndroidContext(this);

        myU = (User) getIntent().getSerializableExtra("User");
        saleItem = (ItemOnSale) getIntent().getSerializableExtra("ItemOnSale");

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();

        // Getting reference to find button
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
            } else {

                // Clears all the existing markers on the map
                googleMap.clear();

                // Adding Markers on Google Map for each matching address
                for (int i = 0; i < addresses.size(); i++) {
                    Address address = addresses.get(i);
                    latlng = new LatLng(address.getLatitude(), address.getLongitude());

                    String addressText = String.format("%s, %s",
                            address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                            address.getCountryName());

                    markerOptions = new MarkerOptions();
                    markerOptions.position(latlng);
                    markerOptions.title(addressText);
                    markerOptions.draggable(true);
                    googleMap.addMarker(markerOptions);

                    // Locate the first location
                    if (i == 0) {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
                    }
                }
            }
        }
    }

    /**
     * Goes back to sale item page
     * @param view used to overwrite method
     */

    public void reportSale(View view) {
        //sets the location of the sale item

        if (markerOptions != null) {
            latlng = markerOptions.getPosition();
            saleItem.setLocation(latlng);
        }

        //sets the date of the sale reported
        //Calendar c = Calendar.getInstance();
        //int day = c.get(Calendar.DATE);
        //int month = c.get(Calendar.MONTH);
        //int year = c.get(Calendar.YEAR);

        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase itRef = myFirebaseRef.child("globalsales");
        itRef.push().setValue(saleItem);
        //Message to inform user that the data has been put into the database
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("You have added " + saleItem.getItem() + " to the global Sales List.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
        Intent i = new Intent("android.HomeScreen");
        i.putExtra("User", myU);
        startActivity(i);

    }

    public void backToSale(View view) {
        finish();
    }
}