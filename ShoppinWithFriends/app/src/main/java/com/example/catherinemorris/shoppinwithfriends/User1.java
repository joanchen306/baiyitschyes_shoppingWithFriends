package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;







/**
 * The user1 class creates static lists of usernames and passwords when a user registers
 *This will be transferred into the database 
 */
public class User1 extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }
    
}
