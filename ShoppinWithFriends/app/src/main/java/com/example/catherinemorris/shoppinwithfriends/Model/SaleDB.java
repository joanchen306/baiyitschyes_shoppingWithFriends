package com.example.catherinemorris.shoppinwithfriends.Model;

import com.example.catherinemorris.shoppinwithfriends.Model.Sale;
import com.example.catherinemorris.shoppinwithfriends.Model.User;
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
        Firebase saleRef = myFirebaseRef.child("userInfo").child(myU.getUser()).child("sales");
        saleRef.push().setValue(s);
    }

}
