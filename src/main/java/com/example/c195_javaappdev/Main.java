package com.example.c195_javaappdev;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    //Locale.setDefault(new Locale("fr")); LANGUAGE/Resource Bundle 'english'/Nat"

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 240);
        stage.setTitle("main login screen");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        JDBC.openConnection();
        JDBC.closeConnection();
    }
}