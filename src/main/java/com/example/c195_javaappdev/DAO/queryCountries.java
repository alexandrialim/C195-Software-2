package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class queryCountries extends Countries {
    /**
     * @param country_id
     * @param country
     */
    public queryCountries(int country_id, String country) {
        super(country_id, country);
    }
    public static ObservableList<queryCountries> getCountriesList() {
        ObservableList<queryCountries> countriesList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM countries";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                String country = r.getString("Country");
                int country_id = r.getInt("Country_ID");
                queryCountries customerCountry = new queryCountries(country_id, country);
                countriesList.add(customerCountry);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countriesList;
    }
}
