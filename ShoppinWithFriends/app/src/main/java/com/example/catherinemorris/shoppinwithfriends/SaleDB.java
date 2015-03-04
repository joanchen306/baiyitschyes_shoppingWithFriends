package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

/**
 * Created by joanchen on 3/4/15.
 */
public class SaleDB  extends android.app.Application {
    User myU;
    Firebase myFirebaseRef;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    public void addSales(Sale s) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase saleRef = myFirebaseRef.child("sales");
        saleRef.push().setValue(s);
    }


}
