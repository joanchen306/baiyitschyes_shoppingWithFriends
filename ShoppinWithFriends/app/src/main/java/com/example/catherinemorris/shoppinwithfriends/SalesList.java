package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SalesList extends ActionBarActivity {

    User myU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);

        myU = (User) getIntent().getSerializableExtra("User");
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
     * @param view
     */
    public void openFriends(View view) {
        Button button = (Button) view;
        Intent i = new Intent("android.FriendList");
        i.putExtra("User", myU);
        startActivity(i);
    }

    public void sendSettings(View view) {
        Intent i = new Intent("android.Settings");
        i.putExtra("User", myU);
        startActivity(i);
    }

    public void goToMyWish(View view) {
        Intent i = new Intent("android.HomeScreen");
        i.putExtra("User", myU);
        startActivity(i);
    }
}
