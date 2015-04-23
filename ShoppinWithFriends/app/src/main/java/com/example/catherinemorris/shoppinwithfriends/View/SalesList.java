package com.example.catherinemorris.shoppinwithfriends.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.example.catherinemorris.shoppinwithfriends.Controller.User;
import com.example.catherinemorris.shoppinwithfriends.Controller.ItemOnSale;
import com.example.catherinemorris.shoppinwithfriends.R;

public class SalesList extends ActionBarActivity {

    private User myU;
    private String[] globalSales;
    private final ArrayList<ItemOnSale> saleList = new ArrayList<>();
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);
        Firebase.setAndroidContext(this);

        //goes to the database and gets all the global sales
        myU = (User) getIntent().getSerializableExtra("User");
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("globalsales").orderByKey();
        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map<String, Map<String, Object>> list = (Map<String, Map<String, Object>>) snapshot.getValue();
                    for (Map<String, Object> itemMap : list.values()) {
                        String it = (String) itemMap.remove("item");
                        double price = (double) itemMap.remove("price");
                        String um = (String) itemMap.remove("user");
                        try {
                            ArrayList<Double> loc = (ArrayList<Double>) itemMap.remove("location");
                            //ArrayList<Integer> exp = (ArrayList<Integer>) itemMap.remove("expDate");
                            ItemOnSale item = new ItemOnSale(it, price, um, new LatLng(loc.get(0), loc.get(1)));
                            //   Log.d("This is the item added at get: ", it);
                            if (!saleList.contains(item)) {
                                saleList.add(item);
                            }
                        } catch (ClassCastException ex) {
                            ItemOnSale item = new ItemOnSale(it, price, um);
                            //   Log.d("This is the item added at get: ", it);
                            if (!saleList.contains(item)) {
                                saleList.add(item);
                            }
                        }
                    }

                    globalSales = new String[saleList.size()];
                    for (int i = 0; i < saleList.size(); i++) {
                        globalSales[i] = saleList.get(i).getItem();
                    }
                    final ListView lv = (ListView) findViewById(R.id.globalList);
                    ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(context,
                            android.R.layout.simple_list_item_1,
                            globalSales);

                    lv.setAdapter(friendAdapt);
                    Firebase.setAndroidContext(context);
                    Log.d("f", saleList.toString());

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String sale = (String) lv.getItemAtPosition(position);
                            Intent i = new Intent("android.SaleConnection");
                            i.putExtra("User", myU);
                            for (ItemOnSale item : saleList) {
                                if(item.getItem().equals(sale)) {
                                    i.putExtra("Sale", item);
                                    break;
                                }
                            }
                            startActivity(i);
                        }
                    });
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
        getMenuInflater().inflate(R.menu.menu_sales_list, menu);
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
     * @param view used to overwrite method
     */
    public void openFriends(View view) {
        Intent i = new Intent("android.FriendList");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the Setting activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view used to overwrite method
     */
    public void sendSettings(View view) {
        Intent i = new Intent("android.Settings");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the HomeScreen activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view used to overwrite method
     */
    public void goToMyWish(View view) {
        Intent i = new Intent("android.HomeScreen");
        i.putExtra("User", myU);
        startActivity(i);
    }

    /**
     * Opens the FriendWishes activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view used to overwrite method
     */
    public void goToFWish(View view) {
        Intent i = new Intent("android.FriendWishes");
        i.putExtra("User", myU);
        startActivity(i);
    }

}
