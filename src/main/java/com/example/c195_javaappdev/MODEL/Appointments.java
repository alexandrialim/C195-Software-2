package com.example.c195_javaappdev.MODEL;

import java.time.LocalDateTime;

public class Appointments {

    private int appointment_id;
    private String title;
    private String description;
    private String location;
    private String type;
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

    public Appointments() {

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
