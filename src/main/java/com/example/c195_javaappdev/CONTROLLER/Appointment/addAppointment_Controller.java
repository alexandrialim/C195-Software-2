package com.example.c195_javaappdev.CONTROLLER.Appointment;

import com.example.c195_javaappdev.DAO.*;
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
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class addAppointment_Controller {
    private static Appointments newlyAddedAppointmentID;
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
    private LocalDate dayofWeek;
    LocalTime twelvePM_UTC_1 = LocalTime.of(12,0,0);
    LocalTime twoAM_UTC_1 = LocalTime.of(2,0,0);
    ZonedDateTime twelvePM_UTC;

    public void initialize() throws Exception {
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
        appUserID.setItems(queryUserInfo.getUserList());
        appCustID.setItems(queryCustomerData.getCustomerList());
        appContact.setItems(queryContacts.getContactList());

        LocalTime twelvePM_UTC_1 = LocalTime.of(12,0,0);

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

    public void clicktoSave(ActionEvent actionEvent) throws IOException {
        Alert insertError = new Alert(Alert.AlertType.ERROR);
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        LocalDateTime startDateTimeValue = LocalDateTime.of(appStartD.getValue(), appStartT.getValue());
        LocalDateTime endDateTimeValue = LocalDateTime.of(appStartD.getValue(), appEndT.getValue());
        Timestamp createDateValue = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdateValue = Timestamp.valueOf(LocalDateTime.now());

        //convert time to UTC
        ZonedDateTime headUTC = startDateTimeValue.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endUTC = endDateTimeValue.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

        //convert to user time zone from UTC
        ZonedDateTime localtime = headUTC.withZoneSameInstant(ZoneId.systemDefault());

        ZonedDateTime twelvePM_UTC = ZonedDateTime.of(appStartD.getValue(),twelvePM_UTC_1, ZoneId.of("UTC"));
        ZonedDateTime twoAM_UTC = ZonedDateTime.of(appStartD.getValue(),twoAM_UTC_1, ZoneId.of("UTC"));
        dayofWeek = appStartD.getValue();

        if (!appTitle.getText().isEmpty() || !appDesc.getText().isEmpty() || !appContact.getSelectionModel().isEmpty()
                || !appType.getText().isEmpty() || !appLocation.getText().isEmpty() || !headUTC.equals(null)
                || !endUTC.equals(null) || !appCustID.getSelectionModel().isEmpty()
                || !appUserID.getSelectionModel().isEmpty()){

            if(headUTC.isAfter(endUTC)){
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is AFTER end time.");
                insertError.showAndWait();
            }
            if (headUTC.equals(endUTC)) {
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is the SAME as end time.");
                insertError.showAndWait();
            }
            if (headUTC.isBefore(twelvePM_UTC)) {
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is before standard business hours start");
                insertError.showAndWait();
            }
            if (endUTC.isAfter(twoAM_UTC) && endUTC.isBefore(twelvePM_UTC)) {
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: End time is outside standard business hours start");
                insertError.showAndWait();
            }
            if (dayofWeek.getDayOfWeek() == DayOfWeek.SATURDAY || dayofWeek.getDayOfWeek() == DayOfWeek.SUNDAY){//(dayofWeek.getDayOfWeek().equals("SATURDAY") || dayofWeek.getDayOfWeek().equals("SUNDAY")) {
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: You have selected a weekend. We are only open from Monday - Friday");
                insertError.showAndWait();
            }
            try {
                if(queryAppointments.appointmentConflict(appStartD.getValue(),appStartT.getValue(),appEndT.getValue()) == false){
                    insertError.setAlertType(Alert.AlertType.ERROR);
                    insertError.setContentText("Error: The appointment time that you have selected overlaps with another appointment.");
                    insertError.showAndWait();
                }else {
                queryAppointments.addAppointment(appTitle.getText(), appDesc.getText(), appContact.getValue().getContact_id()
                        , appType.getText(), appLocation.getText(), headUTC.toLocalDateTime(), endUTC.toLocalDateTime()
                        , appCustID.getValue().toString(), appUserID.getValue().toString(), createDateValue, lastUpdateValue);

                Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(fxmlLoader, 1100, 700);
                stage.setTitle("Appointments/Customer Page");
                stage.setScene(scene);
                stage.show();
                queryAppointments.insertSuccessful.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            insertError.setAlertType(Alert.AlertType.ERROR);
            insertError.setContentText(bundle.getString("Error"));
            insertError.showAndWait();
        }

    }

    public static int returnNewlyAddedAppointmentID() {
        return newlyAddedAppointmentID.getAppointment_id();
    }
}
