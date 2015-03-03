package com.example.catherinemorris.shoppinwithfriends;

/**
 * A request that has been made for the sale of a particular item
 * Created by Caitlin Morris on 2/28/15.
 */
public class Sale {

    private String item;
    private String descripton;
    private long min;
    private long max;
    private User user;

    private UserDB db = new UserDB();

    public Sale(String it, String des, long max, long min, User u) {
        item = it;
        descripton = des;
        this.max = max;
        this.min = min;
        user = u;
    }

}
