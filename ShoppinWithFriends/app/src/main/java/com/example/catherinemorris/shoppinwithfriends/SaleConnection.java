package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class SaleConnection extends ActionBarActivity {

    ItemOnSale sale;
    User myU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_connection);

        myU = (User) getIntent().getSerializableExtra("User");
        sale = (ItemOnSale) getIntent().getSerializableExtra("Sale");

        TextView currName = (TextView)findViewById(R.id.itemField);
        currentUsername.setText(sale.getItem());

        TextView currPrice = (TextView) findViewById(R.id.priceField);
        currentEmail.setText(sale.getPrice());

        TextView currLoc = (TextView) findViewById(R.id.locationField);
        currentEmail.setText(sale.getLocation());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_connection, menu);
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

    public void goBack(View view) {
        finish();
    }
}
