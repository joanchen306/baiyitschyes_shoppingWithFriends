package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by James Nugent on 2/18/2015.
 */
public class User {
    private ArrayList<User> friendList;
    private String username;
    private String password;
    private String email;
    private int rating;
    private int numSales;

    private UserDB db = new UserDB();


    public User(String email, String pass) {
        this.email = email;
        password = pass;
    }


    public User(String user, String email, String pass) {
        username = user;
        this.email = email;
        password = pass;
        rating = 0;
        numSales = 0;
        db.authUser(this);
        db.addUser(this);
    }

    //use to create the Array of Users
    public User(String user, String email, String pass, int ra, int nSales) {
        username = user;
        this.email = email;
        password = pass;
        rating = ra;
        numSales = nSales;
    }

    public String getUser() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return password;
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