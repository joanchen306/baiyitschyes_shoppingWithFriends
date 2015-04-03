package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class DeleteFriendTest extends ActivityInstrumentationTestCase2<FriendList> {

    EditText friendName;
    User userTester;
    String friend;
    String message;
    Activity myActivity;
    FriendList friends;

    public DeleteFriendTest() {
        super(FriendList.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        myActivity = getActivity();
        friendName = (EditText) myActivity.findViewById(R.id.userText);
    }

}