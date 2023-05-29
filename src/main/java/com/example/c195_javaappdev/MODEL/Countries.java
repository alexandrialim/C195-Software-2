package com.example.c195_javaappdev.MODEL;

/**
 * This class holds country data.
 */
public class Countries {
    public int country_id;
    public String country;

    /**
     * gets and sets country id and country name
     * @param country_id
     * @param country
     */
    public Countries(int country_id, String country){
        this.country = country;
        this.country_id = country_id;
    }

    /**
     * @return country id number
     */
    public int getCountry_id(){
        return country_id;
    }

    /**
     * @return country name
     */
    public String getCountry(){
        return country;
    }

    /**
     * @return country name is readable format
     */
    @Override
    public String toString() {
        return country;
    }
}
