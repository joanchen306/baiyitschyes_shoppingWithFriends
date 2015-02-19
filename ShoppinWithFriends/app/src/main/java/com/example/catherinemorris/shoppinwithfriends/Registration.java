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

import java.util.regex.Matcher;

public class Registration extends ActionBarActivity {

    //User1 u= new User1();
    private EditText mUserView;
    private EditText mPassView;
    private EditText mEmailView;
    private EditText mRePassView;

    final Context context = this;

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
        	//check if the username is already registered
            //if (u.findUsername(mUser)) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Invalid Email");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
            //}

            //check if the email is valid
       /**     if (!mEmail.matches("\\w+@\\w.(com|gov|edu|org)")) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Invalid Email");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }


             if (mPass != null && mRePass != null) {
                if (mPass.equals(mRePass) && mUser != null && mEmail != null) {
                    u.addUser(mUser, mPass, mEmail);
                    startActivity(new Intent(this, HomeScreen.class));
                }   
          */  }

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
