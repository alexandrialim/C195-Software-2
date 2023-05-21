package CONTROLLER;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppointmentsController {
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


    /* /**
     * This method initializes the controller
     * @param ResourceBundle a customized resource used to translate between english and French depending on the users
     *                       computer language settings
     */
    public void initialize(){
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
    }

    public void clicktoAddAppointment(ActionEvent actionEvent) {
    }

    public void clicktomodifypartform(ActionEvent actionEvent) {
    }

    public void clicktoDeleteAppointment(ActionEvent actionEvent) {
    }

    public void clicktoDeleteCustomer(ActionEvent actionEvent) {
    }

    public void clicktoModifyCustomer(ActionEvent actionEvent) {
    }

    public void clicktoAddCustomer(ActionEvent actionEvent) {
    }

    public void exitStage(ActionEvent actionEvent) {
    }
}
