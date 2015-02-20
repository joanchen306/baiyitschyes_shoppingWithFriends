package com.example.catherinemorris.shoppinwithfriends;


import android.app.AlertDialog;
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
    private boolean loggedIn = false;
    private int registered = -1;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    public void authUser(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        myFirebaseRef.createUser(u.getEmail(), u.getPassWord(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                loggedIn = true;
                return;
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                loggedIn = false;
                return;
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

    public User getdata(User u) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("email").equalTo(u.getEmail());
        final User[] info = new User[1];

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                final ArrayList<User> list = new ArrayList<>();
                HashMap<String, Object> users = (HashMap<String, Object>) snapshot.getValue();
                for(Object user : users.values()) {
                    HashMap<String, Object> userMap = (HashMap<String, Object>) user;
                    String username = (String) userMap.remove("user");
                    String password = (String) userMap.remove("passWord");
                    String email = (String) userMap.remove("email");
                    long rating = (long) userMap.remove("rate");
                    long sales = (long) userMap.remove("numSales");
                    info[0] = new User(username, password, email, rating, sales);
                    }
                }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return info[0];
    }

    public void addFriend(final String email, String friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("user").equalTo(friend);

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Firebase friendRef = myFirebaseRef.child("userInfo").child(email).child("friends");
                    friendRef.push().setValue(snapshot.getValue());
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
