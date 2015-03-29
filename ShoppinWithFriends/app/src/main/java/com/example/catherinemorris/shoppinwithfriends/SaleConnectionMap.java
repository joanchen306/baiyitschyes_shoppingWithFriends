package com.example.catherinemorris.shoppinwithfriends;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SaleConnectionMap extends FragmentActivity {


    private final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GoogleMap googleMap;
        MarkerOptions markerOptions;
        ItemOnSale saleItem;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        Firebase.setAndroidContext(this);

        saleItem = (ItemOnSale) getIntent().getSerializableExtra("Sale");

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();

        markerOptions = new MarkerOptions();
        markerOptions.position(ATLANTA);
        markerOptions.title("My Location");
        markerOptions.draggable(true);
        googleMap.addMarker(markerOptions);

        MarkerOptions itemMarker = new MarkerOptions();
        ArrayList<Double> loc = saleItem.getLocation();
        LatLng latLong = new LatLng(loc.get(0),loc.get(1));
        itemMarker.position(latLong);
        itemMarker.title("Item Location");
        googleMap.addMarker(itemMarker);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sale_connection_map, menu);
        return true;
    }

//    /**
//     * Creates a Geocoder of the input location and gets three location addresses
//     * Uses the location addresses to place draggable markers on map
//     */
//
//    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {
//
//        protected List<Address> doInBackground(String... locationName) {
//            // Creating an instance of Geocoder class
//            Geocoder geocoder = new Geocoder(getBaseContext());
//            List<Address> addresses = null;
//
//            try {
//                // Getting a maximum of 3 Address that matches the input text
//                addresses = geocoder.getFromLocationName(locationName[0], 3);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return addresses;
//        }
//
//        @Override
//        protected void onPostExecute(List<Address> addresses) {
//
//            if(addresses==null || addresses.size()==0){
//                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
//            }
//
//            // Clears all the existing markers on the map
//            googleMap.clear();
//
//
//                markerOptions = new MarkerOptions();
//                markerOptions.position(ATLANTA);
//                markerOptions.title("My Location");
//                markerOptions.draggable(true);
//                googleMap.addMarker(markerOptions);
//
//            }
//        }

    /**
     * Goes back to SaleConnection page
     * @param view used to overwrite method
     */


    public void backButton(View view) {
        finish();
    }
}