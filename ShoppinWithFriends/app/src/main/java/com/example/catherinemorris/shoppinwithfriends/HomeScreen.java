package com.example.catherinemorris.shoppinwithfriends;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    Wish mySale;
    UserDB udb;

    String found = " (Found!)";
    String looking = " (Still looking!)";


    Context context = this;

    ArrayList<Wish> wishlist = new ArrayList<>();
    ArrayList<ItemOnSale> globalSales = new ArrayList<>();
    String[] wishes;

    /**
     * Overrides onCreate to store the User who is currently logged in
     * to myU. It also uses the database to determine the WishList for each User.
     * Then, it goes through and determines whether or not each Wish has been
     * found in the Global Sales List.
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

        Query queryRef = myFirebaseRef.child("globalsales").orderByKey();


        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map<String, Map<String, Object>> list = (Map<String, Map<String, Object>>) snapshot.getValue();
                    for (Map<String, Object> itemMap : list.values()) {
                        String it = (String) itemMap.remove("item");
                        String loc = (String) itemMap.remove("location");
                        double price = (double) itemMap.remove("price");
                        String um = (String) itemMap.remove("user");

                        ItemOnSale item = new ItemOnSale(it, price, um, loc);
                        Log.d("This is the item added at get: ", it);
                        if (globalSales != null && !globalSales.contains(item)) {
                            globalSales.add(item);
                            Log.d("Matched " + item.getItem() + " , $ " + item.getPrice() + " at ", item.getLocation());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("The read failed: ", "too bad");
            }

        });



        wishes = new String[wishlist.size()];

        queryRef = myFirebaseRef.child("userInfo").child(username).child("wishlist").orderByValue();


        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                        Map<String, Map<String, Object>> list = (Map<String, Map<String, Object>>) snapshot.getValue();
                        for (Map<String, Object> itemMap : list.values()) {
                            String it = (String) itemMap.remove("item");
                            String des = (String) itemMap.remove("description");
                            double price = (double) itemMap.remove("price");
                            Wish item = new Wish(it, des, price);
                            Log.d("This is the item added at get: ", it);
                            if (wishlist != null && !wishlist.contains(item)) {
                                wishlist.add(item);
                                myU.setWishlist(wishlist);
                            }
                    }

                    //compares the wishlist to the global sales list
                    for(Wish currWish : wishlist) {
                        String name = currWish.getItem();
                        for(ItemOnSale currSale : globalSales) {
                            String sale = currSale.getItem();
                            if(name.equals(sale) && currWish.getPrice() >= currSale.getPrice()) {
                                currWish.foundItem();
                                currWish.addSale(currSale);
                            }
                        }
                    }

                    wishes = new String[wishlist.size()];
                    //print out the user's wishlist and mark if it has a match
                    for (int i = 0; i < wishlist.size(); i++) {
                        wishes[i] = wishlist.get(i).getItem();
                        if (wishlist.get(i).getMatched()) {
                            wishes[i] = wishes[i] + found;
                        } else {
                            wishes[i] = wishes[i] + looking;
                        }
                    }

                }

                final ListView lv = (ListView) findViewById(R.id.myWishlist);
                if (wishes == null) {

                }
                ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,
                        wishes);


                lv.setAdapter(friendAdapt);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String wish = (String) lv.getItemAtPosition(position);
                        ArrayList<Wish> wishL = myU.getWishlist();
                        Log.d("this is my wishlist on homescreen ", wishL.toString());
                        for (Wish itemW : wishL) {
                            Log.d("This is the item: ",itemW.getItem());
                            Log.d("This is the wish: ", wish);
                            String it = itemW.getItem() + " (Found!)";
                            if (it.equals(wish)) {
                                if (itemW.getSales().size() > 1) {
                                    Log.d("more than one match! for ", wish);
                                    Intent i = new Intent("android.FoundSaleList");
                                    i.putExtra("Wish", itemW);
                                    i.putExtra("User", myU);
                                    startActivity(i);
                                } else {
                                    Log.d("only one match! for ", wish);
                                    Intent i = new Intent("android.SaleConnection");
                                    ItemOnSale sale = itemW.getSales().get(0);
                                    i.putExtra("Sale", sale);
                                    i.putExtra("User", myU);
                                    startActivity(i);
                                }
                                break;
                            }
                        }
                    }
                });

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
     * Opens the FriendList activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view
     */
    public void openFriends(View view) {
        Button button = (Button) view;
        Intent i = new Intent("android.FriendList");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the Setting activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view
     */
    public void sendSettings(View view) {
        Intent i = new Intent("android.Settings");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the SalesList activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view
     */
    public void goToSalesList(View view) {
        Intent i = new Intent("android.SalesList");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the FriendWishes activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view
     */
    public void goToFWish(View view) {
        Intent i = new Intent("android.FriendWishes");
        i.putExtra("User", myU);
        startActivity(i);
    }
}