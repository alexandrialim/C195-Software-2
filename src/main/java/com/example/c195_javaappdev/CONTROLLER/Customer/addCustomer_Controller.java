package com.example.c195_javaappdev.CONTROLLER.Customer;

import com.example.c195_javaappdev.DAO.queryCountries;
import com.example.c195_javaappdev.DAO.queryCustomerData;
import com.example.c195_javaappdev.DAO.queryFirstLevelDivision;
import com.example.c195_javaappdev.MODEL.Customers;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class addCustomer_Controller {

    @FXML
    public Button cancelCustomerAdd;
    @FXML
    public Button saveCustomerAdd;
    @FXML
    public TextField custID;
    @FXML
    public TextField custName;
    @FXML
    public TextField custAddress;
    @FXML
    public TextField custPostCode;
    @FXML
    public TextField custPhone;
    @FXML
    public TextField custDID;
    @FXML
    public ComboBox custState;
    @FXML
    public ComboBox custCountry;

    public void initialize(){
        ObservableList<queryFirstLevelDivision> fullFLDList = queryFirstLevelDivision.getFirstLevelDivisionList();
        ObservableList<String> fldStates = FXCollections.observableArrayList();
        for (com.example.c195_javaappdev.DAO.queryFirstLevelDivision queryFirstLevelDivision : fullFLDList) {
            fldStates.add(queryFirstLevelDivision.getDivision());
        }
        custState.setItems(fldStates);

        ObservableList<queryCountries> countryList = queryCountries.getCountriesList();
        ObservableList<String> countriesToChooseFrom = FXCollections.observableArrayList();
        for (com.example.c195_javaappdev.DAO.queryCountries queryCountries : countryList) {
            countriesToChooseFrom.add(queryCountries.getCountry());
        }
        custCountry.setItems(countriesToChooseFrom);
    }
    public void clicktoSave(ActionEvent actionEvent) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        if (!custAddress.getText().isEmpty() || !custPostCode.getText().isEmpty() || !custState.getSelectionModel().isEmpty() || !custCountry.getSelectionModel().isEmpty() || !custPhone.getText().isEmpty()){
            queryCustomerData.insertCustomerList(custName,custAddress,custPostCode,custPhone,custState);

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

}
