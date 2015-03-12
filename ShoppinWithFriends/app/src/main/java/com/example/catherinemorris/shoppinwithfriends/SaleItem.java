package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;


public class SaleItem extends ActionBarActivity {

    User myU;
    Firebase myFirebaseRef;

    private EditText itemName;
    private EditText itemPrice;
    private EditText itemLoc;

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

        final String item = itemName.getText().toString();
        final String loc = itemLoc.getText().toString();
        final double price = Double.parseDouble(itemPrice.getText().toString());

        final ItemOnSale saleMe = new ItemOnSale(item, price, myU, loc);
        final String username = myU.getUser();
    }
}
