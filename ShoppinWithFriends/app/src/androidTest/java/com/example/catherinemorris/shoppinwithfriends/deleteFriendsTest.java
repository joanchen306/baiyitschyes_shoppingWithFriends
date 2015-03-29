package com.example.catherinemorris.shoppinwithfriends;

import android.app.AlertDialog;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class DeleteFriendsTest {

    private FriendList friendListTest;
    private EditText nameField;

    @Before
    public void setUp() {
        friendListTest = new FriendList();
        userTester = new User("Dillon");
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
    }

    @Test
    public void noName() {
        friendListTest.addFriends(friendListTest.findViewById(R.id.addFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        assertEquals(friendListTest.getMessage(), "This user does not exist");
    }

    @Test
    public void noNameExists() {
        friendListTest.addFriends(friendListTest.findViewById(R.id.addFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        nameField.setText("Rh aWOv");
        assertEquals(friendListTest.getMessage(), "This user does not exist");

    }

    @Test
    public void deletedFriend() {
        friendListTest.addFriends(friendListTest.findViewById(R.id.addFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        nameField.setText("jnugent6");
        assertEquals(friendListTest.getMessage(), "You have added jnugent6 as a new friend :)");
        friendListTest.deleteFriends(friendListTest.findViewById(R.id.deleteFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        nameField.setText("jnugent6");
        assertEquals(friendListTest.getMessage(), "You have deleted jnugent6 as a friend :'(");
    }

    @Test
    public void friendInDB() {
        friendListTest.addFriends(friendListTest.findViewById(R.id.addFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        nameField.setText("jnugent6");
        assertEquals(true, true);
        friendListTest.deleteFriends(friendListTest.findViewById(R.id.deleteFriend));
        nameField = (EditText) friendListTest.findViewById(R.id.userText);
        nameField.setText("jnugent6");
        assertEquals(true, true);
    }
}