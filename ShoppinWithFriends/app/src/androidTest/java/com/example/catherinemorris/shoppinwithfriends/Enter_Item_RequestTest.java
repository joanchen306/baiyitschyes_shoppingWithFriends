package com.example.catherinemorris.shoppinwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;



import junit.framework.TestCase;

public class Enter_Item_RequestTest extends ActivityInstrumentationTestCase2<Enter_Item_RequestTest> {
    EditText itemName;
    EditText itemDescription;
    EditText itemPrice;
    Enter_Item_Request thisActivity = new Enter_Item_Request();

    public void testSendItemRequestWarning() throws Exception {
            itemName = (EditText) Enter_Item_Request.findViewById(R.id.itemNameField);
            itemDescription = (EditText) Enter_Item_Request.findViewById(R.id.itemDescriptionField);
            itemPrice = (EditText) Enter_Item_Request.findViewById(R.id.priceField);
            itemName.setText("");
            itemDescription.setText("Something");
            itemPrice.setText("Something");
            thisActivity.sendItemRequest(this);
            assertFalse(!thisActivity.DialogAlertCreated());

            itemName.setText("Something");
            itemDescription.setText("");
            itemPrice.setText("Something");
            thisActivity.sendItemRequest(thisActivity);
            assertFalse(!thisActivity.DialogAlertCreated());

            itemName.setText("Something");
            itemDescription.setText("Something");
            itemPrice.setText("");
            thisActivity.sendItemRequest(thisActivity);
            assertFalse(!thisActivity.DialogAlertCreated());

    }
}