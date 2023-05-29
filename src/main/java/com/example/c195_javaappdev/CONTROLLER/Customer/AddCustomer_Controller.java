package com.example.c195_javaappdev.CONTROLLER.Customer;

import com.example.c195_javaappdev.DAO.QueryCountries;
import com.example.c195_javaappdev.DAO.QueryCustomerData;
import com.example.c195_javaappdev.DAO.QueryFirstLevelDivision;
import com.example.c195_javaappdev.MODEL.Countries;
import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import com.example.c195_javaappdev.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddCustomer_Controller {

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
    public ComboBox<First_Level_Divisions> custState;
    @FXML
    public ComboBox<Countries> custCountry;

    public void initialize() {
        custCountry.setItems(QueryCountries.getCountriesList());
    }
    public void clicktoSave(ActionEvent actionEvent) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        int divisionID = custState.getValue().getDivision_id();
        if (!custAddress.getText().isEmpty() || !custPostCode.getText().isEmpty() || !custState.getSelectionModel().isEmpty() || !custCountry.getSelectionModel().isEmpty() || !custPhone.getText().isEmpty()){
            QueryCustomerData.insertCustomerList(custName,custAddress,custPostCode,custPhone,divisionID);

            Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //Create Scene
            Scene scene = new Scene(fxmlLoader,1100, 700);
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
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader,1100, 700);
        stage.setTitle("Appointments/Customer Page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void selectCountry(ActionEvent actionEvent){
        try {
            Countries selectCountry = custCountry.getValue();
            custState.setItems(QueryFirstLevelDivision.getDivisionsByCountryID(selectCountry.country_id));

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
