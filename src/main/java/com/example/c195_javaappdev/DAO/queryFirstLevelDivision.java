package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Countries;
import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class queryFirstLevelDivision{

    public static First_Level_Divisions getFirstLevelDivisionByID(int divisionID) {
        try {
            String q = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(q);
            ps.setInt(1,divisionID);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int division_id = r.getInt("Division_ID");
                String division = r.getString("Division");
                int country_id = r.getInt("Country_ID");
                First_Level_Divisions fld= new First_Level_Divisions(division_id, division, country_id);
                return fld;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ObservableList<First_Level_Divisions> getFirstLevelDivisionList() {
        ObservableList<First_Level_Divisions> flDivisionList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM first_level_divisions";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int division_id = r.getInt("Division_ID");
                String division = r.getString("Division");
                int country_id = r.getInt("Country_ID");
                First_Level_Divisions fld= new First_Level_Divisions(division_id, division, country_id);
                flDivisionList.add(fld);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flDivisionList;
    }

    public static ObservableList<First_Level_Divisions> getDivisionsByCountryID(int countryID) throws SQLException {
        ObservableList<First_Level_Divisions> flds = FXCollections.observableArrayList();
        try {

            flds = queryFirstLevelDivision.getFirstLevelDivisionList().stream()
                    .filter(fld -> fld.getCountry_id() == countryID)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));


        }catch (Exception e) {
            e.printStackTrace();
        }
        return flds;
    }
}
