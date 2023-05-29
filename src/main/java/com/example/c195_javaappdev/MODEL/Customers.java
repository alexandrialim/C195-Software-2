package com.example.c195_javaappdev.MODEL;

import com.example.c195_javaappdev.DAO.QueryAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
/**
 * This class holds customer data.
 */
public class Customers {
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    private int customer_id;
    public String customer_name;
    public String address;
    public String zipcode;
    public String phone_number;
    public LocalDateTime create_date;
    public String created_by;
    public LocalDateTime last_update;
    public String last_updated_by;
    public int division_id;
    public int country_id;
    public String divisionName;
    public int divisionCount = 0;

    /**
     * This gets and sets all customer data.
     * @param customer_id customer ID number
     * @param customer_name customer name
     * @param address customer address
     * @param zipcode customer postal code
     * @param phone_number customer phone number
     * @param create_date customer creation date
     * @param created_by who added the customer data to the database
     * @param last_update when the customer data was last updated/modified
     * @param last_updated_by who updated the customer data in the database
     * @param division_id customer division ID number
     */
    public Customers(int customer_id, String customer_name, String address, String zipcode, String phone_number,
                     LocalDateTime create_date, String created_by, LocalDateTime last_update, String last_updated_by,
                     int division_id){
        super();
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone_number = phone_number;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }
    /**
     * This gets and sets some customer data.
     * @param customer_name customer name
     * @param address customer address
     * @param zipcode customer postal code
     * @param phone_number customer phone number
     * @param create_date customer creation date
     * @param created_by who added the customer data to the database
     * @param last_update when the customer data was last updated/modified
     * @param last_updated_by who updated the customer data in the database
     * @param division_id customer division ID number
     */
    public Customers(String customer_name, String address, String zipcode, String phone_number,
                     LocalDateTime create_date, String created_by, LocalDateTime last_update, String last_updated_by,
                     int division_id){
        super();
        this.customer_name = customer_name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone_number = phone_number;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }

    /**
     * Holds and sets amount of each division in table and the associated division name.
     * @param divisionCount total amount of each division in the table
     * @param divisionName division name
     */
    public Customers(int divisionCount, String divisionName){
        this.divisionCount = divisionCount;
        this.divisionName = divisionName;
    }

    /**
     * @return customer id number
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * @return division name
     */
    public String getDivisionName(){
        return divisionName;
    }

    /**
     * @return amount of each division that is listed in the table
     */
    public int getDivisionCount(){
        return divisionCount;
    }

    /**
     * @return customer name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * @return customer address
     */
    public String getAddress(){
        return address;
    }

    /**
     * @return customer postal code
     */
    public String getZipcode(){
        return zipcode;
    }

    /**
     * @return customer phone number
     */
    public String getPhone_number(){
        return phone_number;
    }

    /**
     * @return Date of when the customer created
     */
    public LocalDateTime getCreate_date(){
        return create_date;
    }

    /**
     * @return who created the customer data in the database
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @return when the customer data was last modified/updated
     */
    public LocalDateTime getLast_update(){
        return last_update;
    }

    /**
     * @return who last updated the customer data in the database
     */
    public String getLast_updated_by(){
        return last_updated_by;
    }

    /**
     * @return Division ID number
     */
    public int getDivision_id(){
        return division_id;
    }

    /**
     * @return country ID number
     */
    public int getCountry_id(){return country_id;}

    /**
     * set country id number
     */
    public void setCountry_id(){this.country_id = country_id;
        this.customer_id = country_id;
    }

    /**
     * set customer id number
     * @param customer_id customer ID
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * set customer name
     * @param customer_name customer name
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * Gets all customers in the complete customer list.
     * @return all customers in the list
     */

    public static ObservableList<Customers> getAllCustomers() {
        allCustomers.add((Customers) QueryAppointments.getAppointmentList());
        return allCustomers;
    }

    /**
     * @return the Customer ID in String form
     */
    @Override
    public String toString(){return String.valueOf(customer_id);}
}
