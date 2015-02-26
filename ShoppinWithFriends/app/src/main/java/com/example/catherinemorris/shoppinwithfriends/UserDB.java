package com.example.catherinemorris.shoppinwithfriends;


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
import java.util.HashMap;

/**
 * Created by joanchen on 2/18/15.
 */
public class UserDB extends android.app.Application implements Serializable {

    private Firebase myFirebaseRef;
    private boolean loggedIn = true;
    private int registered = -1;
    private int counter = 0;
    static ArrayList<String> friendN;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    /**
     * Creates a new User account in the database and logs that User in
     * @param u
     */
    public void authUser(final User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        myFirebaseRef.createUser(u.getEmail(), u.getPassWord(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                loggedIn = true;
                Log.d("Successfully created user account with uid: ", u.getEmail());
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                loggedIn = false;
                Log.d("Failed creating user account with uid: ", u.getEmail());
            }
        });
    }

    /**
     * Adds a User to the database
     * @param u
     */

    public void addUser(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("userInfo");
        userRef.child(u.getUser()).setValue(u);
    }

    public void deleteUser(User u) {
        Log.d("UserDB deleteUser is called", "" + u);
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("userInfo");
        Map<String, Object> users = new HashMap<String, Object>();
        users.remove(u.getUser());
        userRef.child(u.getUser()).updateChildren(users);
    }


    /**
     * Stores all the User's info in the respective fields of the database
     * @param u
     */
    public void login(User u) {
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(u.getUser());

        final String pass = u.getPassWord();
        Log.d("email is", u.getUser());


        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot == null || snapshot.getValue() == null) {

                } else {
                    Map<String, Object> userMap = (Map<String, Object>) snapshot.getValue();
                    String password = (String) userMap.remove("passWord");
                    if (pass.equals(password)) {
                        counter++;
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });


        if (counter <= 0) {
            loggedIn = false;
            Log.d("Failed to log in user: ", u.getUser());
        } else {
            loggedIn = true;
            Log.d("Successfully logged in user: ", ""+counter);
        }

        getFriends(u.getUser());
    }

    /**
     * Adds a String name of a friend to the respective field in the current User's
     * field of the database.
     * @param u
     * @param friend
     */
    public void addFriend(final User u, final String friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("user").equalTo(friend);
        final String username = u.getUser();

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (myFirebaseRef.child("userInfo").child(username).child("friends").equals(null)) {
                        myFirebaseRef.child("userInfo").child(username).child("friends").child("name").setValue(friend);
                    } else {
                        Firebase friendRef = myFirebaseRef.child("userInfo").child(username).child("friends");
                        Map<String, Object> friends = new HashMap<String, Object>();
                        friends.put(friend, friend);
                        friendRef.updateChildren(friends);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void deleteFriend(final User u, final String friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        final String username = u.getUser();
        final Firebase friendRef = myFirebaseRef.child("userInfo").child(username).child("friends").child(friend);
        Log.d("UserDB deleteFriend is called", friend);

        friendRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map<String, Object> friends = new HashMap<String, Object>();
                    friends.remove(friend);
                    Log.d("new friend list:", friends.toString());
                    friendRef.updateChildren(friends);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /**
     * Returns an ArrayList of Strings of all the friend's a given User has.
     * @param username
     */
    public void getFriends(final String username) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(username).child("friends").orderByKey();

        friendN = new ArrayList<>();
        friendN.add(username + "'s Friends: ");

        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                        Map<String, Object> users = (Map<String, Object>) snapshot.getValue();
                        for (Object user : users.values()) {
                            String f = (String) user;
                            Log.d("friend", f);
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

        friendN.remove(0);
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
     * @return
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns a negative number if the user is not registered with the database
     * @return
     */
    public int getRegistered() { return registered; }

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
