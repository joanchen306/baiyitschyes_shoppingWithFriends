package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.widget.EditText;

import junit.framework.TestCase;

import java.util.ArrayList;

public class FriendListTest extends TestCase {

    EditText friendName;
    User userTester;
    String friend;
    String message;
    FriendList friends = new FriendList();
    Activity myActivity;
    ArrayList<String> fList = new ArrayList<String>();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //helper = new Helper(ApplicationContextProvider.getContext());
    }

    public void testAddInvalidFriend() {
        fList.add("joanmouse");
        fList.add("jnugent");
        fList.add("dweeks7");

        friends.setFriendN(fList);
        friends.addFriend("Slowbro");
        assertFalse(friends.getFriendN().contains("Slowbro"));
        assertEquals(3, friends.getFriendN().size());
    }

    public void testAddValid() {
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.addFriend("joanmouse");
        assertTrue(friends.getFriendN().contains("joanmouse"));
        assertEquals(4, friends.getFriendN().size());
    }

    public void testAddNoName() {
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.addFriend("");
        assertFalse(friends.getFriendN().contains(""));
        assertEquals(4, friends.getFriendN().size());
    }

    public void testAddSelf() {
        fList.add("joanmouse");
        fList.add("dweeks7");

        friends.setFriendN(fList);
        friends.addFriend("jnugent6");
        assertTrue(friends.getFriendN().contains("jnugent6"));
        assertEquals(3, friends.getFriendN().size());
    }

    public void testAddExisted() {
        fList.add("joanmouse");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.addFriend("Slowbro");
        assertTrue(friends.getFriendN().contains("Slowbro"));
        assertEquals(3, friends.getFriendN().size());
    }
}