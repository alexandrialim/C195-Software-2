package com.example.c195_javaappdev.CONTROLLER;

import com.example.c195_javaappdev.DAO.queryAppointments;
import com.example.c195_javaappdev.DAO.queryContacts;
import com.example.c195_javaappdev.DAO.queryReports;
import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.MODEL.Contacts;
import com.example.c195_javaappdev.MODEL.Customers;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportsController {
    @FXML
    public Button backToMain;
    @FXML
    public TableColumn totalCustomers;
    @FXML
    public TableColumn divisions;
    @FXML
    public TableColumn appType;
    @FXML
    public TableColumn totalApps;
    @FXML
    public TableColumn months;
    @FXML
    public ComboBox<Contacts> filterByContact;
    @FXML
    public TableColumn appointmentContactID;
    @FXML
    public TableColumn appointmentCustomerID;
    @FXML
    public TableColumn appointmentEnd;
    @FXML
    public TableColumn appointmentStart;
    @FXML
    public TableColumn appointmentType;
    @FXML
    public TableColumn appointmentLocation;
    @FXML
    public TableColumn appointmentDescription;
    @FXML
    public TableColumn appointmentTitle;
    @FXML
    public TableColumn appointmentID;
    @FXML
    public TableView<Appointments> appointmentTable;
    @FXML
    public TableView<Customers> customersDivisionsTable;
    @FXML
    public TableView<Appointments> appointmentTypeMonthTable;

    public void initialize() throws SQLException {
        ResourceBundle b = ResourceBundle.getBundle("language", Locale.getDefault());
        appointmentID.setText(b.getString("appointmentID.text"));
        appointmentTitle.setText(b.getString("appointmentTitle.text"));
        appointmentDescription.setText(b.getString("appointmentDescription.text"));
        appointmentLocation.setText(b.getString("appointmentLocation.text"));
        appointmentType.setText(b.getString("appointmentType.text"));
        appointmentStart.setText(b.getString("appointmentStart.text"));
        appointmentEnd.setText(b.getString("appointmentEnd.text"));
        appointmentCustomerID.setText(b.getString("appointmentCustomerID.text"));
        appointmentContactID.setText(b.getString("appointmentContactID.text"));

        ObservableList<Appointments> appointmentListMain = queryAppointments.getAppointmentList();
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentContactID.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentTable.setItems(appointmentListMain);

        filterByContact.setItems(queryContacts.getContactList());

        appType.setCellValueFactory(new PropertyValueFactory<>("typeDescription"));
        totalApps.setCellValueFactory(new PropertyValueFactory<>("typeAmount"));
        months.setCellValueFactory(new PropertyValueFactory<>("typeMonth"));
        ObservableList<Appointments> eachType = queryReports.getMonthTypeTable();
        appointmentTypeMonthTable.setItems(eachType);




    }
}
