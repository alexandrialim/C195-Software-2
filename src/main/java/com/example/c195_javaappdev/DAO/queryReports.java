package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class queryReports {

    public static ObservableList<Appointments> getTotalOfEachType(){
        ObservableList<Appointments> getTotalTypeList = FXCollections.observableArrayList();
        try {
            String q = "SELECT count(Type) FROM appointments GROUP BY Type";

            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int type = r.getInt("count(Type)");
                Appointments appointments = new Appointments(type);
                getTotalTypeList.add(appointments);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getTotalTypeList;
    }
}
