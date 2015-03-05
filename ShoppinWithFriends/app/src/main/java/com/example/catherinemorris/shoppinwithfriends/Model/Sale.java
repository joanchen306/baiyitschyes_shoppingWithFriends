package com.example.catherinemorris.shoppinwithfriends.Model;

import java.util.ArrayList;

/**
 * A request that has been made for the sale of a particular item
 * Created by Caitlin Morris on 2/28/15.
 */
public class Sale {

    private String item;
    private String description;
    private double price;
    private User user;
    private boolean matched;
    private ArrayList<String> matchIDList;

    User myU;
    SaleDB db = new SaleDB();

    public Sale(String it, String des, double price) {
        item = it;
        description = des;
        this.price = price;
        matched = false;
        db.addSales(this);
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

}
