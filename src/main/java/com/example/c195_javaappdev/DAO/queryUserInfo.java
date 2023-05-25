package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class queryUserInfo {
    public queryUserInfo(int UID, String username, String password){
        super();
    }
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
