module com.example.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.hotelmanager to javafx.fxml;
    exports com.example.hotelmanager;
}