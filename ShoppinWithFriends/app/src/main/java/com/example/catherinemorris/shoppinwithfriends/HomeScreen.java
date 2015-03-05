package com.example.catherinemorris.shoppinwithfriends;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class HomeScreen extends ActionBarActivity {

    User myU;
    Sale mySale;
    UserDB udb;

    Context context = this;

    ArrayList<Sale> wishlist = new ArrayList<>();
    String[] wishes = null;

    /**
     * Overrides onCreate to store the User who is currently logged in
     * to myU
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Firebase.setAndroidContext(this);


        //gets and maybe prints wishlist
        myU = (User) getIntent().getSerializableExtra("User");

        String username = myU.getUser();
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(username).child("wishlist").orderByKey();


        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                        Map<String, Map<String, Object>> list = (Map<String, Map<String, Object>>) snapshot.getValue();
                        for (Map<String, Object> itemMap : list.values()) {
                            String it = (String) itemMap.remove("item");
                            String des = (String) itemMap.remove("description");
                            double price = (double) itemMap.remove("price");
                            Sale item = new Sale(it, des, price);
                            Log.d("This is the item added at get: ", it);
                            if (wishlist != null && !wishlist.contains(item)) {
                                wishlist.add(item);
                                myU.setWishlist(wishlist);
                            }
                    }

                    wishes = new String[wishlist.size()];
                    for (int i = 0; i < wishlist.size(); i++) {
                        wishes[i] = wishlist.get(i).getItem();
                    }

                    final ListView lv = (ListView) findViewById(R.id.myWishlist);
                    ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(context,
                            android.R.layout.simple_list_item_1,
                            wishes);

                    lv.setAdapter(friendAdapt);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("The read failed: ", "too bad");
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
     * Logs you out of Application. It both moves you to Welcome screen and
     * logs you out of the database.
     * @param view
     */
    public void sendMessageLogout(View view) {
        Button button = (Button) view;
        myU.logout();
        startActivity(new Intent(this, Welcome.class));
    }

    /**
     * Opens the FriendList activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view
     */
    public void openFriends(View view) {
        Button button = (Button) view;
        Intent i = new Intent("android.FriendList");
        i.putExtra("User", myU);
        startActivity(i);
        //startActivity(new Intent(this, FriendList.class));
    }

    /**
     * Opens the request sale Activity
     * @param view
     */
    public void createSale(View view) {
        Button button = (Button) view;
        Intent i = new Intent("android.Enter_Item_Request");
        i.putExtra("User", myU);
        startActivity(i);
    }
}