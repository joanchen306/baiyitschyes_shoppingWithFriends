package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SaleConnection extends ActionBarActivity implements Serializable {

    ItemOnSale sale;
    User myU;


    /**
     * Pulls the information from the previous Activity to populate this Activity with
     * information pertaining to the ItemOnSale in question.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_connection);

        myU = (User) getIntent().getSerializableExtra("User"); //pulls in myU from the last Activity
        sale = (ItemOnSale) getIntent().getSerializableExtra("Sale"); //pulls in the ItemOnSale that the User clicked on in HomeScreen or FoundSaleList

        TextView currName = (TextView)findViewById(R.id.itemField);
        currName.setText(sale.getItem());

        TextView currPrice = (TextView) findViewById(R.id.PriceField);
        currPrice.setText("" + sale.getPrice());
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

    /**
     * Closes the current Activity
     * @param view
     */
    public void goBack(View view) {
        finish();
    }

    private static final long serialVersionUID = 7526471155622776147L;
    /**
     * Always treat de-serialization as a full-blown constructor, by
     * validating the final state of the de-serialized object.
     */
    private void readObject(
            ObjectInputStream aInputStream
    ) throws ClassNotFoundException, IOException {
        //always perform the default de-serialization first
        aInputStream.defaultReadObject();

    }

    /**
     * This is the default implementation of writeObject.
     * Customise if necessary.
     */
    private void writeObject(
            ObjectOutputStream aOutputStream
    ) throws IOException {
        //perform the default serialization for all non-transient, non-static fields
        aOutputStream.defaultWriteObject();
    }
}
