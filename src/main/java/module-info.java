module com.example.c195_javaappdev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c195_javaappdev to javafx.fxml;
    exports com.example.c195_javaappdev;
    exports com.example.c195_javaappdev.CONTROLLER;
    opens com.example.c195_javaappdev.CONTROLLER to javafx.fxml;
    opens com.example.c195_javaappdev.MODEL to javafx.fxml;
    exports com.example.c195_javaappdev.MODEL;
    exports com.example.c195_javaappdev.CONTROLLER.Appointment;
    opens com.example.c195_javaappdev.CONTROLLER.Appointment to javafx.fxml;
    exports com.example.c195_javaappdev.CONTROLLER.Customer;
    opens com.example.c195_javaappdev.CONTROLLER.Customer to javafx.fxml;
}