package com.example.c195_javaappdev.CONTROLLER.Appointment;

import com.example.c195_javaappdev.DAO.queryAppointments;
import com.example.c195_javaappdev.DAO.queryContacts;
import com.example.c195_javaappdev.DAO.queryCustomerData;
import com.example.c195_javaappdev.DAO.queryUserInfo;
import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.MODEL.Contacts;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class modifyAppointment_Controller {
    @FXML
    public TextField appID;
    @FXML
    public TextField appTitle;
    @FXML
    public TextField appDesc;
    @FXML
    public TextField appLocation;
    @FXML
    public ComboBox<Contacts> appContact;
    @FXML
    public TextField appType;
    @FXML
    public DatePicker appStartD;
    @FXML
    public ComboBox<LocalTime> appStartT;
    @FXML
    public ComboBox<LocalTime> appEndT;
    @FXML
    public ComboBox appCustID;
    @FXML
    public ComboBox appUserID;
    @FXML
    public Button cancel;
    @FXML
    public Button save;
    private Appointments selectedAppointment;

    public void initialize() throws SQLException {
        selectedAppointment = Appointments_CustomerController.returnAppointmentToModify();
        appID.setText(String.valueOf(selectedAppointment.getAppointment_id()));
        appTitle.setText(selectedAppointment.getTitle());
        appDesc.setText(selectedAppointment.getDescription());
        appType.setText(selectedAppointment.getType());
        appLocation.setText(selectedAppointment.getLocation());
        appContact.setItems(queryContacts.getContactList());
        appContact.setValue(queryContacts.getContactFromContactID(selectedAppointment.getContactID()));
        appType.setText(selectedAppointment.getType());
        appUserID.setItems(queryUserInfo.getUserList());
        appUserID.setValue(selectedAppointment.getUserID());
        appCustID.setItems(queryCustomerData.getCustomerList());
        appCustID.setValue(selectedAppointment.getCustomerID());
        appStartD.setValue(selectedAppointment.getStartTime().toLocalDate());
        LocalTime getHeadStartTime = LocalTime.of(5,0,0);
        LocalTime getTailStartTime = LocalTime.of(15,0,0);
        LocalTime getHeadEndTime = LocalTime.of(6,0,0);
        LocalTime getTailEndTime = LocalTime.of(17,0,0);

        while(getHeadStartTime.isBefore(getTailStartTime)){ //.plusSeconds(0)
            appStartT.getItems().add(getHeadStartTime);
            getHeadStartTime = getHeadStartTime.plusMinutes(30);

            while(getHeadEndTime.isBefore(getTailEndTime)){ //.plusSeconds(0)
                appEndT.getItems().add(getHeadEndTime);
                getHeadEndTime = getHeadEndTime.plusMinutes(15);
            }
        }
        //convert and populate selected utc START time to local user time
        LocalDateTime startDateTimeValue = LocalDateTime.of(selectedAppointment.getStartTime().toLocalDate()
                , selectedAppointment.getStartTime().toLocalTime());
        ZonedDateTime stDT = startDateTimeValue.atZone(ZoneId.of("UTC"));
        LocalDateTime convertSTDT = stDT.toLocalDateTime().atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of(String.valueOf(ZoneId.systemDefault()))).toLocalDateTime();

        System.out.println(stDT);
        System.out.println(convertSTDT);

        //convert and populate selected utc END time to local user time
        LocalDateTime endDateTimeValue = LocalDateTime.of(selectedAppointment.getEndTime().toLocalDate()
                , selectedAppointment.getEndTime().toLocalTime());
        ZonedDateTime eDT = endDateTimeValue.atZone(ZoneId.of("UTC"));
        LocalDateTime convertEndDT = eDT.toLocalDateTime().atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of(String.valueOf(ZoneId.systemDefault()))).toLocalDateTime();

        System.out.println(eDT);
        System.out.println(convertEndDT);
        appStartT.setValue(LocalTime.from(convertSTDT));
       // appStartT.setValue(convertToLocalTimeStart.toLocalTime());
        appEndT.setValue(LocalTime.from(convertEndDT));
    }

    public void clicktoSave(ActionEvent actionEvent) throws IOException, ParseException {
        Alert insertError = new Alert(Alert.AlertType.ERROR);
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        LocalDateTime startDateTimeValue = LocalDateTime.of(appStartD.getValue(), appStartT.getValue());
        LocalDateTime endDateTimeValue = LocalDateTime.of(appStartD.getValue(), appEndT.getValue());
        Timestamp createDateValue = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdateValue = Timestamp.valueOf(LocalDateTime.now());
        int appointmentID = Integer.parseInt(appID.getText());

        //convert to start and end time to utc time
        ZonedDateTime headUTC = startDateTimeValue.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endUTC = endDateTimeValue.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

        //convert to user time zone from UTC
        ZonedDateTime localtime = headUTC.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println(headUTC);
        System.out.println(localtime);


        if (!appTitle.getText().isEmpty() || !appDesc.getText().isEmpty() || !appContact.getSelectionModel().isEmpty()
                || !appType.getText().isEmpty() || !appLocation.getText().isEmpty() || !headUTC.equals(null)
                || !endUTC.equals(null) || !appCustID.getSelectionModel().isEmpty()
                || !appUserID.getSelectionModel().isEmpty()){
            if(headUTC.isAfter(endUTC)){
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is after End time.");
                insertError.showAndWait();
            //} else if () {

            } else{
                queryAppointments.updateAppointmentList(appointmentID,appTitle.getText(),appDesc.getText()
                        ,appContact.getValue().getContact_id(),appType.getText(),appLocation.getText(), headUTC.toLocalDateTime()
                        ,endUTC.toLocalDateTime(),appCustID.getValue().toString(),appUserID.getValue().toString(),lastUpdateValue);

                Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(fxmlLoader,1100, 700);
                stage.setTitle("Appointments/Customer Page");
                stage.setScene(scene);
                stage.show();
                queryAppointments.updateSuccessful.showAndWait();
            }
        }else{
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
}
