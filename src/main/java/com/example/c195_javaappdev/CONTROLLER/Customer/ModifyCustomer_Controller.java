package com.example.c195_javaappdev.CONTROLLER.Customer;

import com.example.c195_javaappdev.CONTROLLER.Appointment.Appointments_CustomerController;
import com.example.c195_javaappdev.DAO.QueryCountries;
import com.example.c195_javaappdev.DAO.QueryCustomerData;
import com.example.c195_javaappdev.DAO.QueryFirstLevelDivision;
import com.example.c195_javaappdev.MODEL.Countries;
import com.example.c195_javaappdev.MODEL.Customers;
import com.example.c195_javaappdev.MODEL.First_Level_Divisions;
import com.example.c195_javaappdev.Main;
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

/**
 * This class holds methods for modifying customer information.
 */
public class ModifyCustomer_Controller {
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

    /**
     * This method initializes the customer form and loads all countries and associated states to choose from.
     */
    public void initialize(){
        try {
            custCountry.setItems(QueryCountries.getCountriesList());

            selectedCustomer = Appointments_CustomerController.returnCustomerToModify();
            custID.setText(String.valueOf(selectedCustomer.getCustomer_id()));
            custName.setText(selectedCustomer.getCustomer_name());
            custAddress.setText(selectedCustomer.getAddress());
            custPost.setText(selectedCustomer.getZipcode());
            custPhone.setText(selectedCustomer.getPhone_number());

            First_Level_Divisions fld = QueryFirstLevelDivision.getFirstLevelDivisionByID(selectedCustomer.getDivision_id());
            Countries country = QueryCountries.getCountryByID(fld.getCountry_id());
            custState.setItems(QueryFirstLevelDivision.getDivisionsByCountryID(country.getCountry_id()));
            custCountry.setValue(country);
            custState.setValue(fld);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows the user to save modified customer data.
     * @param actionEvent When clicked, the modified customer data will be loaded into the customer table.
     * @throws IOException
     */
    public void clicktoSave(ActionEvent actionEvent) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        int divisionID = custState.getValue().getDivision_id();
        if (!custAddress.getText().isEmpty() || !custPost.getText().isEmpty() || !custState.getSelectionModel().isEmpty()
                || !custCountry.getSelectionModel().isEmpty() || !custPhone.getText().isEmpty()){

            QueryCustomerData.updateCustomerList(Integer.parseInt(custID.getText()),custName,custAddress, custPost
                    , custPhone, divisionID);

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

    /**
     * This method allows the user to go back to the main screen without saving any changes.
     * @param actionEvent When clicked the user will be routed back to the main screen.
     * @throws IOException
     */
    public void exitStage(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader,1100, 700);
        stage.setTitle("Appointments/Customer Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method grabs the select country and associated states/provinces.
     * @param actionEvent when the drop-down is clicked, the user can see 3 countries to choose from and
     *                   the associated states/provinces to choose from.
     */
    @FXML
    public void selectedCountry(ActionEvent actionEvent) {
        try {
            Countries selectCountry = custCountry.getValue();
            custState.setItems(QueryFirstLevelDivision.getDivisionsByCountryID(selectCountry.country_id));

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
