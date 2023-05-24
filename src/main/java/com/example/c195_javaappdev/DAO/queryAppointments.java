package com.example.c195_javaappdev.DAO;
import com.example.c195_javaappdev.MODEL.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    //public void
}
