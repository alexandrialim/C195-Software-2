package CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    public Label passwordLabel;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label welcomeLabel;
    @FXML
    private Button hellobutton;


    public void initialize(){
        ResourceBundle b = ResourceBundle.getBundle("language", Locale.getDefault());
        hellobutton.setText(b.getString("hellobutton.text"));
        welcomeLabel.setText(b.getString("welcomeLabel.text"));
        usernameLabel.setText(b.getString("usernameLabel.text"));
        passwordLabel.setText(b.getString("passwordLabel.text"));
    }
}