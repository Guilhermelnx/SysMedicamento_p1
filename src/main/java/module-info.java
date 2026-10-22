module com.example.medicamentos_p1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.medicamentos_p1 to javafx.fxml;
    exports com.example.medicamentos_p1;
    exports models;
    opens models to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}