package com.example.catherinemorris.shoppinwithfriends.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.widget.AdapterView.OnItemClickListener;
import com.example.catherinemorris.shoppinwithfriends.Controller.User;
import com.example.catherinemorris.shoppinwithfriends.Model.UserDB;
import com.example.catherinemorris.shoppinwithfriends.R;
/**
 * Retrieves the List of Friends from the User Object and allows the user to
 * add and delete friends.
 */

public class FriendList extends ActionBarActivity {

    private User myU;
    private final UserDB db = new UserDB();

    private String[] myFriends = null;
    private ArrayList<String> friendN = new ArrayList<>();

    private Firebase myFirebaseRef;

    private EditText mUserText;

    private final Context context = this;

    private AlertDialog.Builder builder1;
    private String alertM;

    /**
     * Overrides onCreate() to store the User who is currently logged in into
     * myU. Creates a String array by parsing through the stored friendList in
     * the database. Puts that String array into the ListView.
     * @param savedInstanceState used to overwrite method
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        Firebase.setAndroidContext(this);
        friendN = db.getFriendL();
        myU = (User) getIntent().getSerializableExtra("User");
        myFriends = new String[friendN.size()];
        for (int i = 0; i < friendN.size(); i++) {
            myFriends[i] = friendN.get(i);
        }
        final ListView lv = (ListView) findViewById(R.id.friendList);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                myFriends);

        lv.setAdapter(friendAdapt);
        Firebase.setAndroidContext(this);

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String friend = (String) lv.getItemAtPosition(position);
                Intent i = new Intent("android.Profile");
                i.putExtra("Friend", friend);
                startActivity(i);
            }
        });

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
     * Closes the current Activity
     * @param view used to overwrite method
     */
    public void goHomeScreen(View view) {
        finish();
    }

    /**
     * Finds the string stored in the Edit Text. Adds the User that has that
     * username to User's friendList. Sets the text back to null.
     * @param view used to overwrite method
     */
    public void addFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        final String friend = mUserText.getText().toString();

        myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").orderByChild("user").equalTo(friend);

        final String username = myU.getUser();
        final ListView lv = (ListView) findViewById(R.id.friendList);

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    friendN.add(friend);
                    if (myFirebaseRef.child("userInfo").child(username).child("friends") != null) {

                        Firebase friendRef = myFirebaseRef.child("userInfo").child(username).child("friends");
                        Map<String, Object> friends = new HashMap<>();
                        friends.put(friend, friend);
                        friendRef.updateChildren(friends);
                    }

                    builder1 = new AlertDialog.Builder(context);
                    alertM = "You have added " + friend + " as a new friend :)";
                    builder1.setMessage(alertM);
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                    ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, friendN);
                    lv.setAdapter(friendAdapt);

                }
                else {
                    builder1 = new AlertDialog.Builder(context);
                    alertM = "This user does not exist";
                    builder1.setMessage(alertM);
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mUserText.setText("");
    }

    public String getMessage() {
        return alertM;
    }

    public void deleteFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        String un = mUserText.getText().toString();

        un = deleteFriend(un);
        if(un == null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("You don't have a friend named: " + un);
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            friendN.remove(un);
            myU.deleteFriend(un);
            mUserText.setText("");
            friendN = db.getList();

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("You have deleted " + un + " as a friend.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();

            myFriends = new String[friendN.size()];
            for (int i = 0; i < friendN.size(); i++) {
                myFriends[i] = friendN.get(i);
            }
            ListView lv = (ListView) findViewById(R.id.friendList);
            ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    myFriends);

            lv.setAdapter(friendAdapt);
        }
    }

    public String deleteFriend(String un) {
        if (!friendN.contains(un)) {
            return null;
        } else {
            friendN.remove(un);
            return un;
        }
    }

    public ArrayList<String> globalfriend = new ArrayList<>();

    public void addFriend(String un) {
        globalfriend.add("joanmouse");
        globalfriend.add("jnugent6");
        globalfriend.add("dillon");
        if (globalfriend.contains(un) && !friendN.contains(un)) {
            friendN.add(un);
        }
    }

    public void setFriendN(ArrayList<String> list) {
        friendN = list;
    }

    public ArrayList<String> getFriendN() {
        return friendN;
    }



}