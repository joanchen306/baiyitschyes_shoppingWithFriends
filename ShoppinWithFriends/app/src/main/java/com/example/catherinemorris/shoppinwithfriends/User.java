package com.example.catherinemorris.shoppinwithfriends;

import java.util.ArrayList;

/**
 * Created by James Nugent on 2/18/2015.
 */
public class User {
    ArrayList<User> friendList;
    String username;
    private String password;
    String email;
    int rating;
    int numSales;

    public User(String user, String email, String pass) {
        username = user;
        this.email = email;
        password = pass;
        rating = 0;
        numSales = 0;
    }

    public String getUser() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getRate() {
        return rating;
    }

    public int getNumSales() {
        return numSales;
    }

    public ArrayList<User> getFriends() {
        return friendList;
    }

    public void addSale(int rate) {
        int totStar = rate + numSales * rating;
        numSales++;
        rating = totStar / numSales;
    }

    public void addFriend(User newU) {
        friendList.add(newU);
    }
}