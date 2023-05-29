package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * This class holds methods to query first level division data from the MySQL database.
 */
public class QueryFirstLevelDivision {

    /**
     * This method gets all first level divisions based on the provided division id number.
     * @param divisionID First Level Division ID
     * @return all first level division data based on the provided division id. Or, return null if that data doesn't exist.
     */
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

    /**
     * This method gets all first level division data.
     * @return Observable array list that holds all first level division data.
     */
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

    /**
     * This method gets all first level division data that is grouped by country id.
     * Lambda is used to fill an Observable array list with first level division data
     * and filtering it by country id number.
     * @param countryID country id number
     * @return the Observable array list with all the grouped first level division data
     * @throws SQLException
     */
    public static ObservableList<First_Level_Divisions> getDivisionsByCountryID(int countryID) throws SQLException {
        ObservableList<First_Level_Divisions> flds = FXCollections.observableArrayList();
        try {

            flds = QueryFirstLevelDivision.getFirstLevelDivisionList().stream()
                    .filter(fld -> fld.getCountry_id() == countryID)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));


        }catch (Exception e) {
            e.printStackTrace();
        }
        return flds;
    }
}
