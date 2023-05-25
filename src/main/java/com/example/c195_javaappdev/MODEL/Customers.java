package com.example.c195_javaappdev.MODEL;

import com.example.c195_javaappdev.CONTROLLER.Appointment.Appointments_CustomerController;
import com.example.c195_javaappdev.DAO.queryAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }
    public String getAddress(){
        return address;
    }
    public String getZipcode(){
        return zipcode;
    }
    public String getPhone_number(){
        return phone_number;
    }
    public LocalDateTime getCreate_date(){
        return create_date;
    }
    public String getCreated_by() {
        return created_by;
    }
    public LocalDateTime getLast_update(){
        return last_update;
    }
    public String getLast_updated_by(){
        return last_updated_by;
    }
    public int getDivision_id(){
        return division_id;
    }
    public int getCountry_id(){return country_id;}
    public int setCountry_id(){this.country_id = country_id;
        return country_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * Gets all parts in the parts inventory list.
     *
     * @return all parts in the list
     */

    public static ObservableList<Customers> getAllCustomers() {
        allCustomers.add((Customers) queryAppointments.getAppointmentList());
        return allCustomers;
    }
}
