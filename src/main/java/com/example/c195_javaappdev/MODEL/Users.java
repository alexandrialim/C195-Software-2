package com.example.c195_javaappdev.MODEL;

/**
 * This class gets and sets user data.
 */
public class Users {
    private int user_id;
    private String user_name;
    public String pw;

    /**
     * This gets and sets user id, username, and password info.
     * @param user_id user id number
     * @param user_name username
     * @param pw password
     */
    public Users(int user_id, String user_name, String pw){
        this.user_id = user_id;
        this.user_name = user_name;
        this.pw = pw;
    }

    /**
     * @return user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @return username
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @return password
     */
    public String getPw() {
        return pw;
    }

    /**
     * @return the User ID in String form
     */
    @Override
    public String toString(){return String.valueOf(user_id);}
}
