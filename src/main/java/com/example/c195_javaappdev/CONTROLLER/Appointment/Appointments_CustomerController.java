package com.example.c195_javaappdev.CONTROLLER.Appointment;

import com.example.c195_javaappdev.DAO.queryAppointments;
import com.example.c195_javaappdev.DAO.queryCustomerData;
import com.example.c195_javaappdev.MODEL.Appointments;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class Appointments_CustomerController {
    private static Customers customerToModify;
    private static Customers customerToDelete;
    private static Appointments appointmentToModify;
    private static Appointments appointmentToDelete;
    private static int countryToModify_fldID;
    @FXML
    public TableColumn appointmentID;
    @FXML
    public TableColumn appointmentTitle;
    @FXML
    public TableColumn appointmentDescription;
    @FXML
    public TableColumn appointmentLocation;
    @FXML
    public TableColumn appointmentType;
    @FXML
    public TableColumn appointmentStart;
    @FXML
    public TableColumn appointmentEnd;
    @FXML
    public TableColumn appointmentCreated;
    @FXML
    public TableColumn appointmentCreatedBy;
    @FXML
    public TableColumn appointmentLastUpdate;
    @FXML
    public TableColumn appointmentLastUpdatedBy;
    @FXML
    public TableColumn appointmentCustomerID;
    @FXML
    public TableColumn appointmentUserID;
    @FXML
    public TableColumn appointmentContactID;
    @FXML
    public Label Label1;
    @FXML
    public Label appointmentLabel;
    @FXML
    public Label customerLabel;
    @FXML
    public TableColumn customerID;
    @FXML
    public TableColumn customerName;
    @FXML
    public TableColumn customerAddress;
    @FXML
    public TableColumn customerPostalCode;
    @FXML
    public TableColumn customerPhone;
    @FXML
    public TableColumn customerCreateDate;
    @FXML
    public TableColumn customerCreatedBy;
    @FXML
    public TableColumn customerLastUpdate;
    @FXML
    public TableColumn customerLastUpdatedBy;
    @FXML
    public TableColumn customerDivisionID;
    @FXML
    public TableView<Customers> customerTable;
    @FXML
    public Button addAppointment;
    @FXML
    public Button modifyAppointment;
    @FXML
    public Button deleteAppointment;
    @FXML
    public Button addCustomer;
    @FXML
    public Button modifyCustomer;
    @FXML
    public RadioButton weekFilter;
    @FXML
    public RadioButton monthFilter;
    @FXML
    public RadioButton allAppointments;
    @FXML
    private TableView<Appointments> appointmentTable;



    /* /**
     * This method initializes the controller
     * @param ResourceBundle a customized resource used to translate between english and French depending on the users
     *                       computer language settings
     */
    public void initialize() throws SQLException {
        ResourceBundle b = ResourceBundle.getBundle("language", Locale.getDefault());
        Label1.setText(b.getString("Label1.text"));
        appointmentLabel.setText(b.getString("appointmentLabel.text"));
        appointmentID.setText(b.getString("appointmentID.text"));
        appointmentTitle.setText(b.getString("appointmentTitle.text"));
        appointmentDescription.setText(b.getString("appointmentDescription.text"));
        appointmentLocation.setText(b.getString("appointmentLocation.text"));
        appointmentType.setText(b.getString("appointmentType.text"));
        appointmentStart.setText(b.getString("appointmentStart.text"));
        appointmentEnd.setText(b.getString("appointmentEnd.text"));
        appointmentCreated.setText(b.getString("appointmentCreated.text"));
        appointmentCreatedBy.setText(b.getString("appointmentCreatedBy.text"));
        appointmentLastUpdate.setText(b.getString("appointmentLastUpdate.text"));
        appointmentLastUpdatedBy.setText(b.getString("appointmentLastUpdatedBy.text"));
        appointmentCustomerID.setText(b.getString("appointmentCustomerID.text"));
        appointmentUserID.setText(b.getString("appointmentUserID.text"));
        appointmentContactID.setText(b.getString("appointmentContactID.text"));


        customerLabel.setText(b.getString("customerLabel.text"));
        customerID.setText(b.getString("customerID.text"));
        customerName.setText(b.getString("customerName.text"));
        customerAddress.setText(b.getString("customerAddress.text"));
        customerPostalCode.setText(b.getString("customerPostalCode.text"));
        customerPhone.setText(b.getString("customerPhone.text"));
        customerCreateDate.setText(b.getString("customerCreateDate.text"));
        customerCreatedBy.setText(b.getString("customerCreatedBy.text"));
        customerLastUpdate.setText(b.getString("customerLastUpdate.text"));
        customerLastUpdatedBy.setText(b.getString("customerLastUpdatedBy.text"));
        customerDivisionID.setText(b.getString("customerDivisionID.text"));

        ObservableList<Appointments> appointmentListMain = queryAppointments.getAppointmentList();
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentCreated.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        appointmentCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        appointmentLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appointmentLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appointmentContactID.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentTable.setItems(appointmentListMain);

        ObservableList<Customers> customerListMain = queryCustomerData.getCustomerList();
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        customerCreateDate.setCellValueFactory(new PropertyValueFactory<>("create_date"));
        customerCreatedBy.setCellValueFactory(new PropertyValueFactory<>("created_by"));
        customerLastUpdate.setCellValueFactory(new PropertyValueFactory<>("last_update"));
        customerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("last_updated_by"));
        customerDivisionID.setCellValueFactory(new PropertyValueFactory<>("division_id"));
        customerTable.setItems(customerListMain);

        ToggleGroup radioToggleGroup = new ToggleGroup();
        allAppointments.setToggleGroup(radioToggleGroup);
        allAppointments.setSelected(true);
        monthFilter.setToggleGroup(radioToggleGroup);
        weekFilter.setToggleGroup(radioToggleGroup);
    }


    public void clicktoAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/addAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader, 400, 550);
        stage.setTitle("Add Customer Appointments");
        stage.setScene(scene);
        stage.show();

    }

    public void clicktoModifyAppointment(ActionEvent actionEvent) throws IOException {
        appointmentToModify = appointmentTable.getSelectionModel().getSelectedItem();

        Alert modifyAppointmentAlert = new Alert(Alert.AlertType.NONE);
        if(appointmentToModify == null){
            modifyAppointmentAlert.setAlertType(Alert.AlertType.ERROR);
            modifyAppointmentAlert.setContentText("No appointment was selected, " +
                    "so no appointment can be modified from the table");
            modifyAppointmentAlert.showAndWait();
        }
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/modifyAppointmentForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader, 400, 550);
        stage.setTitle("Modify Customer Appointments");
        stage.setScene(scene);
        stage.show();
    }

    public void clicktoDeleteAppointment(ActionEvent actionEvent) {
        appointmentToDelete = appointmentTable.getSelectionModel().getSelectedItem();
        Alert deleteAppointmentAlert = new Alert(Alert.AlertType.NONE);
        if(appointmentToDelete == null){
            deleteAppointmentAlert.setAlertType(Alert.AlertType.ERROR);
            deleteAppointmentAlert.setContentText("No appointment was selected, " +
                    "so no appointment can be deleted from the table");
            deleteAppointmentAlert.showAndWait();
        }else{
            queryAppointments.deleteAppointmentFromList(appointmentTable.getSelectionModel().getSelectedItem()
                    .getAppointment_id(), appointmentTable.getSelectionModel().getSelectedItem().getType());
            Parent fxmlLoader = null;
            try {
                fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //Create Scene
            Scene scene = new Scene(fxmlLoader,1100, 700);
            stage.setTitle("Appointments/Customer Page");
            stage.setScene(scene);
            stage.show();
            queryAppointments.deleteSuccessful.showAndWait();
        }
    }

    public void clicktoDeleteCustomer(ActionEvent actionEvent) {
        customerToDelete = customerTable.getSelectionModel().getSelectedItem();
        Alert modifyCustomerAlert = new Alert(Alert.AlertType.NONE);
        if(customerToDelete == null){
            modifyCustomerAlert.setAlertType(Alert.AlertType.ERROR);
            modifyCustomerAlert.setContentText("No customer was selected, " +
                    "so no customer can be deleted from the table");
            modifyCustomerAlert.showAndWait();
        }
        else{
            queryCustomerData.deleteCustomerFromList(customerTable.getSelectionModel().getSelectedItem().getCustomer_id());
            Parent fxmlLoader = null;
            try {
                fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //Create Scene
            Scene scene = new Scene(fxmlLoader,1100, 700);
            stage.setTitle("Appointments/Customer Page");
            stage.setScene(scene);
            stage.show();
            queryCustomerData.deleteSuccessful.showAndWait();
        }
    }


    public void clicktoModifyCustomer(ActionEvent actionEvent) throws IOException {
        customerToModify = customerTable.getSelectionModel().getSelectedItem();
        countryToModify_fldID = customerTable.getSelectionModel().getSelectedItem().getDivision_id();

        Alert modifyCustomerAlert = new Alert(Alert.AlertType.NONE);
        if(customerToModify == null){
            modifyCustomerAlert.setAlertType(Alert.AlertType.ERROR);
            modifyCustomerAlert.setContentText("No customer was selected, " +
                    "so no customer can be modified from the table");
            modifyCustomerAlert.showAndWait();
        }
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/CustomerForms/modifyCustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader, 600, 400);
        stage.setTitle("Modify Customer Information");
        stage.setScene(scene);
        stage.show();
    }

    public void clicktoAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/CustomerForms/addCustomerForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //Create Scene
        Scene scene = new Scene(fxmlLoader, 600, 400);
        stage.setTitle("Add Customer Information");
        stage.setScene(scene);
        stage.show();
    }

    public void clickToFilterAppointmentByWeek(ActionEvent actionEvent) {
        appointmentTable.setItems(queryAppointments.getAppointmentsThisWeek());
    }

    public void clickToFilterAppointmentByMonth(ActionEvent actionEvent) {
        appointmentTable.setItems(queryAppointments.getAppointmentsThisMonth());
    }

    public void clickToGetAllAppointments(ActionEvent actionEvent) {
        appointmentTable.setItems(queryAppointments.getAppointmentList());
    }
    public void exitStage(ActionEvent actionEvent) {
    }

    /**
     * This method allows for part data to be pulled from main screen.
     *
     * @return returns the part selected to be modified.
     */
    public static int returnCustomerDivisionIDToModify(){
        return countryToModify_fldID;
    }

    public static Customers returnCustomerToModify() {
        return customerToModify;
    }
    public static Appointments returnAppointmentToModify() {
        return appointmentToModify;
    }

}
