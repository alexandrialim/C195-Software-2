package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.CONTROLLER.ReportsController;
import com.example.c195_javaappdev.MODEL.Appointments;
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
}
