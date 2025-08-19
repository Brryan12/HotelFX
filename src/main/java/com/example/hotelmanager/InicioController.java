package com.example.hotelmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;

public class InicioController {

    @FXML private TextField txtBuscarCli;
    @FXML private Button btnBuscarCli;
    @FXML private Button btnModificarCli;
    @FXML private Button btnEliminarCli;
    @FXML private Button btnAgregarCli;

    @FXML private String BuscarCli(){
    /*
    if(nameTabla == txtBuscarCli.getText())
        muestra solamente el cliente
    else
         tira error que le usuario que busco no se encuentra en la tabla
     */
        return txtBuscarCli.getText();
    }
    @FXML private void btnModificarCliOnAction(ActionEvent event) {}
    @FXML private void btnEliminarCliOnAction(ActionEvent event) {}
    @FXML private void btnAgregarCliOnAction(ActionEvent event) {}




}
