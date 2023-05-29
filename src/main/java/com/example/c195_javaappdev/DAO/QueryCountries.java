package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * This class holds methods for running SQL queries to get country data from the database.
 */
public class QueryCountries {
    /**
     * This method gets all teh countries listed in the database based on the provided country ID number.
     * Lambda is used to fill an ObservableList with countries based on the country ID.
     * @param countryID Locale Country ID
     * @return Country List based on country id, or return null if it's an empty list.
     */
    public static Countries getCountryByID(int countryID){
        //Lambda is used to fill an ObservableList with countries based on the country ID.
        ObservableList<Countries> list = getCountriesList().stream()
                .filter(c -> c.getCountry_id() == countryID)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * This method gets all country data.
     * @return All country data from the database and put it into an array list
     * that can be used to add info into the app tables.
     */
    public static ObservableList<Countries> getCountriesList() {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM countries";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                String country = r.getString("Country");
                int country_id = r.getInt("Country_ID");
                Countries customerCountry = new Countries(country_id, country);
                countriesList.add(customerCountry);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countriesList;
    }

}
