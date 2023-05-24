package com.example.c195_javaappdev.MODEL;

public class Contacts {
    private int contact_id;
    public String contact_name;
    public String email;

    public int getContact_id() {
        return contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getEmail() {
        return email;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
