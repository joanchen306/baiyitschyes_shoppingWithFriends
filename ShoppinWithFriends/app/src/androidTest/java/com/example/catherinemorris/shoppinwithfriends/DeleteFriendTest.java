package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;

import android.widget.EditText;

import junit.framework.TestCase;

import java.util.ArrayList;

public class DeleteFriendTest extends TestCase {

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

    public void testDeleteFriend() {
        fList.add("joanmouse");
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.deleteFriend("Slowbro");
        assertFalse(friends.getFriendN().contains("Slowbro"));
        assertEquals(3, friends.getFriendN().size());
    }

    public void testDeleteInvalid() {
        fList.add("joanmouse");
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.deleteFriend("hahahahaha");
        assertFalse(friends.getFriendN().contains("hahhahahah"));
        assertEquals(4, friends.getFriendN().size());
    }

    public void testDeleteNoName() {
        fList.add("joanmouse");
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.deleteFriend("");
        assertFalse(friends.getFriendN().contains(""));
        assertEquals(4, friends.getFriendN().size());
    }

    public void testDeleteFriendTwice() {
        fList.add("joanmouse");
        fList.add("jnugent");
        fList.add("dweeks7");
        fList.add("Slowbro");

        friends.setFriendN(fList);
        friends.deleteFriend("Slowbro");
        assertFalse(friends.getFriendN().contains("Slowbro"));
        assertEquals(3, friends.getFriendN().size());

        friends.setFriendN(fList);
        friends.deleteFriend("Slowbro");
        assertFalse(friends.getFriendN().contains("Slowbro"));
        assertEquals(3, friends.getFriendN().size());
    }

    public void testDeleteFriendFromEmptyList() {
        friends.setFriendN(fList);
        friends.deleteFriend("Slowbro");
        assertFalse(friends.getFriendN().contains("Slowbro"));
        assertEquals(0, friends.getFriendN().size());
    }
}