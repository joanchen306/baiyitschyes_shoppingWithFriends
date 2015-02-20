package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Context;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.AuthData;

import java.util.ArrayList;


public class Login extends ActionBarActivity {

    /**
     * private static final String[] DUMMY_CREDENTIALS = new String[]{
     * "foo@example.com:hello", "bar@example.com:world"
     * };
     */
    User myU;

    UserDB db = new UserDB();
    private EditText mUserView;
    private EditText mPasswordView;


    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        Firebase.setAndroidContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    /**
     * Closes the Login Activity and returns to the Welcome screen.
     * @param view
     */
    public void sendMessageBack(View view) {
        Button button = (Button) view;
        finish();
    }

    /**
     * This methods allows the user to login if they 
     * entered the correct password and username
     * should bring the user to their homepage
     * @param view
     * @return none
     */
    public void sendMessageLogin(View view) {
        Button button = (Button) view;

        // Store values at the time of the login attempt.
        mUserView = (EditText) this.findViewById(R.id.UserField);
        mPasswordView = (EditText) this.findViewById(R.id.PassField);

        String email = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();


        if (!email.equals("") && !password.equals("")) {
            User userFile = new User(email, password);
            db.login(userFile);
            if (db.isLoggedIn()) {
                Intent i = new Intent("android.HomeScreen");
                i.putExtra("User", userFile);
                startActivity(i);
            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Invalid Email Address or Password");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }
}