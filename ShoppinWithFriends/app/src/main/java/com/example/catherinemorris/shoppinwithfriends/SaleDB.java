package com.example.catherinemorris.shoppinwithfriends;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by joanchen on 3/4/15.
 */
public class SaleDB  extends android.app.Application  implements Serializable {
    User myU;
    Firebase myFirebaseRef;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    public void addSales(Sale s) {
        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Firebase saleRef = myFirebaseRef.child("userInfo").child(myU.getUser()).child("wishlist");
        saleRef.push().setValue(s);
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
