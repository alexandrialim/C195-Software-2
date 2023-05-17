module com.example.c195_javaappdev {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.c195_javaappdev to javafx.fxml;
    exports com.example.c195_javaappdev;
}