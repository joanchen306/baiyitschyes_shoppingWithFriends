package com.example.catherinemorris.shoppinwithfriends;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by James Nugent on 3/5/2015.
 */
class ItemOnSale implements Serializable {


    private String item;
    private double price;
    private String user;
    private ArrayList<Double> location = new ArrayList<>();
    //private ArrayList<Integer> expDate;

    //used to call the item and to put it in the list
    public ItemOnSale(String it, double price, String seller, LatLng loc) {
        item = it;
        this.price = price;
        user = seller;
        location.add(loc.latitude);
        location.add(loc.longitude);
        //expDate = new ArrayList<>();
        //expDate.add(90);
    }

    //useed to create a sales item when reported
    //location will be set in a later process
    //through set location
    public ItemOnSale(String it, double price, String seller) {
        item = it;
        this.price = price;
        user = seller;
        location = new ArrayList<Double>();
        location.add(0, 33.7550);
        location.add(1, -84.3900);
    }

    public void setLocation(LatLng loc) {
        location.remove(0);
        location.remove(1);
        location.add(loc.latitude);
        location.add(loc.longitude);
    }

    public String getItem() { return item; }

    public double getPrice() { return price; }

    public ArrayList<Double> getLocation() { return location; }

    public String getUser() { return user;}

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
