module com.example.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires jakarta.xml.bind;

    // Paquete raíz
    opens com.example.hotelmanager to javafx.fxml;
    exports com.example.hotelmanager;

    // Aplicación
    exports com.example.hotelmanager.model;
    opens com.example.hotelmanager.model to javafx.fxml;

    opens com.example.hotelmanager.datos to jakarta.xml.bind;
    exports com.example.hotelmanager.datos;
    exports com.example.hotelmanager.controller;
    opens com.example.hotelmanager.controller to javafx.fxml;
}