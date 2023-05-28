package com.example.c195_javaappdev.MODEL;


import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

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
    public Appointments(int typeNum, String typeDesc, String monthName) {
        this.typeAmount = typeNum;
        this.typeDescription = typeDesc;
        this.typeMonth = monthName;
    }

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

    public Appointments() {

    }

    public int getTypeAmount(){
        return typeAmount;
    }
    public String getTypeDescription(){
        return typeDescription;
    }
    public String getTypeMonth(){
        return typeMonth;
    }
    public int getAppointment_id() {
        return this.appointment_id;
    }
    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public LocalDateTime getCreateDate(){
        return createDate;
    }
    public String getCreatedBy(){
        return createdBy;
    }
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }
    public int getCustomerID(){
        return customerID;
    }
    public int getUserID(){
        return userID;
    }
    public int getContactID(){
        return contactID;
    }

}
