package com.example.c195_javaappdev;
import com.example.c195_javaappdev.DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**This class launches the scheduling application.*/
public class Main extends Application {
    /**This loads the login screen.*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 240);
        stage.setTitle("main login screen");
        stage.setScene(scene);
        stage.show();
    }
    /**This is the main method. It gets called when the program is ran.*/
    public static void main(String[] args) {
        launch();
        JDBC.openConnection();
        JDBC.closeConnection();
    }
}