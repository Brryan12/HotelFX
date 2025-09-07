module com.example.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.hotelmanager to javafx.fxml;
    exports com.example.hotelmanager;
    exports com.example.hotelmanager.model;
    opens com.example.hotelmanager.model to javafx.fxml;
    exports com.example.hotelmanager.controller;
    opens com.example.hotelmanager.controller to javafx.fxml;
}