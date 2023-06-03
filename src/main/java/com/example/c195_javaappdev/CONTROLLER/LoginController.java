package com.example.c195_javaappdev.CONTROLLER;

import com.example.c195_javaappdev.DAO.JDBC;
import com.example.c195_javaappdev.DAO.QueryAppointments;
import com.example.c195_javaappdev.MODEL.Appointments;
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

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.c195_javaappdev.DAO.QueryUserInfo.checkLoginInfo;

/**
 * This class contains methods for logging into the system.
 */
public class LoginController {
    @FXML
    public Label passwordLabel;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label locationLabel;
    public TextField passwordBox;
    public TextField usernameBox;
    @FXML
    private Button signinbutton;

    /**
     * This method initializes the controller.
     * A resource bundle is used to translate text from French to English depending
     * on the users computer language settings.
     */
    public void initialize(){
        ResourceBundle b = ResourceBundle.getBundle("language", Locale.getDefault());
        signinbutton.setText(b.getString("hellobutton.text"));
        welcomeLabel.setText(b.getString("welcomeLabel.text"));
        usernameLabel.setText(b.getString("usernameLabel.text"));
        passwordLabel.setText(b.getString("passwordLabel.text"));
        locationLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    /**
     * This method checks a users login information to see if it exists in the database.
     * If the user exists in the database, it logs the user in.
     * It also logs the user login attempts and whether it was successful or a failed attempt.
     * @param actionEvent When clicked it runs user verification and logs the login attempt in login_activity.txt.
     * @throws IOException throws an error if any out of scope errors come up.
     */
    public void clicktologin (ActionEvent actionEvent) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());
        FileWriter newLoginLog = new FileWriter("login_activity.txt", true);
        String unInput = usernameBox.getText();
        String pwInput = passwordBox.getText();
        Alert signinError = new Alert(Alert.AlertType.ERROR);
        if (unInput.isEmpty() && pwInput.isEmpty()) {
            signinError.setAlertType(Alert.AlertType.ERROR);
            signinError.setContentText(bundle.getString("err1"));
            signinError.showAndWait();
        } else if (unInput.isEmpty()) {
            signinError.setContentText(bundle.getString("err2"));
            signinError.showAndWait();
        } else if (pwInput.isEmpty()) {
            signinError.setAlertType(Alert.AlertType.ERROR);
            signinError.setContentText(bundle.getString("err3"));
            signinError.showAndWait();
        } else if (!unInput.isEmpty() && !pwInput.isEmpty()) {
            JDBC.openConnection();
            String chkuser = checkLoginInfo(unInput, pwInput);
            if (chkuser.equals("test test") || chkuser.equals("admin admin")) {
                newLoginLog.write(Timestamp.valueOf(LocalDateTime.now()) + " " + usernameBox + " successful\n");

                Parent appointments = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(appointments, 1100, 700);
                stage.setTitle("Customer Appointments");
                stage.setScene(scene);
                stage.show();
                newLoginLog.close();
                upcomingAppointment();
            } else if(!chkuser.equals("test test") || !chkuser.equals("admin admin")){
                signinError.setAlertType(Alert.AlertType.ERROR);
                signinError.setContentText(bundle.getString("err4"));
                signinError.showAndWait();
                newLoginLog.write(Timestamp.valueOf(LocalDateTime.now()) + " " + usernameBox + " Failed\n");
                newLoginLog.close();
            }

        }
    }

    /**
     * This method checks if a user has an upcoming appointment in the next 15 mins.
     * If there is an appointment in 15 minutes from the login time, a warning will pop up to notify the user.
     * If there is no appointment in the next 15 minutes from login time, a confirmation will pop up to notify the user.
     */
    private void upcomingAppointment() {
        boolean in15MinFlag = false;
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime in15Mins = currentTime.plusMinutes(15);
        LocalDateTime previous1Min = currentTime.plusMinutes(1);
        ZonedDateTime time1;
        Appointments b = null;

        for (Appointments a : QueryAppointments.getAppointmentList()) {
            time1 = a.getStartTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(String.valueOf(ZoneId.systemDefault())));
            if((time1.isAfter(previous1Min.atZone(ZoneId.systemDefault())) || time1.isEqual(in15Mins.atZone(ZoneId.systemDefault())))
                    && (time1.isBefore(in15Mins.atZone(ZoneId.systemDefault()))
                    || (time1.isEqual(in15Mins.atZone(ZoneId.systemDefault()))))){
                in15MinFlag = true;
                b = a;
            }
        }
        if (in15MinFlag == true) {
            ZonedDateTime localtime = b.getStartTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(String.valueOf(ZoneId.systemDefault())));
            Alert appointmentWarning = new Alert(Alert.AlertType.WARNING);
            appointmentWarning.setAlertType(Alert.AlertType.WARNING);
            // appointment ID, date, and time.
            appointmentWarning.setContentText("You have an upcoming appointment " + b.getAppointment_id() + " that starts within the next 15 minutes at " + localtime);
            appointmentWarning.showAndWait();
        } else{
            Alert noAppointmentSoon = new Alert(Alert.AlertType.CONFIRMATION);
            noAppointmentSoon.setAlertType(Alert.AlertType.CONFIRMATION);
            noAppointmentSoon.setContentText("You DO NOT have an upcoming appointment that starts " +
                    "within the next 15 minutes. :)");
            noAppointmentSoon.showAndWait();
        }
    }

}