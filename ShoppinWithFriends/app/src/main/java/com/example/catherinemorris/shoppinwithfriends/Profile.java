package com.example.catherinemorris.shoppinwithfriends;

/**
 * This class displays the username, email, ratings, and number
 * of sales of the user that is selected from the friend's list.
 */



import android.content.Context;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class Profile extends ActionBarActivity {

    private String username;
    private RatingBar ratingBar;
    TextView ratingVal;
    int numRatings;
    int sumRate;

    ArrayList<String> comments = new ArrayList<String>();
    private final Context context = this;





    /**
     * Creates the view by searching in the database for the information
     * of the specified user and displays it
     * @param savedInstanceState used to overwrite method
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Firebase.setAndroidContext(this);

//        final ListView commentList = (ListView) findViewById(R.id.commentListView);
//        if(comments.length > 0) {
//            ArrayAdapter<String> friendAdapt = new ArrayAdapter<>(this,
//                    android.R.layout.simple_list_item_1,
//                    comments);
//            commentList.setAdapter(friendAdapt);
//        }



        addListenerOnRatingBar();

        username = getIntent().getStringExtra("Friend");
        Firebase myFirebaseRef = new Firebase("https://baiyitschyes.firebaseio.com");
        Query queryRef = myFirebaseRef.child("userInfo").child(username);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> infoMap = (Map<String, Object>) snapshot.getValue();
                String email = (String) infoMap.remove("email");
                long sales = (long) infoMap.remove("numSales");
                long rate = (long) infoMap.remove("rate");

                TextView currentUsername = (TextView)findViewById(R.id.currentUsername);
                currentUsername.setText(username);

                TextView currentEmail = (TextView) findViewById(R.id.currentEmail);
                currentEmail.setText(email);

                TextView currentSales = (TextView)findViewById(R.id.currentSales);
                currentSales.setText(""+sales);

//                TextView currentRating = (TextView)findViewById(R.id.currentRating);
//                currentRating.setText(""+rate);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    /**
     * Closes the current Activity
     * @param view used to overwrite method
     */
    public void goBack(View view) {
        finish();
    }

    public void addComment(View view) {
        EditText etComment = (EditText) this.findViewById(R.id.comment);
        final String comment = etComment.getText().toString();
        comments.add(comment);
        ListView commentList = (ListView) findViewById(R.id.commentListView);
        ArrayAdapter<String> commentAdapt = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, comments);
        commentList.setAdapter(commentAdapt);
        commentAdapt.notifyDataSetChanged();
    }

}

