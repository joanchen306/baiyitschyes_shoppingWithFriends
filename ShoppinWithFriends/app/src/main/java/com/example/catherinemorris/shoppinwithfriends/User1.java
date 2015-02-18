package com.example.catherinemorris.shoppinwithfriends;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user1 class creates static lists of usernames and passwords when a user registers
 *This will be transferred into the database 
 */
public class User1 {

    private static ArrayList<String> userList = new ArrayList<String>();
    private static ArrayList<String>  pwList = new ArrayList<String>();
    private static ArrayList<String>  emList = new ArrayList<String>();


    /**
     *This method adds a new username to the username list
     * @param  un, username retrieved from the text box
     * @return none
     */
    public static void setUsername(String un) {
        userList.add(un);
    }

    /**
     *This method adds a new password to the password list
     * @param pw, password retrieved from the text box
     * @return none
     */
    public static void setPassword(String pw) {
        pwList.add(pw);
    }

    /**
     *This method adds a new email to the email list
     * @param em, emailretrieved from the text box
     * @return none
     */
    public static void setEmail(String em) {
        emList.add(em);
    }

    /**
     *This method checks if the username is already in the list
     * in order to authenticate login
     * @param um, username retrieved from the text box
     * @return true if it is in the list
     */
    public static boolean findUsername(String um) {
        return (userList.contains(um));
    }

    /**
     *This method checks if the password is already in the list
     * in order to authenticate login
     * @param pw, password retrieved from the text box
     * @return true if it is in the list
     */
    public static boolean findPassWord(String pw) {
        return (pwList.contains(pw));
    }

}
