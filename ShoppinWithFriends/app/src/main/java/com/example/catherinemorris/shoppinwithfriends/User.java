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
    private long rating = 0;
    private long numSales = 0;
    private long numRate = 0;

    private UserDB db = new UserDB();

    /**
     * A test User constructor that creates a User with only a String for
     * the username
     * @param user
     */
    public User (String user) {
        username = user;
        password = "pass";
        email = "basic@gmail.com";
    }

    /**
     * A test User constructor that creates a User with only a String for
     * the username and a String for the password
     * @param user, pass
     */
    public User(String user, String pass) {
        username = user;
        password = pass;
        email = "basic@gmail.com";


    }

    /**
     * The User constructor that creates a User with a username, password,
     * and email.
     * @param user, email, pass
     */
    public User(String user, String email, String pass) {
        username = user;
        this.email = email;
        password = pass;
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

    /**
     * Returns User's Username
     * @return
     */
    public String getUser() {
        return username;
    }

    /**
     * Returns User's Email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns User's Password
     * @return
     */
    public String getPassWord() {
        return password;
    }

    /**
     * Returns User's Rating
     * @return
     */
    public long getRate() {
        return rating;
    }

    /**
     * Returns User's Number of Sales
     * @return
     */
    public long getNumSales() {
        return numSales;
    }

    /**
     * Returns User's friend list in the form of an ArrayList of
     * String usernames
     * @return
     */
    public ArrayList<String> getFriends() {
        return friendList;
    }

    /**
     * Increments sale counter
     * @return
     */
    public void addSale() {
        numSales++;
    }

    /**
     * Adds a rating to use and updates overall stars
     * @return
     */
    public void addRate(int rate) {
        long totRate = rate + numRate * rating;
        numRate++;
        rating = totRate / numRate;
    }

    /**
     * Adds a User to both the FriendLit of Users and the database
     * @return
     */
    public void addUser(User user) {
        friendList.add(user.getUser());
        db.addFriend(this, user.getUser());
    }

    public void login() {

    }

    /**
     * Invokes the database logout method
     * @return
     */
    public void logout() {
        db.logout();
    }

    /**
     * Removes a given friend from the ArrayList of Users
     * @return boolean
     */
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