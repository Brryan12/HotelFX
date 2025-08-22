package com.example.hotelmanager;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable{

    /*Cotroles tabla*/
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente,String> colNombreCli;
    @FXML private TableColumn<Cliente,String> colIdentificacionCli;
    @FXML private TableColumn<Cliente,String> colIdCli;

    /*Controles busqueda*/
    @FXML private TextField txtBuscarCli;

    /*Almacenamiento de datos*/
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(); //lista que se actualiza atm
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //asociar columnas ->propiedad Cliente
        colIdCli.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreCli.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdentificacionCli.setCellValueFactory(new PropertyValueFactory<>("identificacion"));

        listaClientes.addAll(
            new Cliente("Juan",2, LocalDate.of(1991,12,15),"Ricardo","1234"),
            new Cliente("Fatima",1, LocalDate.of(2000,8,25),"Santa","7894"),
            new Cliente("Carlos",3, LocalDate.of(2005,12,14),"Mata","7854")
        );
        tblClientes.setItems(listaClientes);
    }
    @FXML private void agregarCliente(){
        String identificacion = null; //txtIdentificacionCliente.getText().trim();
        String nombre = null; //txtNombreCliente.getText().trim();
        String primerApellido = null; //txtPrimerApellidoCliente.getText().trim();
        LocalDate fechaNacimiento = null; //dtpFechaNacimientoCliente.getValue();

        if(identificacion.isEmpty() || nombre.isEmpty() || primerApellido.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.");
            return; //salir del metodo
        }

        //validar cedula
        for(Cliente cliente : listaClientes){
            if(cliente.getIdentificacion().equals(identificacion)){
                mostrarAlerta("Error de validación", "Ya existe un cliente con esa identificación.");
                return; //salir del metodo
            }
        }

        //valir fecha de nacimiento con un try catch
//        listaClientes.add(new Cliente(nombre, listaClientes.getLast().getId() + 1, fechaNacimiento, primerApellido, identificacion));
//        limpiarCampos(); //limpiar campos de texto
//        tblClientes.refresh(); //actualizar tabla
//        mostrarAlerta("Cliente agregado", "El cliente ha sido agregado correctamente.");
    }

    @FXML private void modificarCliente(){
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if(seleccionado == null){
            mostrarAlerta("Error de selección", "Debe seleccionar un cliente para modificar.");
            return; //salir del metodo
        }
        String nombre = null; //txtNombreCliente.getText().trim();
        String primerApellido = null; //txtPrimerApellidoCliente.getText().trim();
        LocalDate fechaNacimiento = null; //dtpFechaNacimientoCliente.getValue();

        if(nombre.isEmpty() || primerApellido.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.");
            return; //salir del metodo
        }

        seleccionado.setNombre(nombre);
        seleccionado.setPrimerApellido(primerApellido);
        seleccionado.setFechaNacimiento(fechaNacimiento);
        tblClientes.refresh(); //actualizar tabla
        mostrarAlerta("Cliente modificado", "El cliente ha sido modificado correctamente.");

    }
    private void mostrarAlerta(String titulo, String mensaje) {;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        //txtIdentificacionCliente.clear();
        //txtNombreCliente.clear();
        //txtPrimerApellidoCliente.clear();
        //dtpFechaNacimientoCliente.setValue(null);
    }
}
