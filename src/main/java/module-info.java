module com.example.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelmanager to javafx.fxml;
    exports com.example.hotelmanager;
}