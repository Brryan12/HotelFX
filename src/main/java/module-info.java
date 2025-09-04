module com.example.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.hotelmanager to javafx.fxml;
    exports com.example.hotelmanager;
    exports com.example.hotelmanager.ui;
    opens com.example.hotelmanager.ui to javafx.fxml;
    exports com.example.hotelmanager.ui.controller;
    opens com.example.hotelmanager.ui.controller to javafx.fxml;
    exports com.example.hotelmanager.domain;
    opens com.example.hotelmanager.domain to javafx.fxml;
    exports com.example.hotelmanager.domain.entity;
    opens com.example.hotelmanager.domain.entity to javafx.fxml;
}