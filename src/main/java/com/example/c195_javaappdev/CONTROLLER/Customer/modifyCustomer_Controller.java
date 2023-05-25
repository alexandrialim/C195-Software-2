package com.example.c195_javaappdev.CONTROLLER.Customer;

import com.example.c195_javaappdev.CONTROLLER.Appointment.Appointments_CustomerController;
import com.example.c195_javaappdev.DAO.queryCountries;
import com.example.c195_javaappdev.DAO.queryCustomerData;
import com.example.c195_javaappdev.DAO.queryFirstLevelDivision;
import com.example.c195_javaappdev.MODEL.Countries;
import com.example.c195_javaappdev.MODEL.Customers;
import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import com.example.c195_javaappdev.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class modifyCustomer_Controller {
    private Customers selectedCustomer;
    @FXML
    public Button saveCustomerModify;
    @FXML
    public Button cancelCustomerModify;
    @FXML
    public TextField custName;
    @FXML
    public TextField custID;
    @FXML
    public TextField custAddress;
    @FXML
    public TextField custPost;
    @FXML
    public TextField custPhone;
    @FXML
    public TextField custDID;
    @FXML
    public ComboBox<First_Level_Divisions> custState;
    @FXML
    public ComboBox<Countries> custCountry;

    public void initialize(){
        try {
            custCountry.setItems(queryCountries.getCountriesList());

            selectedCustomer = Appointments_CustomerController.returnCustomerToModify();
            //ObservableList<Customers> customerListMain = queryCustomerData.getCustomerList();
            custID.setText(String.valueOf(selectedCustomer.getCustomer_id()));
            custName.setText(selectedCustomer.getCustomer_name());
            custAddress.setText(selectedCustomer.getAddress());
            custPost.setText(selectedCustomer.getZipcode());
            custPhone.setText(selectedCustomer.getPhone_number());
            //custDID.setText(String.valueOf(selectedCustomer.getDivision_id()));
            First_Level_Divisions fld = queryFirstLevelDivision.getFirstLevelDivisionByID(selectedCustomer.getDivision_id());
            Countries country = queryCountries.getCountryByID(fld.getCountry_id());
            custState.setItems(queryFirstLevelDivision.getDivisionsByCountryID(country.getCountry_id()));
            custCountry.setValue(country);
            custState.setValue(fld);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //customerTable.setItems(customerListMain);
    }

    public void clicktoSave(ActionEvent actionEvent) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        int divisionID = custState.getValue().getDivision_id();
        //System.out.println(fldCountry);
        //System.out.println(returnCountryName);
        //System.out.println(myCountryID);
        //System.out.println(DID);
//        for(queryFirstLevelDivision fld : queryFirstLevelDivision.getFirstLevelDivisionList()) {
//            if (DID == myCountry.getCountry_id()) {
//                System.out.println(myCountry.getCountry());
//            }
//        }
        //selectedCountry(actionEvent, Appointments_CustomerController.returnCustomerDivisionIDToModify());
        if (!custAddress.getText().isEmpty() || !custPost.getText().isEmpty() || !custState.getSelectionModel().isEmpty() || !custCountry.getSelectionModel().isEmpty() || !custPhone.getText().isEmpty()){

            queryCustomerData.insertCustomerList(custName,custAddress,custPost,custPhone,divisionID);

            Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //Create Scene
            Scene scene = new Scene(fxmlLoader,1900, 400);
            stage.setTitle("Appointments/Customer Page");
            stage.setScene(scene);
            stage.show();
        }else{
            Alert insertError = new Alert(Alert.AlertType.ERROR);
            insertError.setAlertType(Alert.AlertType.ERROR);
            insertError.setContentText(bundle.getString("Error"));
            insertError.showAndWait();
        }
    }


    public void exitStage(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader,1900, 400);
        stage.setTitle("Appointments/Customer Page");
        stage.setScene(scene);
        stage.show();
    }

    public void selectedCountry(ActionEvent actionEvent) {
    }
}
