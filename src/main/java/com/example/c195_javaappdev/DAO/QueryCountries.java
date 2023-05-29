package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class QueryCountries {
    public static Countries getCountryByID(int countryID){
        ObservableList<Countries> list = getCountriesList().stream()
                .filter(c -> c.getCountry_id() == countryID)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

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
