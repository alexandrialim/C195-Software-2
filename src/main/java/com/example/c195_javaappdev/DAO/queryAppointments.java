package com.example.c195_javaappdev.DAO;
import com.example.c195_javaappdev.MODEL.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class queryAppointments {

    /**
     * Observablelist for all appointments in the database
     * @return aList = appointment list
     */
    public static ObservableList<Appointments> getAppointmentList(){
        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM appointments";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int appointmentID = r.getInt("Appointment_ID");
                String title = r.getString("Title");
                String description = r.getString("Description");
                String location = r.getString("Location");
                String type = r.getString("Type");
                LocalDateTime start = r.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = r.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = r.getTimestamp("Create_Date").toLocalDateTime();
                String createBy = r.getString("Created_By");
                LocalDateTime lastUpdate = r.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = r.getString("Last_Updated_By");
                int CID = r.getInt("Customer_ID");
                int UID = r.getInt("User_ID");
                int contactID = r.getInt("Contact_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end,
                        createDate, createBy, lastUpdate, lastUpdatedBy, CID, UID, contactID);
                aList.add(appointments);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aList;
    }

    public static int addAppointment(String title, String description, int contactID, String type, String location
            , LocalDateTime startDateTime, LocalDateTime endDateTime, String customerID, String userID,
                                     Timestamp createDate, Timestamp lastUpdate){
        int rowsAffected;
        try {
            String q = "INSERT INTO appointments (Title, Description, Contact_ID, Type, Location, Start, End, Customer_ID, User_ID, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, contactID);
            ps.setString(4, type);
            ps.setString(5, location);
            ps.setTimestamp(6, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(7, Timestamp.valueOf(endDateTime));
            ps.setString(8, customerID);
            ps.setString(9, userID);
            ps.setTimestamp(10, createDate);
            ps.setString(11, "script");
            ps.setTimestamp(12, lastUpdate);
            ps.setString(13, "script");

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static int updateAppointmentList(int appointmentID, String title, String description, int contactID, String type, String location
            , LocalDateTime startDateTime, LocalDateTime endDateTime, String customerID, String userID,
                                           Timestamp lastUpdate) {
        int rowsAffected;
        try {
            String q = "UPDATE appointments SET Title = ?, Description = ?, Contact_ID = ?, Type =  ?, Location = ?, Start = ?, End = ?, Customer_ID = ?, User_ID =? , Last_Update =? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, contactID);
            ps.setString(4, type);
            ps.setString(5, location);
            ps.setTimestamp(6, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(7, Timestamp.valueOf(endDateTime));
            ps.setString(8, customerID);
            ps.setString(9, userID);
            //ps.setTimestamp(10, createDate);
            //ps.setString(11, "script");
            ps.setTimestamp(10, lastUpdate);
            ps.setInt(11, appointmentID);
            //ps.setString(13, "script");

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static int deleteAppointmentFromList(int appointmentID){
        int rowsAffected;
        try {
            String q = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setInt(1, appointmentID);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

//    public static Appointments getAppointmentContactByID(int appContactID){
//        ObservableList<Appointments> list = getAppointmentList().stream()
//                .filter(c -> c.getContactID() == appContactID)
//                .collect(Collectors.toCollection(FXCollections::observableArrayList));
//
//        if(list.size() > 0){
//            return list.get(0);
//        }else{
//            return null;
//        }
//    }

//    public static Contacts getContactFromContactID(int contactID) throws SQLException {
//        String q = "select * from contacts where Contact_ID = " + contactID;
//        ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
//        while (r.next()) {
//            int contact_id = r.getInt("Contact_ID");
//            String contact_name = r.getString("Contact_Name");
//            String email = r.getString("Email");
//            Contacts contact = new Contacts(contact_id, contact_name,email);
//            return contact;
//        }
//        return null;
//    }

}
