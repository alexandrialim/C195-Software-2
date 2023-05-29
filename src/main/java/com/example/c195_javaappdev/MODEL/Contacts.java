package com.example.c195_javaappdev.MODEL;
/**
 * This class holds contact data.
 */
public class Contacts {
    public int contact_id;
    public String contact_name;
    public String email;

    /**
     * This gets and sets individual contact data
     * @param contactId contact ID number
     * @param contactName contact name
     * @param email contact email address
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contact_id = contactId;
        this.contact_name = contactName;
        this.email = email;
    }

    /**
     * @return contact id number
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * @return contact name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * @return contact email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * set contact ID
     * @param contact_id contact ID number
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * set contact name
     * @param contact_name contact name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * set contact email address
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the Contact String name
     */
    @Override
    public String toString(){return contact_name;}
}
