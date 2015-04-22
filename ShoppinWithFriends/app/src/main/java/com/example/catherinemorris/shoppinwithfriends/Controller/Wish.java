package com.example.catherinemorris.shoppinwithfriends.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A request that has been made for the sale of a particular item
 * Created by Caitlin Morris on 2/28/15.
 */
public class Wish implements Serializable {

    private final String item;
    private final String description;
    private final double price;
    private boolean matched;
    private final ArrayList<ItemOnSale> matchedSales = new ArrayList<>();


    public Wish(String it, String des, double price) {
        item = it;
        description = des;
        this.price = price;
        matched = false;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<ItemOnSale> getSales() { return matchedSales; }

    public void addSale(ItemOnSale sale) { matchedSales.add(sale); }

    public boolean getMatched() { return matched; }

    public void foundItem() { matched = true;}

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
