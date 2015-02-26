package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Profile extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myU = (User) getIntent().getSerializableExtra("User");

        String username = myU.getUser();
        TextView currentUsername = (TextView)findViewById(R.id.currentUsername);
        currentUsername.setText(username);

        String email = myU.getEmail();
        TextView currentEmail = (TextView)findViewById(R.id.currentEmail);
        currentEmail.setText(email);

        String rating = "" + myU.getRate();
        TextView currentRating = (TextView)findViewById(R.id.currentRating);
        currentRating.setText(rating);
    }
}
