package com.example.catherinemorris.shoppinwithfriends;



import android.app.Activity;
import android.app.AlertDialog;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.widget.EditText;


public class LoginTest extends ActivityInstrumentationTestCase2<Login> {

    Login mActivity;
    private EditText mUserView;
    private EditText mPassWord;
    AlertDialog.Builder alert;

    public LoginTest(Class<Login> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();

        mUserView = (EditText) mActivity.findViewById(R.id.UserField);
        mPassWord = (EditText) mActivity.findViewById(R.id.PassField);

    }

    public void testSendMessageLogin() throws Exception {

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                mUserView.setText("hello");
                mPassWord.setText("hello123");
            }
        });

        System.out.println("User: " + mUserView.getText() + "  PassWord: " + mPassWord.getText());


    }
}