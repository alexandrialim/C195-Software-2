package com.example.c195_javaappdev.DAO;
import com.example.c195_javaappdev.MODEL.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.*;

public class queryAppointments {
    public static Alert insertSuccessful = new Alert(Alert.AlertType.CONFIRMATION);
    public static Alert updateSuccessful = new Alert(Alert.AlertType.CONFIRMATION);
    public static Alert deleteSuccessful = new Alert(Alert.AlertType.CONFIRMATION);

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
            PreparedStatement ps = JDBC.connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
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

            int generatedAppointmentID = -1;
            ResultSet r = ps.getGeneratedKeys();
            if (r.next()) {
                generatedAppointmentID = r.getInt(1);
            }
            insertSuccessful.setAlertType(Alert.AlertType.CONFIRMATION);
            insertSuccessful.setContentText("Appointment ID #" + generatedAppointmentID + " that goes over " + type + " has been added.");
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
            ps.setTimestamp(10, lastUpdate);
            ps.setInt(11, appointmentID);
            rowsAffected = ps.executeUpdate();

            updateSuccessful.setAlertType(Alert.AlertType.CONFIRMATION);
            updateSuccessful.setContentText("Appointment ID #" + appointmentID + " that goes over " + type + " has been updated.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static int deleteAppointmentFromList(int appointmentID, String type){
        int rowsAffected = 0;
        try {
            String q = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setInt(1, appointmentID);
            rowsAffected = ps.executeUpdate();

            deleteSuccessful.setAlertType(Alert.AlertType.CONFIRMATION);
            deleteSuccessful.setContentText("Appointment ID #" + appointmentID + " that goes over " + type + " has been deleted.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public static ObservableList<Appointments> getAppointmentsThisWeek(){
        ObservableList<Appointments> aWeekList = FXCollections.observableArrayList();
        //set time frame
        LocalDate today = LocalDate.now();
        LocalDate endWeekDate = today.plusWeeks(1);
        try {
            String q = "SELECT * FROM appointments WHERE Start >= ? AND Start <= ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setTimestamp(1, Timestamp.valueOf(today.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(endWeekDate.atStartOfDay()));
            ResultSet r = ps.executeQuery();
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
                aWeekList.add(appointments);

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aWeekList;
    }

    public static ObservableList<Appointments> getAppointmentsThisMonth(){
        ObservableList<Appointments> aMonthList = FXCollections.observableArrayList();
        //set time frame
        LocalDate today = LocalDate.now();
        LocalDate endWeekDate = today.plusMonths(1);
        try {
            String q = "SELECT * FROM appointments WHERE Start >= ? AND Start <= ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setTimestamp(1, Timestamp.valueOf(today.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(endWeekDate.atStartOfDay()));
            ResultSet r = ps.executeQuery();
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
                aMonthList.add(appointments);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aMonthList;
    }

    public static boolean appointmentConflict(LocalDate startDate, LocalTime startTime, LocalTime endTime) throws SQLException {
        boolean flag = false;
        try {
            ZonedDateTime combinedDT_Start = ZonedDateTime.of(startDate,startTime, ZoneId.of("UTC"));
            ZonedDateTime combinedDT_End = ZonedDateTime.of(startDate,endTime, ZoneId.of("UTC"));
            String q = "SELECT * FROM appointments WHERE ? BETWEEN Start AND End OR ? BETWEEN Start AND End OR ? < Start AND ? > End";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);

            ps.setTimestamp(1, Timestamp.valueOf(combinedDT_Start.toLocalDateTime()));
            ps.setTimestamp(2, Timestamp.valueOf(combinedDT_End.toLocalDateTime()));
            ps.setTimestamp(3, Timestamp.valueOf(combinedDT_Start.toLocalDateTime()));
            ps.setTimestamp(4, Timestamp.valueOf(combinedDT_End.toLocalDateTime()));
            //ps.setInt(5, appID);
            ps.executeQuery();
            ResultSet r = ps.getResultSet();
            if(r != null){
                flag = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

//    public static ObservableList<Appointments> getAppointmentsThisMonth(){
//        ObservableList<Appointments> aMonthList = FXCollections.observableArrayList();
//        //set time frame
//        LocalDate today = LocalDate.now();
//        LocalDate endWeekDate = today.plusMonths(1);
//        try {
//            String q = "SELECT * FROM appointments WHERE Start >= ? AND Start <= ?";
//            PreparedStatement ps = JDBC.connection.prepareStatement(q);
//            ps.setTimestamp(1, Timestamp.valueOf(today.atStartOfDay()));
//            ps.setTimestamp(2, Timestamp.valueOf(endWeekDate.atStartOfDay()));
//            ResultSet r = ps.executeQuery();
//            while (r.next()) {
//                int appointmentID = r.getInt("Appointment_ID");
//                String title = r.getString("Title");
//                String description = r.getString("Description");
//                String location = r.getString("Location");
//                String type = r.getString("Type");
//                LocalDateTime start = r.getTimestamp("Start").toLocalDateTime();
//                LocalDateTime end = r.getTimestamp("End").toLocalDateTime();
//                LocalDateTime createDate = r.getTimestamp("Create_Date").toLocalDateTime();
//                String createBy = r.getString("Created_By");
//                LocalDateTime lastUpdate = r.getTimestamp("Last_Update").toLocalDateTime();
//                String lastUpdatedBy = r.getString("Last_Updated_By");
//                int CID = r.getInt("Customer_ID");
//                int UID = r.getInt("User_ID");
//                int contactID = r.getInt("Contact_ID");
//                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end,
//                        createDate, createBy, lastUpdate, lastUpdatedBy, CID, UID, contactID);
//                aMonthList.add(appointments);
//            }
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return aMonthList;
//    }

}
