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


public class Registration extends ActionBarActivity {

    User1 u= new User1();
    private EditText mUserView;
    private EditText mPassView;
    private EditText mEmailView;
    private EditText mRePassView;

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
        mUserView = (EditText) this.findViewById(R.id.UserField);
        mPassView = (EditText) this.findViewById(R.id.PassField);
        mEmailView = (EditText) this.findViewById(R.id.EmailField);
        mRePassView = (EditText) this.findViewById(R.id.RePassField);

        String mUser = mUserView.getText().toString();
        String mEmail = mEmailView.getText().toString();
        String mPass = mPassView.getText().toString();
        String mRePass = mRePassView.getText().toString();


        if (mUser != null) {
            if (u.findUsername(mUser)) {
                //popup window
            } 

            u.setUsername(mUser);

            if (mPass != null && mRePass != null) {
                if (mPass.equals(mRePass) && mUser != null && mEmail != null) {
                    String password = (String) this.findViewById(R.id.PassField).getContentDescription();
                    u.setUsername(mUser);
                    u.setPassword(mPass);
                    u.setEmail(mEmail);
                    startActivity(new Intent(".HomeScreen"));
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
