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
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * This class holds methods to add appointment information.
 */
public class AddAppointment_Controller {
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

    /**
     * This method initializes the add appointment form.
     * It adds a range of times for the user to pick for an appointment time, user IDs to pick from,
     * customer IDs to pick from and contact names to pick from.
     * @throws Exception
     */
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
        appUserID.setItems(QueryUserInfo.getUserList());
        appCustID.setItems(QueryCustomerData.getCustomerList());
        appContact.setItems(QueryContacts.getContactList());

        LocalTime twelvePM_UTC_1 = LocalTime.of(12,0,0);

    }

    /**
     * This method allows a user to exit the add appointment form and go back to the main page without saving any inputs.
     * @param actionEvent When clicked, the user will be routed back to the main page.
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
     * This method is used to save all appointment details when modifying the appointment.
     * @param actionEvent When clicked, the method will grab all data from the form
     *                    and validate it before running an update query.
     * @throws IOException
     * @throws SQLException
     */
    public void clicktoSave(ActionEvent actionEvent) throws IOException, SQLException {
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

        boolean chk1 = false;
        boolean chk2 = false;
        boolean chk3 = false;
        boolean chk4 = false;
        boolean chk5 = false;
        boolean chk6 = false;
        boolean chk7 = false;

        if (!appTitle.getText().isEmpty() || !appDesc.getText().isEmpty() || !appContact.getSelectionModel().isEmpty()
                || !appType.getText().isEmpty() || !appLocation.getText().isEmpty() || headUTC != null
                || endUTC != null || !appCustID.getSelectionModel().isEmpty()
                || !appUserID.getSelectionModel().isEmpty()){
            if(QueryAppointments.appointmentConflict(appStartD.getValue(), appStartT.getValue(), appEndT.getValue()
                    , Integer.parseInt(appCustID.getValue().toString())) == true){
                chk2 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                    insertError.setContentText("Error: The appointment time that you have " +
                            "selected overlaps with another appointment.");
                    insertError.showAndWait();
            }
            if(headUTC.isAfter(endUTC)){
                chk3 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is AFTER end time.");
                insertError.showAndWait();
            }
            if (headUTC.equals(endUTC)) {
                chk4 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is the SAME as end time.");
                insertError.showAndWait();
            }
            if (headUTC.isBefore(twelvePM_UTC)) {
                chk5 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: Start time is before standard business hours start");
                insertError.showAndWait();
            }
            if (endUTC.isAfter(twoAM_UTC) && endUTC.isBefore(twelvePM_UTC)) {
                chk6 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: End time is outside standard business hours start");
                insertError.showAndWait();
            }
            if (dayofWeek.getDayOfWeek() == DayOfWeek.SATURDAY || dayofWeek.getDayOfWeek() == DayOfWeek.SUNDAY){
                chk7 = true;
                insertError.setAlertType(Alert.AlertType.ERROR);
                insertError.setContentText("Error: You have selected a weekend. We are only open from Monday - Friday");
                insertError.showAndWait();
            }
            else if(!chk1 && !chk2 && !chk3 && !chk4 && !chk5 && !chk6){
                QueryAppointments.addAppointment(appTitle.getText(), appDesc.getText(), appContact.getValue().getContact_id()
                        , appType.getText(), appLocation.getText(), headUTC.toLocalDateTime(), endUTC.toLocalDateTime()
                        , appCustID.getValue().toString(), appUserID.getValue().toString(), createDateValue, lastUpdateValue);

                Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(fxmlLoader, 1100, 700);
                stage.setTitle("Appointments/Customer Page");
                stage.setScene(scene);
                stage.show();
                QueryAppointments.insertSuccessful.showAndWait();
                }

        }else{
            chk1 = true;
            insertError.setAlertType(Alert.AlertType.ERROR);
            insertError.setContentText(bundle.getString("Error"));
            insertError.showAndWait();
        }

    }

}
