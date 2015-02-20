package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by James Nugent on 2/18/2015.
 */
public class User {
    private ArrayList<String> friendList = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private long rating;
    private long numSales;
    private long numRate;

    private UserDB db = new UserDB();



    public User(String user, String pass) {
        username = user;
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
    }

    //use to create the Array of Users
    public User(String user, String email, String pass, long ra, long nSales) {
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

    public long getRate() {
        return rating;
    }

    public long getNumSales() {
        return numSales;
    }

    public ArrayList<String> getFriends() {
        return friendList;
    }

    public void addSale() {
        numSales++;
    }

    public void addRate(int rate) {
        long totRate = rate + numRate * rating;
        numRate++;
        rating = totRate / numRate;
    }

    public void addUser(User user) {

    }

    public void addFriend(String u) {
        db.addFriend(this, u);
    }

    public void login() {

    }

    public void logout() {
        db.logout();
    }

    public boolean deleteFriend(User deleteU) {
        return friendList.remove(deleteU);
    }
}