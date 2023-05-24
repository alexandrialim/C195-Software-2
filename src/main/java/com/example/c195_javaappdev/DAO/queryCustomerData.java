package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class queryCustomerData {
    /**
     * Observablelist for all appointments in the database
     * @return aList = appointment list
     */
    public static ObservableList<Customers> getCustomerList(){
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM customers";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int customer_id = r.getInt("Customer_ID");
                String customer_name = r.getString("Customer_Name");
                String address = r.getString("Address");
                String zipcode = r.getString("Postal_Code");
                String phone = r.getString("Phone");
                LocalDateTime createdDate = r.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = r.getString("Created_By");
                LocalDateTime lastUpdate = r.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = r.getString("Last_Updated_By");
                int divisionID = r.getInt("Division_ID");
                Customers customers = new Customers(customer_id, customer_name, address, zipcode, phone, createdDate,
                        createdBy, lastUpdate, lastUpdatedBy, divisionID);
                cList.add(customers);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cList;
    }

    public static int insertCustomerList(TextField cName, TextField cAddress, TextField cPost,
                                         TextField cPhone, ComboBox cState) {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        int rowsAffected;
        try {
            int fldID = 0;
            for (queryFirstLevelDivision fld : queryFirstLevelDivision.getFirstLevelDivisionList()) {
                if (cState.getSelectionModel().getSelectedItem().equals(fld.getDivision())) {
                    fldID = fld.getDivision_id();
                }
            }
            String q = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setString(1, cName.getText()); //.toString()
            ps.setString(2, cAddress.getText());
            ps.setString(3, cPost.getText());
            ps.setString(4, cPhone.getText());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "script");
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "script");
            ps.setInt(9, fldID);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static int updateCustomerList(TextField CID, TextField cName, TextField cAddress, TextField cPost,
                                         TextField cPhone, ComboBox cState) {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        int rowsAffected;
        try {
            int fldID = 0;
            for (queryFirstLevelDivision fld : queryFirstLevelDivision.getFirstLevelDivisionList()) {
                if (cState.getSelectionModel().getSelectedItem().equals(fld.getDivision())) {
                    fldID = fld.getDivision_id();
                }
            }
            String q = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone =  ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID =? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setString(1, cName.getText()); //.toString()
            ps.setString(2, cAddress.getText());
            ps.setString(3, cPost.getText());
            ps.setString(4, cPhone.getText());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "script");
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "script");
            ps.setInt(9, fldID);
            ps.setInt(10, Integer.parseInt(CID.getId()));
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }


}
