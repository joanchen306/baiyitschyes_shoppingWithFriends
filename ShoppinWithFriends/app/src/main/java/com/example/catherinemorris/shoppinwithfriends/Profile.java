package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.RatingBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.Map;


public class Profile extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();
    private RatingBar ratingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Firebase.setAndroidContext(this);

        myU = (User) getIntent().getSerializableExtra("User");

        //final String username = getIntent().getStringExtra("Friend");
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("user").equalTo("annoyingOrange");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> infoMap = (Map<String, Object>) snapshot.getValue();
                String email = (String) infoMap.remove("email");
                long rate = (long) infoMap.remove("rate");

                TextView currentUsername = (TextView)findViewById(R.id.currentUsername);
                currentUsername.setText("annoyingOrange");

                TextView currentEmail = (TextView)findViewById(R.id.currentEmail);
                currentEmail.setText(email);
                ;
                TextView currentRating = (TextView)findViewById(R.id.currentRating);
                currentRating.setText(""+rate);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
