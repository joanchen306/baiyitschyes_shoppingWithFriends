package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;


public class FriendListTest extends ActivityInstrumentationTestCase2<FriendList> {

    EditText friendName;
    User userTester;
    String friend;
    String message;
    Activity myActivity;

    public FriendListTest() {
        super(FriendList.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        myActivity = getActivity();
        friendName = (EditText) myActivity.findViewById(R.id.userText);
        //friend = friendName.getText().toString();
        //message = getMessage();
    }

    public void noName() {
        Button pressMe = (Button) myActivity.findViewById(R.id.addFriend);
    }


}