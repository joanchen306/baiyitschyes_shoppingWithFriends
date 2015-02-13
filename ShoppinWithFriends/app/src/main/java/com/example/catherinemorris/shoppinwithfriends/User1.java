package com.example.catherinemorris.shoppinwithfriends;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by joanchen on 2/4/15.
 */
public class User1 {

    //why are these all ints?
    private static ArrayList<String> userList = new ArrayList<String>();
    private static ArrayList<String>  pwList = new ArrayList<String>();
    private static ArrayList<String>  emList = new ArrayList<String>();


    public static void setUsername(String un) {
        userList.add(un);
    }

    public static void setPassword(String pw) {
        pwList.add(pw);
    }

    public static void setEmail(String em) {
        emList.add(em);
    }

    public static boolean findUsername(String um) {
        return (userList.contains(um));
    }

    public static boolean findPassWord(String um) {
        return (pwList.contains(um));
    }

}
