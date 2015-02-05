package com.example.catherinemorris.shoppinwithfriends;

/**
 * Created by joanchen on 2/4/15.
 */
public class User1 {

    //why are these all ints?
    private String username;
    private String password;
    private String email;


    public void setUsername(String un) {
        username = un;
    }

    public void setPassword(String pw) {
        password = pw;
    }

    public void setEmail(String em) {
        email = em;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
