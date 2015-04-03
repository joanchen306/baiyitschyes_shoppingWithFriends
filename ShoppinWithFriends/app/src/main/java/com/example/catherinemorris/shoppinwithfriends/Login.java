package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;


public class Login extends ActionBarActivity {

    private User myU;
    private UserDB udb = new UserDB();
    private EditText mUserView;
    private EditText mPasswordView;

    private static boolean loggedIn = false;


    private final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        Firebase.setAndroidContext(this);

        if (this.getParent() instanceof Registration) {
            myU = (User) getIntent().getSerializableExtra("User");

            mUserView = (EditText) this.findViewById(R.id.UserField);
            mPasswordView = (EditText) this.findViewById(R.id.PassField);

            mUserView.setText(myU.getUser());
            mPasswordView.setText(myU.getPassWord());
        }
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

        // Store values at the time of the login attempt.
        mUserView = (EditText) this.findViewById(R.id.UserField);
        mPasswordView = (EditText) this.findViewById(R.id.PassField);

        final String username = mUserView.getText().toString();
        final String password = mPasswordView.getText().toString();

        logIn(username, password);

    }

    public void logIn(final String username, final String password) {

        if (!username.equals("") && !password.equals("")) {
            Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
            Query queryRef = myFirebaseRef.child("userInfo").child(username);

            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if(snapshot == null || snapshot.getValue() == null) {
                        Log.d("Not registered", "yes");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage(R.string.common_google_play_services_invalid_account_text)
                                .setTitle(R.string.common_google_play_services_invalid_account_title)
                                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User clicked OK button
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    } else {
                        Map<String, Object> userMap = (Map<String, Object>) snapshot.getValue();
                        String pass = (String) userMap.remove("passWord");
                        if (password.equals(pass)) {
                            udb.getFriends(username);
                            User userFile = new User(username, password);

                            setLoggedIn(true);
                            Intent i = new Intent("android.HomeScreen");
                            i.putExtra("User", userFile);
                            startActivity(i);

                        }
                        else {
                            Log.d("Wrong PassWord", "yes");
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                            builder1.setMessage("Wrong Password.");
                            builder1.setCancelable(true);
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });

        } else {
            Log.d("Blank", "yes");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No information should be left blank.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    public void setLoggedIn(boolean bool) {
        Log.d("is logged in", "yes");
        loggedIn = bool;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }
}