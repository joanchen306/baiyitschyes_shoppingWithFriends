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
import java.util.Map;


public class FriendList extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();

    String[] myFriends = null;
    ArrayList<String> friendN = db.friendN;



    private EditText mUserText;

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
        Firebase.setAndroidContext(this);
        //myU = db.userInfoList.get(1);
        Log.d("f" , friendN.toString());

    }


    /*protected void onListItemClick(ListView list, View view, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(list, view, position, id);

        new AlertDialog.Builder(this)
                .setTitle("Hello")
                .setMessage("from " + getListView().getItemAtPosition(position))
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                .show();

        Toast.makeText(ListviewActivity.this,
                "ListView: " + l.toString() + "\n" +
                        "View: " + v.toString() + "\n" +
                        "position: " + String.valueOf(position) + "\n" +
                        "id: " + String.valueOf(id),
                Toast.LENGTH_LONG).show();
    }
>>>>>>> 748d51ae959f1d2fa890a7f95551f048331410bb

    */
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
        finish();
    }

    public void addFriends(View view) {
        mUserText = (EditText) this.findViewById(R.id.userText);
        String un = mUserText.getText().toString();
        myU.addUser(new User(un));
        Log.d("Is this working?","Yes it " + un);
        mUserText.setText("");
    }

}
