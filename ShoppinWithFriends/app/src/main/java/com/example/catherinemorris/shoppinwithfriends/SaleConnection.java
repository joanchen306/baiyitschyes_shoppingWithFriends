package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SaleConnection extends ActionBarActivity implements Serializable {

    ItemOnSale sale;
    User myU;
    GoogleMap googleMap;

    final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);


    /**
     * Pulls the information from the previous Activity to populate this Activity with
     * information pertaining to the ItemOnSale in question.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_connection);

        myU = (User) getIntent().getSerializableExtra("User"); //pulls in myU from the last Activity
        sale = (ItemOnSale) getIntent().getSerializableExtra("Sale"); //pulls in the ItemOnSale that the User clicked on in HomeScreen or FoundSaleList

        TextView currName = (TextView)findViewById(R.id.itemField);
        currName.setText(sale.getItem());

        TextView currPrice = (TextView) findViewById(R.id.PriceField);
        currPrice.setText("" + sale.getPrice());

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.smallmap);
        googleMap = fm.getMap();

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

        TextView currLoc = (TextView) findViewById(R.id.locationField);
        currLoc.setText(sale.getLocation().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openMap (View view) {
        Intent i = new Intent("android.SaleConnectionMap");
        i.putExtra("User", myU);
        i.putExtra("Sale", sale);
        startActivity(i);
    }

    /**
     * Closes the current Activity
     * @param view
     */
    public void goBack(View view) {
        finish();
    }

    private static final long serialVersionUID = 7526471155622776147L;
    /**
     * Always treat de-serialization as a full-blown constructor, by
     * validating the final state of the de-serialized object.
     */
    private void readObject(
            ObjectInputStream aInputStream
    ) throws ClassNotFoundException, IOException {
        //always perform the default de-serialization first
        aInputStream.defaultReadObject();

    }

    /**
     * This is the default implementation of writeObject.
     * Customise if necessary.
     */
    private void writeObject(
            ObjectOutputStream aOutputStream
    ) throws IOException {
        //perform the default serialization for all non-transient, non-static fields
        aOutputStream.defaultWriteObject();
    }
}
