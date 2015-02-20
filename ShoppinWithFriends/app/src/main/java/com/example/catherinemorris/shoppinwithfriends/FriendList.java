package com.example.catherinemorris.shoppinwithfriends;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.ArrayList;


public class FriendList extends ActionBarActivity {

    UserDB db = new UserDB();

    String[] myFriends;

    User myU;
    String username;

    private EditText mUserText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        myU = (User) getIntent().getSerializableExtra("User");

        ArrayList<User> hisFriends = myU.getFriends();
        myFriends = new String[hisFriends.size()];
        for (int i = 0; i < hisFriends.size(); i++) {
            myFriends[i] = hisFriends.get(i).getUser();
        }
        ListView lv = (ListView) findViewById(R.id.friendList);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                myFriends);

        lv.setAdapter(friendAdapt);
        Firebase.setAndroidContext(this);
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

    public void goHomeScreen(View view) {

        Intent i = new Intent("android.HomeScreen");
        i.putExtra("User", myU);
        finish();
        startActivity(i);
    }

    public void addFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        String un = mUserText.getText().toString();
        myU.addUser(new User(un));
        Log.d("Is this working?","Yes it " + un);
        mUserText.setText("");
    }
}
