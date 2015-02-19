package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import android.content.Context;

/**
 * Created by James Nugent on 2/18/2015.
 */
public class User {
    private ArrayList<User> friendList = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private int rating;
    private int numSales;
    private int numRate;

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
        numRate = 0;
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

    public void addSale() {
        numSales++;
    }

    public void addRate(int rate) {
        int totRate = rate + numRate * rating;
        numRate++;
        rating = totRate / numRate;
    }

    public void addFriend(String username, User newU) {
        db.addFriend(username, newU);
    }

    public boolean deleteFriend(User deleteU) {
        return friendList.remove(deleteU);
    }
}