package com.example.catherinemorris.shoppinwithfriends;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SaleConnectionMap extends FragmentActivity implements OnMapReadyCallback {
    private ItemOnSale saleItem;


    private final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sale_connection_map);
        Firebase.setAndroidContext(this);

        saleItem = (ItemOnSale) getIntent().getSerializableExtra("ItemOnSale");

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.saleconnectionmap);
        fm.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ATLANTA);
        markerOptions.title("My Location");
        markerOptions.draggable(true);
        map.addMarker(markerOptions);
        builder.include(markerOptions.getPosition());

        MarkerOptions itemMarker = new MarkerOptions();
        ArrayList<Double> loc = saleItem.getLocation();
        LatLng latLong = new LatLng(loc.get(0),loc.get(1));
        itemMarker.position(latLong);
        itemMarker.title("Item Location");
        map.addMarker(itemMarker);
        builder.include(itemMarker.getPosition());

        LatLngBounds bounds = builder.build();
        //int padding = 0;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,25,25,5);
        map.moveCamera(cameraUpdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sale_connection_map, menu);
        return true;
    }

    /**
     * Goes back to SaleConnection page
     * @param view used to overwrite method
     */


    public void backButton(View view) {
        finish();
    }
}