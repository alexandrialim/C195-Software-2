module com.example.c195_javaappdev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c195_javaappdev to javafx.fxml;
    exports com.example.c195_javaappdev;
}