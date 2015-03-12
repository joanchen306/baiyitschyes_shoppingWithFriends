package com.example.catherinemorris.shoppinwithfriends;

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

import com.firebase.client.Firebase;


public class FoundSaleList extends ActionBarActivity {

    Wish myWish;
    User myU;
    String[] mySales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_sale_list);

        myU = (User) getIntent().getSerializableExtra("User");
        myWish = (Wish) getIntent().getSerializableExtra("Wish");

        ArrayList<ItemOnSale> salesList = myWish.getSales();
        mySales = new String[salesList.size()];
        for (int i = 0; i < salesList.size(); i++) {
            mySales[i] = salesList.get(i);
        }
        final ListView lv = (ListView) findViewById(R.id.salesList);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mySales);

        lv.setAdapter(friendAdapt);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sale = (String) lv.getItemAtPosition(position);
                Intent i = new Intent("android.SaleConnection");
                for (ItemOnSale saleMe : salesList) {
                    if (saleMe.getItem().equals(sale)) {
                        i.putExtra("Sale", saleMe);
                    }
                }
                startActivity(i);
            }
        });
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

    public void goBack(View view) {
        finish();
    }
}
