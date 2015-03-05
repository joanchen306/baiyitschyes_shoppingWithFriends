package com.example.catherinemorris.shoppinwithfriends;

/**
 * This class displays the username, email, ratings, and number
 * of sales of the user that is selected from the friend's list.
 */


import android.content.Context;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
    private String username;
    Context context = this;


    /**
     * Creates the view by searching in the database for the information
     * of the specified user and displays it
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Firebase.setAndroidContext(this);

        myU = (User) getIntent().getSerializableExtra("User");

        username = getIntent().getStringExtra("Friend");
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(username);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> infoMap = (Map<String, Object>) snapshot.getValue();
                String email = (String) infoMap.remove("email");
                long sales = (long) infoMap.remove("numSales");
                long rate = (long) infoMap.remove("rate");

                TextView currentUsername = (TextView)findViewById(R.id.currentUsername);
                currentUsername.setText(username);

                TextView currentEmail = (TextView) findViewById(R.id.currentEmail);
                currentEmail.setText(email);

                TextView currentSales = (TextView)findViewById(R.id.currentSales);
                currentSales.setText(""+sales);

                TextView currentRating = (TextView)findViewById(R.id.currentRating);
                currentRating.setText(""+rate);
            }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * CLoses the current Activity
     * @param view
     */
    public void goBack(View view) {
        finish();
    }


}

