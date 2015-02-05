package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Registration extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    public void sendMessageJoin(View view) {
        Button button = (Button) view;
        String user = (String) this.findViewById(R.id.UserField).getContentDescription();
        String email = (String) this.findViewById(R.id.EmailField).getContentDescription();
        String pass = (String) this.findViewById(R.id.PassField).getContentDescription();
        String pass2 = (String) this.findViewById(R.id.PassField2).getContentDescription();
        if (pass.equals(pass2) && user != null && email != null) {
            String password = (String) this.findViewById(R.id.PassField).getContentDescription();
            User1 user1 = new User1();
            user1.setUsername(user);
            user1.setPassword(password);
            user1.setEmail(email);
            startActivity(new Intent("android.HomeScreen"));
        }
    }

    public void sendMessageBack(View view) {
        Button button = (Button) view;
        finish();
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
}
