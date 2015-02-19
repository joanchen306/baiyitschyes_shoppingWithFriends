package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.catherinemorris.shoppinwithfriends.User1;


public class Login extends ActionBarActivity {

    /**
     * private static final String[] DUMMY_CREDENTIALS = new String[]{
     * "foo@example.com:hello", "bar@example.com:world"
     * };
     */
    private EditText mUserView;
    private EditText mPasswordView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

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

        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        //if (u.findUsername(user) && u.findPassWord(password)) {
            //checks if the user is registered or not
            //if (!u.findUsername(user)) {
            //    //popup window "You are not registered";
            //}

            //Check if the passwords are correct
            //if (u.findPassWord(password)) {
                startActivity(new Intent("android.HomeScreen"));
            //} else {
                //popup "Incorrect password"
            //}
            //}
        //}
    }
}

  /**  public void onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);
            return rootView;
        }
    }
        */

