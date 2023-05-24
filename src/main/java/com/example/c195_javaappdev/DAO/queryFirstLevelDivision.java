package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class queryFirstLevelDivision extends First_Level_Divisions {
    public queryFirstLevelDivision(int division_id, String division, int country_id) {
        super(division_id, division, country_id);
    }

    public static ObservableList<queryFirstLevelDivision> getFirstLevelDivisionList() {
        ObservableList<queryFirstLevelDivision> flDivisionList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM first_level_divisions";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int division_id = r.getInt("Division_ID");
                String division = r.getString("Division");
                int country_id = r.getInt("Country_ID");
                queryFirstLevelDivision fld= new queryFirstLevelDivision(division_id, division, country_id);
                flDivisionList.add(fld);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flDivisionList;
    }
}
