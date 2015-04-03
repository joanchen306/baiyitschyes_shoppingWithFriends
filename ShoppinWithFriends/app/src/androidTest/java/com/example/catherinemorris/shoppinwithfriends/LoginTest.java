package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;


public class LoginTest extends ActivityInstrumentationTestCase2<Login> {

    private EditText mUserView, mPasswordView;
    private Activity myActivity;

    public LoginTest() {
        super(Login.class);
    }

    protected void setUp() throws Exception {
    /*
     * Call the super constructor (required by JUnit)
     */
        super.setUp();
        Activity login = getActivity();

        mUserView = (EditText) login.findViewById(R.id.UserField);
        mPasswordView = (EditText) login.findViewById(R.id.PassField);

    }



    public void testValidUserNamePassword() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);

        // open current activity.
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.login);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("joanmouse");
                mPasswordView.requestFocus();
                mPasswordView.setText("nyan");

                // click button and open next activity.

                button.requestFocus();
                button.performClick();
            }
        });

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 100000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity .finish();
    }

    public void testInvalidUserName() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);

        // open current activity.
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.login);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("joannnnnn");
                mPasswordView.requestFocus();
                mPasswordView.setText("nyan");

                // click button and open next activity.

                button.requestFocus();
                button.performClick();
            }
        });

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
        // next activity is opened and captured.
        assertNull(nextActivity);
        //nextActivity .finish();
    }

    public void testBlankUserName() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);

        // open current activity.
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.login);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("");
                mPasswordView.requestFocus();
                mPasswordView.setText("nyan");

                // click button and open next activity.

                button.requestFocus();
                button.performClick();
            }
        });

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
        // next activity is opened and captured.
        assertNull(nextActivity);
        //nextActivity .finish();
    }

    public void testBlankPassWord() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);

        // open current activity.
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.login);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("joanmouse");
                mPasswordView.requestFocus();
                mPasswordView.setText("");

                // click button and open next activity.

                button.requestFocus();
                button.performClick();
            }
        });

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
        // next activity is opened and captured.
        assertNull(nextActivity);
        //nextActivity .finish();
    }

    public void testInvalidPassWord() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);

        // open current activity.
        myActivity = getActivity();
        final Button button = (Button) myActivity.findViewById(R.id.login);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUserView.requestFocus();
                mUserView.setText("joanmouse");
                mPasswordView.requestFocus();
                mPasswordView.setText("wrong");

                // click button and open next activity.

                button.requestFocus();
                button.performClick();
            }
        });

        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
        // next activity is opened and captured.
        assertNull(nextActivity);
        //nextActivity .finish();
    }

}