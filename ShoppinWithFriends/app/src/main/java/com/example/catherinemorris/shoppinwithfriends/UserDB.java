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

import android.content.Context;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by joanchen on 2/18/15.
 */
public class UserDB extends android.app.Application  {

    private Firebase myFirebaseRef;
    private boolean loggedIn = true;

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
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
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

    public void addFriend(String user, User friend) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase friendRef = myFirebaseRef.child(user).child("friends");
        Query queryRef = myFirebaseRef.child(user).orderByChild("user").equalTo(user);
        friendRef.push().setValue(friend);

        ref.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });

    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
