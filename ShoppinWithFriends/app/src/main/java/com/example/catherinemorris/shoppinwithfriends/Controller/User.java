package com.example.catherinemorris.shoppinwithfriends.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import com.example.catherinemorris.shoppinwithfriends.Model.UserDB;
/**
 * A User object that is the main variable carrying through the app
 */
public class User implements Serializable{
    private ArrayList<Wish> wishlist = new ArrayList<>();
    private final ArrayList<String> friendList = new ArrayList<>();
    private final String username;
    private final String password;
    private final String email;
    private long rating = 0;
    private long numSales = 0;
    private long numRate = 0;

    private final UserDB db = new UserDB();

    /**
     * A test User constructor that creates a User with only a String for
     * the username
     * @param user used to overwrite method
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
        db.addUser(this);
    }

    //use to create the Array of Users
    public User(String user, String email, String pass, long ra, long nSales, long nRate) {
        username = user;
        this.email = email;
        password = pass;
        rating = ra;
        numSales = nSales;
        numRate = nRate;
    }

    public void setWishlist(ArrayList<Wish> wl) {
        wishlist = wl;
    }

    /**
     * Returns User's Username
     * @return name of user
     */
    public String getUser() {
        return username;
    }

    /**
     * Returns User's Email
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns User's Password
     * @return password of user
     */
    public String getPassWord() {
        return password;
    }

    /**
     * Returns User's Rating
     * @return rating of user
     */
    public long getRate() {
        return rating;
    }

    /**
     * Returns User's Number of Sales
     * @return Number of sales user has reported
     */
    public long getNumSales() {
        return numSales;
    }

    /**
     * Returns User's friend list in the form of an ArrayList of
     * String usernames
     * @return an Array of friends
     */
    public ArrayList<String> getFriends() {
        return friendList;
    }

    public ArrayList<Wish> getWishlist() {
        return wishlist;
    }

    /**
     * Increments sale counter
     */
    public void addSale() {
        numSales++;
    }

    /**
     * Adds a rating to use and updates overall stars
     */
    public void addRate(int rate) {
        long totRate = rate + numRate * rating;
        numRate++;
        rating = totRate / numRate;
    }

    //public void deleteUser(User user) {
        //friendList.remove(user.getUser());
    //}

    //public void login() {

//    }

    /**
     * Invokes the database logout method
     */
    public void logout() {
        db.logout();
    }

    /**
     * Removes a given friend from the ArrayList of Users
     */
    public void deleteFriend(String deleteU) {
        db.deleteFriend(this, deleteU);
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