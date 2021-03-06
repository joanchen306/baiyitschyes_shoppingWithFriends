package com.example.catherinemorris.shoppinwithfriends.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

import com.example.catherinemorris.shoppinwithfriends.Controller.User;
import com.example.catherinemorris.shoppinwithfriends.Controller.ItemOnSale;
import com.example.catherinemorris.shoppinwithfriends.R;


public class SaleItem extends ActionBarActivity {

    private User myU;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_item);

        myU = (User) getIntent().getSerializableExtra("User"); //pulls in myU from the last Activity
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

    /**
     * Opens the HomeScreen activity and sends the logged in User's
     * information to the activity via .putExtra()
     * @param view used to overwrite method
     */
    public void goHome(View view) {
        Intent i = new Intent("android.HomeScreen");
        i.putExtra("User", myU);
        startActivity(i);
    }


    public void goToMap(View view) {
        ItemOnSale saleItem;
        String it;
        //String pr;

        Intent i = new Intent("android.Map");
        EditText etName = (EditText) this.findViewById(R.id.itemNameField);
        EditText etPrice = (EditText) this.findViewById(R.id.priceField);
        it = etName.getText().toString();
        //pr = etPrice.getText().toString();
        double price = Double.parseDouble(etPrice.getText().toString());
        saleItem = new ItemOnSale(it, price, myU.getUser());
        i.putExtra("User", myU);
        i.putExtra("ItemOnSale", saleItem);
        startActivity(i);
    }

    /**
     * Allows the user to send the information from the EditText fields to the
     * database.
     * @param view
     */
    /**
    public void sendSaleRequest(View view) throws InterruptedException {
        itemName = (EditText) this.findViewById(R.id.itemNameField);
        itemPrice = (EditText) this.findViewById(R.id.priceField);

        String item = itemName.getText().toString(); //Gets Item Double.parseDouble(itemPrice.getText().toString());name String
        double price = Double.parseDouble(itemPrice.getText().toString()); //Gets Item price double

        if (item.equals("") | itemPrice.getText().toString().equals("")) {
            //Error message for if the User tries an illegal input
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No fields should be blank.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            ItemOnSale saleMe = new ItemOnSale(item, price, myU.getUser());
            myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
            Firebase itRef = myFirebaseRef.child("globalsales");
            itRef.push().setValue(saleMe);
            //Message to inform user that the data has been put into the database
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("You have added " + item + " to the global Sales List.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }


        itemPrice.setText("");
        itemName.setText("");
    }
     */
}
