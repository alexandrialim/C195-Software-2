package com.example.c195_javaappdev.MODEL;


import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * This class holds appointment data.
 */
public class Appointments {

    private int appointment_id;
    private String title;
    private String description;
    private String location;
    private String type;
    private int typeAmount;
    private String typeDescription;
    private String typeMonth;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * This holds and sets data for ALL appointment data.
     * @param appointment_id Associated Appointment ID
     * @param title Associated Appointment Title
     * @param description Associated Appointment Description
     * @param location Associated Appointment Location
     * @param type Associated Appointment Type
     * @param startTime Associated Appointment Start Time
     * @param endTime Associated Appointment End Time
     * @param createDate Associated Appointment Creation Date
     * @param createdBy Who added this appointment to the database
     * @param lastUpdate Last time the appointment was updated
     * @param lastUpdatedBy Who updated this appointment in the database
     * @param customerID Associated Appointment Customer ID
     * @param userID Associated Appointment User ID
     * @param contactID Associated Appointment Contact ID
     */
    public Appointments(int appointment_id, String title, String description, String location, String type,
                        LocalDateTime startTime, LocalDateTime endTime, LocalDateTime createDate, String createdBy,
                        LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID){
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * This holds and sets appointment data for the number of each appointment type, the type of appointment,
     * the appointment month.
     * @param typeNum Amount of each appointment type
     * @param typeDesc Description that was entered for the appointment type
     * @param monthName Which month does the appointment occur at
     */
    public Appointments(int typeNum, String typeDesc, String monthName) {
        this.typeAmount = typeNum;
        this.typeDescription = typeDesc;
        this.typeMonth = monthName;
    }

    /**
     * This holds and sets appointment id, title, description, location, type, start time, end time, customer id, contact id data.
     * @param appointment_id Associated Appointment ID
     * @param title Associated Appointment Title
     * @param description Associated Appointment Description
     * @param location Associated Appointment Location
     * @param type Associated Appointment Type
     * @param startTime Associated Appointment Start Time
     * @param endTime Associated Appointment End Time
     * @param customerID Associated Appointment Customer ID
     * @param contactID Associated Appointment Contact ID
     */
    public Appointments(int appointment_id, String title, String description, String location, String type,
                        LocalDateTime startTime, LocalDateTime endTime, int customerID, int contactID) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerID = customerID;
        this.contactID = contactID;
    }

    /**
     * Empty appointment member
     */
    public Appointments() {

    }

    /**
     * @return amount of each appointment type
     */
    public int getTypeAmount(){
        return typeAmount;
    }

    /**
     * @return the type of appointment
     */
    public String getTypeDescription(){
        return typeDescription;
    }

    /**
     * @return the month of the identified type of appointment.
     */
    public String getTypeMonth(){
        return typeMonth;
    }

    /**
     * @return appointment ID number
     */
    public int getAppointment_id() {
        return this.appointment_id;
    }

    /**
     * @return appointment title
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return the appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * @return start time in Local Date Time format
     */
    public LocalDateTime getStartTime(){
        return startTime;
    }
    /**
     * @return end time in Local Date Time format
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
    /**
     * @return appointment creation date in Local Date Time format
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**
     * @return who created the appointment in the database
     */
    public String getCreatedBy(){
        return createdBy;
    }
    /**
     * @return last time the appointment was updated in Local Date Time format
     */
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    /**
     * @return who last updated the appointment in the database
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /**
     * @return customer ID number
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * @return user ID number
     */
    public int getUserID(){
        return userID;
    }

    /**
     * @return contact ID number
     */
    public int getContactID(){
        return contactID;
    }

}
