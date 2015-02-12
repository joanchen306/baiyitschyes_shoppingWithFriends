package com.example.catherinemorris.shoppinwithfriends;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by joanchen on 2/4/15.
 */
public class User1 {

    //why are these all ints?
    private ArrayList<String> userList = new ArrayList<String>();
    private ArrayList<String>  pwList = new ArrayList<String>();
    private ArrayList<String>  emList = new ArrayList<String>();


    public void setUsername(String un) {
        userList.add(un);
    }

    public void setPassword(String pw) {
        pwList.add(pw);
    }

    public void setEmail(String em) {
        emList.add(em);
    }

    public boolean findUsername(String um) {
        return (userList.contains(um));
    }

    public boolean findPassWord(String um) {
        return (pwList.contains(um));
    }

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	User1 u = new User1();

    	System.out.print("Please enter a username");
    	u.setUsername(input.nextLine());

    	System.out.print("Please enter a password");
    	u.setPassword(input.nextLine());

    }

}
