package CONTROLLER;

import DAO.JDBC;
import DAO.queryUserInfo;
import MODEL.Users;
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
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.queryUserInfo.checkLoginInfo;

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
                Parent appointments = FXMLLoader.load(Main.class.getResource("Views/Appointments.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //Create Scene
                Scene scene = new Scene(appointments, 1900, 400);
                stage.setTitle("Customer Appointments");
                stage.setScene(scene);
                stage.show();
            } else {
                signinError.setAlertType(Alert.AlertType.ERROR);
                signinError.setContentText(bundle.getString("Error"));
                signinError.showAndWait();
            }

        }
    }
}