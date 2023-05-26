package com.example.c195_javaappdev.MODEL;

public class Contacts {
    public int contact_id;
    public String contact_name;
    public String email;

    public Contacts(int contactId, String contactName, String email) {
        this.contact_id = contactId;
        this.contact_name = contactName;
        this.email = email;
    }

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

    /**
     * @return the Contact String name
     */
    @Override
    public String toString(){return contact_name;}
}
