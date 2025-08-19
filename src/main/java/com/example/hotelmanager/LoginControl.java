package com.example.hotelmanager;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class LoginControl {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pwdPassword;

    @FXML
    protected void funcionLogin() {
        String user = txtUser.getText();
        String pwd = pwdPassword.getText();

        if (user.equals("admin") && pwd.equals("1234")) { //datos corretos
            //Caso exito
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource
                        ("com.example.hotelmanager/Login-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) txtUser.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Pantalla de Inicio");
            }
            catch(Exception error){
                //error sistema
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Sistema");
                alert.setHeaderText(error.getStackTrace()[0].getMethodName());
                alert.setContentText("Ocurrio un error inesperado del sistema. Intente de nuevo más tarde." +
                        "Como referencia, el detalla del error es: " + error.getMessage());
                alert.showAndWait(); //solo un boton y espera por input para salir
            }
        } else {
            //error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al Iniciar Sesion. Usuario o contraseña incorrectos");
            alert.showAndWait(); //solo un boton y espera por input para salir
        }
    }
}
