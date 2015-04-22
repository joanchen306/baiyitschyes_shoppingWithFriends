package com.example.catherinemorris.shoppinwithfriends.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.example.catherinemorris.shoppinwithfriends.Controller.ItemOnSale;
import com.example.catherinemorris.shoppinwithfriends.Controller.User;
import com.example.catherinemorris.shoppinwithfriends.R;

public class SaleConnection extends ActionBarActivity {

    private ItemOnSale sale;
    private User myU;

    private String username;
    private RatingBar ratingBar;
    TextView ratingVal;
    int numRatings;
    int sumRate;

    ArrayList<String> comments = new ArrayList<String>();
    private final Context context = this;

    final static LatLng ATLANTA = new LatLng(33.7550,-84.3900);


    /**
     * Pulls the information from the previous Activity to populate this Activity with
     * information pertaining to the ItemOnSale in question.
     * @param savedInstanceState used to overwrite method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //GoogleMap googleMap;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_connection);

        myU = (User) getIntent().getSerializableExtra("User"); //pulls in myU from the last Activity
        sale = (ItemOnSale) getIntent().getSerializableExtra("Sale"); //pulls in the ItemOnSale that the User clicked on in HomeScreen or FoundSaleList

        TextView currName = (TextView)findViewById(R.id.itemField);
        currName.setText(sale.getItem());

        TextView currPrice = (TextView) findViewById(R.id.PriceField);
        currPrice.setText("" + sale.getPrice());


        TextView currLoc = (TextView) findViewById(R.id.locationField);
        currLoc.setText(sale.getLocation().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openMap (View view) {
        Intent i = new Intent("android.SaleConnectionMap");
        i.putExtra("User", myU);
        i.putExtra("ItemOnSale", sale);
        startActivity(i);
    }

    /**
     * Closes the current Activity
     * @param view used to overwrite method
     */
    public void goBack(View view) {
        finish();
    }

    public void addComment(View view) {
        EditText etComment = (EditText) this.findViewById(R.id.saleConnectionComment);
        final String comment = etComment.getText().toString();
        comments.add(comment);
        ListView commentList = (ListView) findViewById(R.id.saleConnectionListView);
        ArrayAdapter<String> commentAdapt = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, comments);
        commentList.setAdapter(commentAdapt);
        commentAdapt.notifyDataSetChanged();
    }



    public void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingVal = (TextView) findViewById(R.id.currentRating);

        ratingBar.setOnRatingBarChangeListener((new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // rating = (rating + sumRate) / numRatings;
                TextView currentRating = (TextView) findViewById(R.id.currentRating);
                ratingVal.setText(String.valueOf(rating));
                currentRating.setText(""+rating);

            }
        }));
    }
}

