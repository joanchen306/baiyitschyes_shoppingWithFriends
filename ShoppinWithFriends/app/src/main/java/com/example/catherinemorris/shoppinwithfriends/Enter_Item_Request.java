package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the Object of the Sale Reports that are reported by users
 */

public class Enter_Item_Request extends ActionBarActivity {

    private User myU;
    private Firebase myFirebaseRef;


    private final Context context = this;

    private final ArrayList<Wish> wishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myU = (User) getIntent().getSerializableExtra("User");
        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enter__item__request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter__item__request, menu);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_enter__item__request, container, false);
        }
    }

    /**
     * When a user sends in an item request it adds in the wishlist on the database
     * @param view Used as an override
     */

    public void sendItemRequest(View view) {
        EditText itemName;
        EditText itemDescription;
        EditText itemPrice;

        itemName = (EditText) this.findViewById(R.id.itemNameField);
        itemDescription = (EditText) this.findViewById(R.id.itemDescriptionField);
        itemPrice = (EditText) this.findViewById(R.id.priceField);

        final String item = itemName.getText().toString();
        final String descrip = itemDescription.getText().toString();
        final double price = Double.parseDouble(itemPrice.getText().toString());

        if (item.equals("") | descrip.equals("") | itemPrice.getText().toString().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No fields should be blank.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {

            final Wish wishItems = new Wish(item, descrip, price);
            final String username = myU.getUser();

            myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
            Firebase queryRef = myFirebaseRef.child("userInfo").child(myU.getUser()).child("wishlist");

            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    wishlist.add(wishItems);
                    if (wishlist.size() == 1) {
                        myFirebaseRef.child("userInfo").child(username).child("wishlist").child(item).setValue(wishItems);
                    } else {
                        Firebase itemRef = myFirebaseRef.child("userInfo").child(username).child("wishlist");
                        Map<String, Object> wish = new HashMap<>();
                        if(wish.containsKey(item)){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                            builder1.setMessage("You have already added " + item + ".");
                            builder1.setCancelable(true);
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        } else {
                            wish.put(item, wishItems);
                            itemRef.updateChildren(wish);
                        }
                    }

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("You have added " + item + " to your wish list.");
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                    Intent i = new Intent("android.HomeScreen");
                    i.putExtra("User", myU);
                    startActivity(i);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });

        }
    }

    public void back(View view) {
        finish();
    }
}
