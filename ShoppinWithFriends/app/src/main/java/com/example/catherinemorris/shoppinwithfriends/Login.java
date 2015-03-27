package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class Login extends ActionBarActivity {

    /**
     * private static final String[] DUMMY_CREDENTIALS = new String[]{
     * "foo@example.com:hello", "bar@example.com:world"
     * };
     */
    User myU;

    UserDB udb = new UserDB();
    private EditText mUserView;
    private EditText mPasswordView;


    final Context context = this;


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

        final String username = mUserView.getText().toString();
        final String password = mPasswordView.getText().toString();


        if (!username.equals("") && !password.equals("")) {

            final User userFile = new User(username, password);
            Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
            Query queryRef = myFirebaseRef.child("userInfo").child(username);

            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if(snapshot == null || snapshot.getValue() == null) {
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
                            String email = (String) userMap.remove("email");
                            long s = (long) userMap.remove("numSales");
                            long r = (long) userMap.remove("rate");
                           // myU = new User(username, email, pass, r, s, 0);
                            udb.getFriends(username);
                            Intent i = new Intent("android.HomeScreen");
                            i.putExtra("User", userFile);
                            startActivity(i);
                        }
                        else {
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
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No information should be left blank.");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}