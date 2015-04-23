package com.example.catherinemorris.shoppinwithfriends.Model;


import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Map;
import com.example.catherinemorris.shoppinwithfriends.Controller.User;

/**
 * manages interaction between User and database
 */
public class UserDB extends android.app.Application implements Serializable {

    private Firebase myFirebaseRef;
    private boolean loggedIn = true;

    private static ArrayList<String> friendN;

    public ArrayList<String> retList() {
        return friendN;
    }

    public ArrayList<String> getList() {
        return friendN;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    /**
     * Creates a new User account in the database and logs that User in
     * @param u used to overwrite method
     */
    public void authUser(final User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        myFirebaseRef.createUser(u.getEmail(), u.getPassWord(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                loggedIn = true;
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                loggedIn = false;
            }
        });
    }

    /**
     * Adds a User to the database
     * @param u used to overwrite method
     */

    public void addUser(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("userInfo");
        userRef.child(u.getUser()).setValue(u);
    }


    public ArrayList<String> getFriendL() { return friendN;}
    /**
     * finds the user's friend list and delets the specifies friend if it exists
     * @param u, the User that is currently using the app
     * @param friend, the username of the friend that the user wants to delete
     */


    public void deleteFriend(final User u, final String friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        final String username = u.getUser();
        final Firebase friendRef = myFirebaseRef.child("userInfo").child(username).child("friends").child(friend);

        friendRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    friendRef.setValue(null);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /**
     * Returns an ArrayList of Strings of all the friend's a given User has.
     * @param username used to overwrite method
     */
    public void getFriends(final String username) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(username).child("friends").orderByKey();

        friendN = new ArrayList<>();

        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                        Map<String, Object> users = (Map<String, Object>) snapshot.getValue();
                        for (Object user : users.values()) {
                            String f = (String) user;
                            if (!friendN.contains(f)) {
                                friendN.add((String) user);
                            }
                        }
                    }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("The read failed: ", "too bad");
            }
        });

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String friend = (String)dataSnapshot.getValue();
                if (!friendN.contains(friend)) {
                    friendN.add(friend);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    /**
     * Logs the user out of being the current user in the app, and deauthorizes
     * said user.
     */
    public void logout() {
        loggedIn = false;
        friendN = null;
    }

    /**
     * Returns whether or not a user is logged in to the App
     * @return true if logged in
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns a negative number if the user is not registered with the database
     */


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
