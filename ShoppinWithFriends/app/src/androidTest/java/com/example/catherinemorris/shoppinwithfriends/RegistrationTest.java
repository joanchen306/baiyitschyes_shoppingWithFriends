package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


public class RegistrationTest extends ActivityInstrumentationTestCase2<Registration> {

    // private Instrumentation mInstrumentation;
    //Instrumentation.ActivityMonitor monitor;
    Activity myActivity;
    EditText mUserView, mPassView, mEmailView, mRePassView;
    Registration r = new Registration();

    public RegistrationTest() {
        super(Registration.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //mInstrumentation = getInstrumentation();
        //setActivityInitialTouchMode(false);

        myActivity = getActivity();

        mUserView = (EditText) myActivity.findViewById(R.id.UserField);
        mPassView = (EditText) myActivity.findViewById(R.id.PassField);
        mEmailView = (EditText) myActivity.findViewById(R.id.EmailField);
        mRePassView = (EditText) myActivity.findViewById(R.id.RePassField);

    }


    public void testNoUsername() {
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.register);

        Log.d("line", "1");

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("");
                mPassView.requestFocus();
                mPassView.setText("nyan");
                mRePassView.requestFocus();
                mRePassView.setText("meow");
                mEmailView.requestFocus();
                mEmailView.setText("irisks@gmail.com");

                Log.d("line", "2");

                button.requestFocus();
                button.performClick();
            }
        });

        Log.d("line", "3");

        Instrumentation.ActivityMonitor next = getInstrumentation().addMonitor(Login.class.getName(),null, false);

        Activity nextAct = getInstrumentation().waitForMonitorWithTimeout( next, 1000);
        assertNull(nextAct);

    }

    public void testC_OpenLoginActivityIfUserIsNotLoggedIn() {
        myActivity = getActivity();

        final Button button = (Button) myActivity.findViewById(R.id.register);

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("bobby");
                mPassView.requestFocus();
                mPassView.setText("meow");
                mRePassView.requestFocus();
                mRePassView.setText("meow");
                mEmailView.requestFocus();
                mEmailView.setText("irisks@gmail.com");

                button.requestFocus();
                button.performClick();
            }
        });

        Instrumentation.ActivityMonitor next = getInstrumentation().addMonitor(Login.class.getName(),null, false);

        Activity nextAct = getInstrumentation().waitForMonitorWithTimeout( next, 1000);
        assertNotNull(nextAct);
        nextAct.finish();

    }


    public void testMisMatchedPasswords() {

        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.register);

        Log.d("line", "1");

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("jim");
                mPassView.requestFocus();
                mPassView.setText("nyan");
                mRePassView.requestFocus();
                mRePassView.setText("meow");
                mEmailView.requestFocus();
                mEmailView.setText("irisks@gmail.com");

                Log.d("line", "2");

                button.requestFocus();
                button.performClick();
            }
        });

        Log.d("line", "3");

        Instrumentation.ActivityMonitor next = getInstrumentation().addMonitor(Login.class.getName(),null, false);

        Activity nextAct = getInstrumentation().waitForMonitorWithTimeout(next, 1000);

        assertEquals(null, nextAct);

    }


    public void testNotEmail() {

        myActivity = getActivity();

        final Button button = (Button) myActivity.findViewById(R.id.register);

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("mary");
                mPassView.requestFocus();
                mPassView.setText("nyan");
                mRePassView.requestFocus();
                mRePassView.setText("nyan");
                mEmailView.requestFocus();
                mEmailView.setText("irisks");

                button.requestFocus();
                button.performClick();
            }
        });

        Instrumentation.ActivityMonitor next = getInstrumentation().addMonitor(Login.class.getName(),null, false);

        Activity nextAct = getInstrumentation().waitForMonitorWithTimeout( next, 1000);
        assertNotNull(nextAct);
        //nextAct.finish();
    }

    public void testNoSecondPassword() {

        myActivity = getActivity();

        final Button button = (Button) myActivity.findViewById(R.id.register);

        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("jane");
                mPassView.requestFocus();
                mPassView.setText("nyan");
                mRePassView.requestFocus();
                mRePassView.setText("");
                mEmailView.requestFocus();
                mEmailView.setText("irisks@gmail.com");

                button.requestFocus();
                button.performClick();
            }
        });

        Instrumentation.ActivityMonitor next = getInstrumentation().addMonitor(Login.class.getName(),null, false);

        Activity nextAct = getInstrumentation().waitForMonitorWithTimeout( next, 1000);
        assertNull(nextAct);
    }
}
