package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;


public class SaleItem extends ActionBarActivity {

    User myU;
    private Firebase myFirebaseRef;

    private EditText itemName;
    private EditText itemPrice;
    private EditText itemLoc;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_item);

        myU = (User) getIntent().getSerializableExtra("User");
        Firebase.setAndroidContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_item, menu);
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

    public void back(View view) {
        finish();
    }

    public void sendSaleRequest(View view) {
        itemName = (EditText) this.findViewById(R.id.itemNameField);
        itemLoc = (EditText) this.findViewById(R.id.itemLocation);
        itemPrice = (EditText) this.findViewById(R.id.priceField);

        String item = itemName.getText().toString();
        String loc = itemLoc.getText().toString();
        double price = Double.parseDouble(itemPrice.getText().toString());

        if (item.equals("") | loc.equals("") | itemPrice.getText().toString().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No fields should be blank");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {

            ItemOnSale saleMe = new ItemOnSale(item, price, myU, loc);
            Log.d("Tag", "The item is " + item + ". It costs " + price + ". It's from " + loc);
            myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com").child("sales");
            myFirebaseRef.push().setValue(saleMe);
        }

        itemPrice.setText("");
        itemLoc.setText("");
        itemName.setText("");
    }
}
