package com.example.catherinemorris.shoppinwithfriends;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendList extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();

    String[] myFriends = null;
    ArrayList<String> friendN = db.friendN;

    Firebase myFirebaseRef;

    private EditText mUserText;

    final Context context = this;

    /**
     * Overrides onCreate() to store the User who is currently logged in into
     * myU. Creates a String array by parsing through the stored friendList in
     * the database. Puts that String array into the ListView.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        Firebase.setAndroidContext(this);

        myU = (User) getIntent().getSerializableExtra("User");
        myFriends = new String[friendN.size()];
        for (int i = 0; i < friendN.size(); i++) {
            myFriends[i] = friendN.get(i);
        }
        ListView lv = (ListView) findViewById(R.id.friendList);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                myFriends);

        lv.setAdapter(friendAdapt);
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

    /**
     * CLoses the current Activity
     * @param view
     */
    public void goHomeScreen(View view) {
        finish();
    }

    /**
     * Finds the string stored in the Edit Text. Adds the User that has that
     * username to User's friendList. Sets the text back to null.
     * @param view
     */
    public void addFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        final String friend = mUserText.getText().toString();

        Log.d("Is this working?","Yes it " + friend);

        db.addFriend(myU, friend,context, (ListView) findViewById(R.id.friendList));

        mUserText.setText("");
    }

    public void deleteFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        String un = mUserText.getText().toString();
        if(!friendN.contains(un)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("you don't have a friend named: " + un);
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            friendN.remove(un);
            myU.deleteFriend(un);
            Log.d("deleteFriends is called", un);
            mUserText.setText("");
            friendN = db.friendN;
            myFriends = new String[friendN.size()];
            for (int i = 0; i < friendN.size(); i++) {
                myFriends[i] = friendN.get(i);
            }
            ListView lv = (ListView) findViewById(R.id.friendList);
            ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    myFriends);

            lv.setAdapter(friendAdapt);
        }
    }

}