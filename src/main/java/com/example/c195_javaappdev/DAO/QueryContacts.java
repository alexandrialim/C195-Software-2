package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class hold methods that run SQL queries to gather different pieces of contact data.
 */
public class QueryContacts {

    /**
     * This method gets all the contacts that exist in the database and loads it into a local array list.
     * Observablelist all contacts in the database
     * @return cList = contact list
     */
    public static ObservableList<Contacts> getContactList(){
        ObservableList<Contacts> cList = FXCollections.observableArrayList();
        try {
            String q = "SELECT * FROM contacts";
            ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
            while (r.next()) {
                int contact_id = r.getInt("Contact_ID");
                String contact_name = r.getString("Contact_Name");
                String email = r.getString("Email");
                Contacts contact = new Contacts(contact_id, contact_name,email);
                cList.add(contact);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cList;
    }

    /**
     * This method gets contact names based on the provided contact ID.
     * @param contactID associated contact ID number
     * @return contact name that is associated with the provided contact ID.
     * @throws SQLException
     */
    public static Contacts getContactFromContactID(int contactID) throws SQLException {
        String q = "select * from contacts where Contact_ID = " + contactID;
        ResultSet r = JDBC.connection.prepareStatement(q).executeQuery();
        while (r.next()) {
            int contact_id = r.getInt("Contact_ID");
            String contact_name = r.getString("Contact_Name");
            String email = r.getString("Email");
            Contacts contact = new Contacts(contact_id, contact_name,email);
                return contact;
        }
        return null;
    }
}
