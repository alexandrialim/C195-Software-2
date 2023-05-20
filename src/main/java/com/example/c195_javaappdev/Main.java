package com.example.c195_javaappdev;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    //Locale.setDefault(new Locale("fr")); LANGUAGE/Resource Bundle 'english'/Nat"

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 240);
        stage.setTitle("main login screen");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
   /*   Locale frenchLocale = new Locale("fr", "FR");
        Locale englishLocale = new Locale("en", "US");
        // Load the resource bundle for French
        ResourceBundle bundle = ResourceBundle.getBundle("language", Locale.getDefault());

        // Get the translated value for "hello" from the resource bundle
        String translatedHello = bundle.getString("hello");

        System.out.println(translatedHello);*/
        launch();
        JDBC.openConnection();
        JDBC.closeConnection();



    }
}