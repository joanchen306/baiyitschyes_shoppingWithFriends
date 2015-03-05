package com.example.catherinemorris.shoppinwithfriends;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by James Nugent on 3/5/2015.
 */
public class ItemOnSale implements Serializable {


    private String item;
    private String description;
    private double price;
    private User user;
    private String location;

    public ItemOnSale(String it, String des, double price, User seller, String loc) {
        item = it;
        description = des;
        this.price = price;
        user = seller;
        location = loc;
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