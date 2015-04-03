package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * this class creates a list of matched sales when there are more than one matches
 */

public class FoundSaleList extends ActionBarActivity {



    /**
     * Defines what a FoundSaleList looks like when it is opened. First it creates a ListView
     * that has a listener that will be called if someone touches the list. From there, it
     * determines what element was touched and sends the info to that Intent.
     * @param savedInstanceState used to overwrite method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_sale_list);

        Wish myWish;
        String[] mySales;


        myWish = (Wish) getIntent().getSerializableExtra("Wish"); //pulls in the Wish that the User clicked on in HomeScreen

        final ArrayList<ItemOnSale> salesList = myWish.getSales();
        mySales = new String[salesList.size()];
        for (int i = 0; i < salesList.size(); i++) {
            mySales[i] = salesList.get(i).getItem() + (i + 1);
        } //Created an array, mySales, that has a String list of all sales that match the wish
        final ListView lv = (ListView) findViewById(R.id.FoundSales);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                mySales); //Adds the list of Strings into the listView

        lv.setAdapter(friendAdapt);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sale = (String) lv.getItemAtPosition(position);
                Intent i = new Intent("android.SaleConnection");
                int num = 1;
                for (ItemOnSale saleMe : salesList) {
                    String item = saleMe.getItem() + num;
                    if (item.equals(sale)) {
                        i.putExtra("Sale", saleMe);
                        break;
                    }
                    num++;
                }
                startActivity(i);
            }
        }); //A listener to both determine what Sale was clicked on, and to subsequently open
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_found_sale_list, menu);
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
    public void goBack(View view) {
        finish();
    }
}
