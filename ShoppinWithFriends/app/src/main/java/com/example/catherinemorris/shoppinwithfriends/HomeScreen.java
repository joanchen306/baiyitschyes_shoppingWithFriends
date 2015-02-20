package com.example.catherinemorris.shoppinwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class HomeScreen extends ActionBarActivity {

    User myU;
    UserDB db = new UserDB();

    /**
     * Overrides onCreate to store the User who is currently logged in
     * to myU
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        myU = (User) getIntent().getSerializableExtra("User");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
     * Logs you out of Application. It both moves you to Welcome screen and
     * logs you out of the database.
     * @param view
     */
    public void sendMessageLogout(View view) {
        Button button = (Button) view;
        db.logout();
        startActivity(new Intent(this, Welcome.class));
    }

    /**
     * Opens the FriendList activity and sends the logged in User's
     * information to the actiity via .putExtra()
     * @param view
     */
    public void openFriends(View view) {
        Button button = (Button) view;
        Intent i = new Intent("android.FriendList");
        i.putExtra("User", myU);
        startActivity(i);
        //startActivity(new Intent(this, FriendList.class));
    }
}