package com.example.catherinemorris.shoppinwithfriends;


import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.MutableData;
import com.firebase.client.AuthData;

import java.util.regex.Pattern;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by joanchen on 2/18/15.
 */
public class UserDB extends android.app.Application  {

    private Firebase myFirebaseRef;
    private boolean loggedIn = true;
    private int registered = -1;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

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

    public void addUser(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase userRef = myFirebaseRef.child("userInfo");
        userRef.child(u.getUser()).setValue(u);
    }

    public void login(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        myFirebaseRef.authWithPassword(u.getEmail(), u.getPassWord(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                loggedIn = true;
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                loggedIn = false;
            }
        });

    }

    public ArrayList<User> getdata(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("email").equalTo(u.getEmail());
        Log.d("email is", u.getEmail());
        final ArrayList<User> list = new ArrayList<>();

            queryRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    HashMap<String, Object> users = (HashMap<String, Object>) snapshot.getValue();
                    for (Object user : users.values()) {
                        HashMap<String, Object> userMap = (HashMap<String, Object>) user;
                        String username = (String) userMap.remove("user");
                        Log.d("query successful with username", username);
                        String password = (String) userMap.remove("passWord");
                        String email = (String) userMap.remove("email");
                        long rating = (long) userMap.remove("rate");
                        long sales = (long) userMap.remove("numSales");
                        list.add(new User(username, password, email, rating, sales));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });

        list.add(new User("joanmouse", "joajoan11", "joan.chen@gatech.edu", 9, 70));
        return list;
    }

    public void addFriend(final User u, final String friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("user").equalTo(friend);
        final String username = u.getUser();

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Firebase friendRef = myFirebaseRef.child("userInfo").child(username).child("friends");
                    friendRef.child(friend).setValue(snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getRegistered() { return registered; }
}
