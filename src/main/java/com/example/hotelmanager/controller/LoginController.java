package com.example.hotelmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;


public class LoginController {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSalir;
    @FXML
    private ProgressIndicator progress; //poner id en properties and Code

    @FXML
    private void funcionLogin() {
        String usuario = txtUser.getText();
        String contrasena = pwdPassword.getText();

        // Mostrar progress y ocultar botón
        btnLogin.setVisible(false);
        btnSalir.setVisible(false);
        progress.setVisible(true);

        // Simular un pequeño retardo como si validara contra un servidor
        new Thread(() -> {
            try {
                Thread.sleep(1500); // Simula procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                // Ocultar el progress y restaurar botón
                progress.setVisible(false);
                btnLogin.setVisible(true);
                btnSalir.setVisible(true);
                if (usuario.equals("admin") && contrasena.equals("1234")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanager/inicio-view.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) txtUser.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Pantalla de Inicio");
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de sistema");
                        alert.setHeaderText(e.getStackTrace()[0].getClassName());
                        alert.setContentText("No fue posible iniciar sesión, debido a un error de sistema: " + e.getMessage());
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de autenticación");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario o contraseña incorrectos.");
                    alert.showAndWait();
                }
            });
        }).start();
    }
}