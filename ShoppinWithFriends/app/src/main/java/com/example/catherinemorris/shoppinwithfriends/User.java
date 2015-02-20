package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by James Nugent on 2/18/2015.
 */
public class User implements Serializable{
    private ArrayList<User> friendMe = new ArrayList<>();
    private ArrayList<String> friendList = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private long rating;
    private long numSales;
    private long numRate;
    private int registered = 0;

    private UserDB db = new UserDB();


    public User (String user) {
        username = user;
        password = "pass";
        email = "basic@gmail.com";
        rating = 0;
        numRate = 0;
        numSales = 0;
    }

    public User(String user, String pass) {
        username = user;
        password = pass;
        rating = 0;
        numRate = 0;
        numSales = 0;
        //username = user;


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

    public int getRegistered() { return registered; }

    public void addSale() {
        numSales++;
    }

    public void addRate(int rate) {
        long totRate = rate + numRate * rating;
        numRate++;
        rating = totRate / numRate;
    }

    public void addUser(User user) {
        friendList.add(user.getUser());
        db.addFriend(this, user.getUser());
    }

    public void login() {

    }

    public void logout() {
        db.logout();
    }

    public boolean deleteFriend(User deleteU) {
        return friendList.remove(deleteU);
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