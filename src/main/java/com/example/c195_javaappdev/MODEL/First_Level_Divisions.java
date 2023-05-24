package com.example.c195_javaappdev.MODEL;

import java.time.LocalDateTime;

public class First_Level_Divisions {
    private int division_id;
    public String division;
    public LocalDateTime create_date;
    public LocalDateTime timestamp;
    public int country_id;

    public First_Level_Divisions(int division_id, String division, int country_id){
        this.division_id = division_id;
        this.division = division;
        this.country_id = country_id;
    }

    public int getDivision_id() {
        return division_id;
    }
    public String getDivision(){
        return division;
    }

    public int getCountry_id() {
        return country_id;
    }
}
