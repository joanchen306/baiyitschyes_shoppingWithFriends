package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.app.AlertDialog;
import android.content.Context;

import com.firebase.client.Firebase;

public class Registration extends ActionBarActivity {


    private final UserDB db = new UserDB();

    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Firebase.setAndroidContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    /**
     * This method reads all the text boxes and registers the users
     * should show warnings if the user is already registered
     * or if any of the text boxes are empty
     * If succeed, this should bring the user to their homepage
     *
     * @param view used to overwrite method
     */

    public void sendMessageJoin(View view) {
        User myU;

        EditText mUserView = (EditText) this.findViewById(R.id.UserField);
        EditText mPassView = (EditText) this.findViewById(R.id.PassField);
        EditText mEmailView = (EditText) this.findViewById(R.id.EmailField);
        EditText mRePassView = (EditText) this.findViewById(R.id.RePassField);

        String mUser = mUserView.getText().toString();
        String mEmail = mEmailView.getText().toString();
        String mPass = mPassView.getText().toString();
        String mRePass = mRePassView.getText().toString();


        if (!mUser.equals("")) {
            if (mPass != null && mRePass != null) {
                if (mPass.equals(mRePass) && !mUser.equals("") && mEmail != null) {
                    myU = new User(mUser, mEmail, mPass);
                    if (db.isLoggedIn()) {
                        db.addUser(myU);
                        Intent i = new Intent("android.Login");
                        //i.putExtra("User", myU);
                        startActivity(i);
                    } else {
                        //myU = null;
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage("Please make sure you have entered valid email.");
                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                }

            }
        }
    }


    /**
     * Closes the Registration Activity and returns to the Welcome screen.
     * @param view used to overwrite method
     */
    public void sendMessageBack(View view) {
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
