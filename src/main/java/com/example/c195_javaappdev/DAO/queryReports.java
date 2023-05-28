package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.CONTROLLER.ReportsController;
import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.MODEL.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class queryReports {

    public static int createTotalAndMonthOfEachTypeTable(){
        int rowsAffected;
        try {
            String q = "CREATE TEMPORARY TABLE sys.monthType AS SELECT count(Type), Type, monthname(Start) " +
                    "FROM appointments Group By Type, Start";

           PreparedStatement ps = JDBC.connection.prepareStatement(q);
           rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static ObservableList<Appointments> getMonthTypeTable() {
        ObservableList<Appointments> getTotalTypeList = FXCollections.observableArrayList();
        try {
            createTotalAndMonthOfEachTypeTable();
            String q = "SELECT * FROM sys.monthtype";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int typeCount = r.getInt("count(Type)");
                String typeDesc = r.getString("Type");
                String typeMonth = r.getString("monthname(Start)");
                Appointments appointments = new Appointments(typeCount, typeDesc, typeMonth);
                getTotalTypeList.add(appointments);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return getTotalTypeList;
    }

    public static ObservableList<Appointments> getAppointmentsByContact(int contact_id) {
        ObservableList<Appointments> getListForEachContact = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM appointments WHERE Contact_ID = ?;";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setInt(1, contact_id);
            ResultSet r = ps.executeQuery();
            while (r.next()){
                int appointmentID = r.getInt("Appointment_ID");
                String title = r.getString("Title");
                String description = r.getString("Description");
                String location = r.getString("Location");
                String type = r.getString("Type");
                LocalDateTime start = r.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = r.getTimestamp("End").toLocalDateTime();
                int custID = r.getInt("Customer_ID");
                int contID = r.getInt("Contact_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start
                        , end, custID, contID);
                getListForEachContact.add(appointments);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return getListForEachContact;
    }

    public static int createTotalCustomersPerDivisionTable(){
        int rowsAffected;
        try {
            String q = "CREATE TEMPORARY TABLE sys.customerByDivision AS SELECT count(B.Division_ID), A.Division " +
                    "FROM first_level_divisions A, customers B " +
                    "WHERE A.Division_ID = B.Division_ID " +
                    "Group By A.Division";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static ObservableList<Customers> getCustomersByDivisionTable() {
        ObservableList<Customers> divisionCustomerList = FXCollections.observableArrayList();
        try {
            createTotalCustomersPerDivisionTable();
            String q = "SELECT * FROM sys.customerByDivision";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int divisionCount = r.getInt("count(B.Division_ID)");
                String divisionName = r.getString("Division");
                Customers c = new Customers(divisionCount, divisionName);
                divisionCustomerList.add(c);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return divisionCustomerList;
    }
}
