package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Profile extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myU = (User) getIntent().getSerializableExtra("User");
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_profile, menu);
//        return true;
//    }

    public String showUsername() {
        String username = myU.getUser();
        return username;
    }
}
