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


public class Settings extends ActionBarActivity {

    private String[] options = {"Request Sale", "Add Sale", "Preferences", "Logout"};
    User myU;

    private Settings set = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myU = (User) getIntent().getSerializableExtra("User");

        final ListView lv = (ListView) findViewById(R.id.settingOpts);
        ArrayAdapter<String> friendAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                options);

        lv.setAdapter(friendAdapt);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String friend = (String) lv.getItemAtPosition(position);
                if (friend.equals("Request Sale")) {
                    /**
                     * Opens the request sale Activity
                     * @param view
                     */
                    Intent i = new Intent("android.Enter_Item_Request");
                    i.putExtra("User", myU);
                    startActivity(i);
                } else if (friend.equals("Add Sale")) {
                    Intent i = new Intent("android.SaleItem");
                    i.putExtra("User", myU);
                    startActivity(i);
                } else if (friend.equals("Preferences")) {

                } else if (friend.equals("Logout")) {
                    /**
                     * Logs you out of Application. It both moves you to Welcome screen and
                     * logs you out of the database.
                     * @param view
                     */
                    myU.logout();
                    startActivity(new Intent(set, Welcome.class));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void goHomeScreen(View view) {
        finish();
    }
}
