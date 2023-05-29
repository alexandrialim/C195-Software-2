package com.example.c195_javaappdev.CONTROLLER;

import com.example.c195_javaappdev.DAO.QueryAppointments;
import com.example.c195_javaappdev.DAO.QueryContacts;
import com.example.c195_javaappdev.DAO.QueryReports;
import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.MODEL.Contacts;
import com.example.c195_javaappdev.MODEL.Customers;
import com.example.c195_javaappdev.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

        ObservableList<Appointments> appointmentListMain = QueryAppointments.getAppointmentList();
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

        filterByContact.setItems(QueryContacts.getContactList());

        appType.setCellValueFactory(new PropertyValueFactory<>("typeDescription"));
        totalApps.setCellValueFactory(new PropertyValueFactory<>("typeAmount"));
        months.setCellValueFactory(new PropertyValueFactory<>("typeMonth"));
        ObservableList<Appointments> eachType = QueryReports.getMonthTypeTable();
        appointmentTypeMonthTable.setItems(eachType);

        ObservableList<Customers> customersByDivision = QueryReports.getCustomersByDivisionTable();
        divisions.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        totalCustomers.setCellValueFactory(new PropertyValueFactory<>("divisionCount"));
        customersDivisionsTable.setItems(customersByDivision);

    }
    public void clickToFilterByContact(ActionEvent actionEvent) {
        appointmentTable.setItems(QueryReports.getAppointmentsByContact
                (filterByContact.getSelectionModel().getSelectedItem().getContact_id()));
    }

    public void goBackToHome(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader,1100, 700);
        stage.setTitle("Appointments/Customer Page");
        stage.setScene(scene);
        stage.show();
    }
}
