package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.MODEL.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class holds methods that query the database to generate appointment and customer reports.
 */
public class QueryReports {

    /**
     * This method creates a temporary sql table that holds the number of each type of appointment,
     * the associated month of the appointment, and the total number of each type of appointment.
     * @return how many rows have been created in the table.
     */
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

    /**
     * This method grabs the month and type table that was created in the previous method.
     * @return month and type table data.
     */
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

    /**
     * This method gets all appointments and groups them by contact id to return all scheduled appointments
     * for each contact.
     * @param contact_id contact id number
     * @return list of all appointments for each contact.
     */
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

    /**
     * This method creates a temporary sql table to hold the number of customers associated with each division
     * and the division name.
     * @return how many rows have been created in the table.
     */
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

    /**
     * This method grabs the customer division data that was generated in the previous method.
     * @return the observable array list with the total number of customers grouped by division.
     */
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
