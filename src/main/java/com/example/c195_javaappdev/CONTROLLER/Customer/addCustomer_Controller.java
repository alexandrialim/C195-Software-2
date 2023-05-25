package com.example.c195_javaappdev.CONTROLLER.Customer;

import com.example.c195_javaappdev.DAO.JDBC;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    public ComboBox<First_Level_Divisions> custState;
    @FXML
    public ComboBox<Countries> custCountry;

    public void initialize(){
        custCountry.setItems(queryCountries.getCountriesList());


//        ObservableList<queryFirstLevelDivision> fullFLDList = queryFirstLevelDivision.getFirstLevelDivisionList();
//        ObservableList<String> fldStates = FXCollections.observableArrayList();
//        for (com.example.c195_javaappdev.DAO.queryFirstLevelDivision queryFirstLevelDivision : fullFLDList) {
//            fldStates.add(queryFirstLevelDivision.getDivision());
//        }
//        custState.setItems(fldStates);
//        ObservableList<queryCountries> totalCountries= queryCountries.getCountriesList();
//        ObservableList<String> countryName = FXCollections.observableArrayList();
//        totalCountries.forEach(countries -> countryName.add(countries.getCountry()));
//        custState.setItems(countryName);
//
//        ObservableList<queryFirstLevelDivision> totalStates = queryFirstLevelDivision.getFirstLevelDivisionList();
//        ObservableList<String> fldStateName = FXCollections.observableArrayList();
//        totalStates.forEach(firstLevelDivision -> fldStateName.add(firstLevelDivision.getDivision()));
//        custState.setItems(fldStateName);


//        ObservableList<queryFirstLevelDivision> fullFLDList = queryFirstLevelDivision.getFirstLevelDivisionList();
//        ObservableList<String> fldStates = FXCollections.observableArrayList();
//        for (com.example.c195_javaappdev.DAO.queryFirstLevelDivision queryFirstLevelDivision : fullFLDList) {
//            fldStates.add(queryFirstLevelDivision.getDivision());
//        }
//        custState.setItems(fldStates);
//
//        ObservableList<queryCountries> countryList = queryCountries.getCountriesList();
//        custCountry.setItems(queryCountries.getCountriesList());

//        ObservableList<String> countriesToChooseFrom = FXCollections.observableArrayList();
//        ObservableList<queryCountries> countryList = queryCountries.getCountriesList();
//        for (com.example.c195_javaappdev.DAO.queryCountries queryFirstLevelDivision : countryList) {
//            fldStates.add(queryFirstLevelDivision.getDivision());
//        }
//        custCountry.setItems(countriesToChooseFrom);
//
////        for (com.example.c195_javaappdev.DAO.queryCountries queryCountries : countryList) {
////            countriesToChooseFrom.add(queryCountries.getCountry());
////        }
//
//
//        ObservableList<queryFirstLevelDivision> fullFLDList = queryFirstLevelDivision.getFirstLevelDivisionList();
//        ObservableList<String> fldStates = FXCollections.observableArrayList();
//        for (com.example.c195_javaappdev.DAO.queryFirstLevelDivision queryFirstLevelDivision : fullFLDList) {
//            fldStates.add(queryFirstLevelDivision.getDivision());
//        }
//        custState.setItems(fldStates);
    }

        //fullFLDList.forEach(firstLevelDivision -> fldStates.add(firstLevelDivision.getDivision()));
        //custCountry.setItems(queryCountries.getCountriesList());
        //ObservableList<queryCountries> countryList = queryCountries.getCountriesList();
//        ObservableList<String> countriesToChooseFrom = FXCollections.observableArrayList();
//        for (com.example.c195_javaappdev.DAO.queryCountries queryCountries : countryList) {
//            countriesToChooseFrom.add(queryCountries.getCountry());
//        }

        //for (com.example.c195_javaappdev.DAO.queryFirstLevelDivision queryFirstLevelDivision : fullFLDList) {
//            fldStates.add(queryFirstLevelDivision.getDivision());
//        }
        //custCountry.setItems(countriesToChooseFrom);
        //custCountry.setItems(queryCountries.getCountriesList());

    public void clicktoSave(ActionEvent actionEvent) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        int divisionID = custState.getValue().getDivision_id();
        if (!custAddress.getText().isEmpty() || !custPostCode.getText().isEmpty() || !custState.getSelectionModel().isEmpty() || !custCountry.getSelectionModel().isEmpty() || !custPhone.getText().isEmpty()){
            queryCustomerData.insertCustomerList(custName,custAddress,custPostCode,custPhone,divisionID);

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
    @FXML
    public void selectCountry(ActionEvent actionEvent){
        try {
            Countries selectCountry = custCountry.getValue();
            custState.setItems(queryFirstLevelDivision.getDivisionsByCountryID(selectCountry.country_id));

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


//
//        ObservableList<String> countryL = FXCollections.observableArrayList();
//        ObservableList<queryCountries> country = queryCountries.getCountriesList();
//        if (country != null) {
//            for (Countries eachCountry: country) {
//                countryL.add(eachCountry.getCountry());
//            }
//        }
//        custState.setItems(countryL);



//    void SelectCountry(ActionEvent event) {
//        ObservableList<String> divisionList = FXCollections.observableArrayList();
//        try {
//            ObservableList<Division> divisions = DivisionQuery.getDivisionsByCountry(CountryCombo.getSelectionModel().getSelectedItem());
//            if (divisions != null) {
//                for (Division division: divisions) {
//                    divisionList.add(division.getDivision());
//                }
//            }
//            DivisionCombo.setItems(divisionList);
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

}
