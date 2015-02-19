package com.example.catherinemorris.shoppinwithfriends;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.ArrayList;


public class FriendList extends ActionBarActivity {

    public static final String[] myFriends = {
        "Friend 1",
        "Friend 2",
        "Friend 3"
    };

    Firebase mFirebaseRef;
    String username;
    UserSQL mydb;
    private ListView obj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        ListView view = (ListView)findViewById(R.id.friendList);
        view.setAdapter(new EnhancedListAdapter(this, myFriends));

        mFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
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

    public void goHomeScreen() {
        finish();
    }
}
