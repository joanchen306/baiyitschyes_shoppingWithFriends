package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.firebase.client.Firebase;

import java.util.regex.Matcher;

public class Registration extends ActionBarActivity {

<<<<<<< HEAD
=======
    //User1 u= new User1();
>>>>>>> f5ccd9a9e2dade73ca9f17aba02f89f2b79bd0b3
    private EditText mUserView;
    private EditText mPassView;
    private EditText mEmailView;
    private EditText mRePassView;
    private User u;

    UserDB db = new UserDB();

    final Context context = this;

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
     *If succeed, this should bring the user to their homepage
     * @param view
     * @return void 
     *
     */

    public void sendMessageJoin(View view) {
        Button button = (Button) view;
        mUserView = (EditText) this.findViewById(R.id.UserField);
        mPassView = (EditText) this.findViewById(R.id.PassField);
        mEmailView = (EditText) this.findViewById(R.id.EmailField);
        mRePassView = (EditText) this.findViewById(R.id.RePassField);

        String mUser = mUserView.getText().toString();
        String mEmail = mEmailView.getText().toString();
        String mPass = mPassView.getText().toString();
        String mRePass = mRePassView.getText().toString();


        if (mUser != null) {

             if (mPass != null && mRePass != null) {
                if (mPass.equals(mRePass) && mUser != null && mEmail != null) {
                    new User(mUser, mEmail, mPass);
                    if (db.isLoggedIn()) {
                        startActivity(new Intent(this, HomeScreen.class));
                    }
                }
             }

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
