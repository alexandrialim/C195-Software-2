package com.example.c195_javaappdev.DAO;

import com.example.c195_javaappdev.MODEL.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryContacts {

    /**
     * Observablelist for all appointments in the database
     * @return cList = Customer list
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

//    public static ObservableList<Contacts> getContactsByID(int contactID){
//        ObservableList<Contacts> contactList = FXCollections.observableArrayList();
//        try {
//            contactList = queryContacts.getContactList().stream()
//                    .filter(contacts -> contacts.getContact_id() == contactID)
//                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return contactList;
//    }

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
