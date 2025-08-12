package com.example.hotelmanager;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginControl {
    @FXML
    private TextField txtUser;
    private PasswordField pwdPassword;

    @FXML
    protected void funcionLogin() {
        String user = "admin";
        String pwd = "1234";

        if (true) { //datos corretos
            //login
            //pantalla principal
        } else {
            //error
        }
    }
}
