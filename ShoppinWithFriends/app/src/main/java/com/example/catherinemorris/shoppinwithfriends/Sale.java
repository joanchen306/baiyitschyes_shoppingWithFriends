package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

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
    private String username;
    private boolean matched;
    private ArrayList<String> matchIDList;

    User myU;
    SaleDB db = new SaleDB();

    public Sale(String it, String des, double price, User u) {
        item = it;
        description = des;
        this.price = price;
        user = u;
        username = myU.getUser();
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


    public User getUser() {
        return user;
    }

}
