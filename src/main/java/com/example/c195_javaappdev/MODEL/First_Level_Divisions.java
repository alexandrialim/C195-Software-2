package com.example.c195_javaappdev.MODEL;

import java.time.LocalDateTime;

/**
 * This class holds and sets first level division data.
 */
public class First_Level_Divisions {
    private int division_id;
    public String division;
    public LocalDateTime create_date;
    public LocalDateTime timestamp;
    public int country_id;

    /**
     * gets and sets first level division data.
     * @param division_id division id number
     * @param division division name
     * @param country_id country id number
     */
    public First_Level_Divisions(int division_id, String division, int country_id){
        this.division_id = division_id;
        this.division = division;
        this.country_id = country_id;
    }

    /**
     * @return country id number
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * @return division id number
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * @return division name in readable form
     */
    @Override
    public String toString() {
        return division;
    }

    /**
     * @return division name
     */
    public String getDivision(){
        return division;
    }

}
