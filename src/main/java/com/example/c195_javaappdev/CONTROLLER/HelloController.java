package com.example.c195_javaappdev.CONTROLLER;

import com.example.c195_javaappdev.DAO.JDBC;
import com.example.c195_javaappdev.DAO.queryAppointments;
import com.example.c195_javaappdev.MODEL.Appointments;
import com.example.c195_javaappdev.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.c195_javaappdev.DAO.queryUserInfo.checkLoginInfo;

public class HelloController {
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


    public void initialize(){
        ResourceBundle b = ResourceBundle.getBundle("language", Locale.getDefault());
        signinbutton.setText(b.getString("hellobutton.text"));
        welcomeLabel.setText(b.getString("welcomeLabel.text"));
        usernameLabel.setText(b.getString("usernameLabel.text"));
        passwordLabel.setText(b.getString("passwordLabel.text"));
        locationLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    public void clicktologin (ActionEvent actionEvent) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());

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
                Parent appointments = FXMLLoader.load(Main.class.getResource("Views/AppointmentForms/Appointments and Customers.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(appointments, 1100, 700);
                stage.setTitle("Customer Appointments");
                stage.setScene(scene);
                stage.show();

                upcomingAppointment();
            } else {
                signinError.setAlertType(Alert.AlertType.ERROR);
                signinError.setContentText(bundle.getString("Error"));
                signinError.showAndWait();
            }

        }
    }

    private void upcomingAppointment() {
        boolean in15MinFlag = false;
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime in15Mins = currentTime.plusMinutes(15);
        LocalDateTime time1;

        ObservableList<Appointments> tempArray = FXCollections.observableArrayList();

        for (Appointments a: queryAppointments.getAppointmentList()) {
            time1 = a.getStartTime();
            if(time1.equals(in15Mins) || (time1.toLocalDate().equals(in15Mins.toLocalDate())
                    && time1.toLocalTime().isBefore(in15Mins.toLocalTime()))) {
                in15MinFlag = true;
            }
        }
        if (in15MinFlag == true) {
            Alert appointmentWarning = new Alert(Alert.AlertType.WARNING);
            appointmentWarning.setAlertType(Alert.AlertType.WARNING);
            appointmentWarning.setContentText("You have an upcoming appointment that starts within the next 15 minutes!");
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