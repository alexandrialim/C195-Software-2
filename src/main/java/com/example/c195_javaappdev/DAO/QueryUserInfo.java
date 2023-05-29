package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class holds methods to query user data from the MySQL database.
 */
public class QueryUserInfo {

    /**
     * This method is used to check if the user log in information exists in the database.
     * @param username username in database
     * @param password password in database
     * @return the username and password from the database.
     */
    public static String checkLoginInfo(String username, String password){
        try {
            String q = "SELECT * FROM users WHERE user_name = '" + username + "' AND password = '" + password +"'";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while(r.next()){
                username = r.getString("User_Name");
                password = r.getString("Password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username + " " + password;
    }

    /**
     * This method gets all users that exist in the database.
     * @return a list of all user information that exists in the database.
     * @throws SQLException
     */
    public static ObservableList<Users> getUserList() throws SQLException {
        ObservableList<Users> completeUserList = FXCollections.observableArrayList();
        String q1 = "SELECT * FROM users";
        ResultSet r = JDBC.connection.prepareStatement(q1).executeQuery();
        while (r.next()) {
            int UID = r.getInt("User_ID");
            String username = r.getString("User_Name");
            String password = r.getString("Password");
            Users user = new Users(UID, username, password);
            completeUserList.add(user);
        }
        return completeUserList;
    }

}
