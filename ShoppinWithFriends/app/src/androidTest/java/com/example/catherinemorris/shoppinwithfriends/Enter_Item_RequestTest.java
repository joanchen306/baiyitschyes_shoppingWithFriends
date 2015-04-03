package com.example.catherinemorris.shoppinwithfriends;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import junit.framework.TestCase;

public class Enter_Item_RequestTest extends ActivityInstrumentationTestCase2<Enter_Item_RequestTest> {

    public void testSendItemRequest() throws Exception {
            View view = new View();
            Enter_Item_Request noNameActivity = new Enter_Item_Request();
            noNameActivity.sendItemRequest(view).itemName = "";
            noNameActivity.sendItemRequest(view).itemDescription = "";
            noNameActivity.sendItemRequest(view).itemPrice = "";
            assertEquals("No fields should be blank.", sendItemRequest(view).builder1.getMessage);


    }
}