package com.example.hotelmanager;

import java.time.LocalDate;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import java.util.stream.Collectors;

public class InicioController implements Initializable{

    /*Cotroles tabla*/
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente,String> colNombreCli;
    @FXML private TableColumn<Cliente,String> colIdentificacionCli;
    @FXML private TableColumn<Cliente,String> colIdCli;

    /*Controles busqueda*/
    @FXML private TextField txtBuscarCli;
    @FXML private Button btnBuscarCli;
    @FXML private TextField txtIdentificacionCli;
    @FXML private TextField txtNombreCli;
    @FXML private TextField txtApellidoCli;
    @FXML private DatePicker dtpFechaNacimiento;

    /*Almacenamiento de datos*/
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(); //lista que se actualiza atm

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIdCli.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(String.valueOf(cd.getValue().getId()))
        );

        colIdentificacionCli.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(cd.getValue().getIdentificacion())
        );

        colNombreCli.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        cd.getValue().getNombre() + " " + cd.getValue().getPrimerApellido()
                )
        );

        listaClientes.addAll(
                new Cliente("Juan",2, LocalDate.of(1991,12,15),"Ricardo","1234"),
                new Cliente("Fatima",1, LocalDate.of(2000,8,25),"Santa","7894"),
                new Cliente("Carlos",3, LocalDate.of(2005,12,14),"Mata","7854")
        );

        tblClientes.setItems(listaClientes);
    }

    @FXML private void agregarCliente(){
        String identificacion = txtIdentificacionCli.getText().trim();
        String nombre = txtNombreCli.getText().trim();
        String primerApellido = txtApellidoCli.getText().trim();
        LocalDate fechaNacimiento = dtpFechaNacimiento.getValue();

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
        Cliente nuevo = new Cliente(nombre, listaClientes.isEmpty() ? 1 : listaClientes.getLast().getId() + 1, fechaNacimiento, primerApellido, identificacion);
        listaClientes.add(nuevo);
        mostrarAlerta("Cliente agregado", "El cliente ha sido agregado correctamente.");
        limpiarCampos(); //limpiar campos de texto
    }

    @FXML private void modificarCliente(){
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if(seleccionado == null){
            mostrarAlerta("Error de selección", "Debe seleccionar un cliente para modificar.");
            return; //salir del metodo
        }
        String identificacion = txtIdentificacionCli.getText().trim();
        String nombre = txtNombreCli.getText().trim();
        String primerApellido = txtApellidoCli.getText().trim();
        LocalDate fechaNacimiento = dtpFechaNacimiento.getValue();

        if(nombre.isEmpty() || primerApellido.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.");
            return; //salir del metodo
        }
        seleccionado.setIdentificacion(identificacion);
        seleccionado.setNombre(nombre);
        seleccionado.setPrimerApellido(primerApellido);
        seleccionado.setFechaNacimiento(fechaNacimiento);
        tblClientes.refresh(); //actualizar tabla
        mostrarAlerta("Cliente modificado", "El cliente ha sido modificado correctamente.");
        limpiarCampos(); //limpiar campos de texto

    }
    private void mostrarAlerta(String titulo, String mensaje) {;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void eliminarCliente() {
        try {
            Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Seleccione un cliente", "Por favor, seleccione un cliente de la tabla para eliminar.");
                return;
            }

            listaClientes.remove(seleccionado);
        }
        catch (Exception error)
        {
            mostrarAlerta("Error al eliminar el cliente", error.getMessage());
        }
    }

    @FXML
    private void buscarCliente() {
        try {
            String criterio = txtBuscarCli.getText().trim().toLowerCase();
            if(criterio.isEmpty())
            {
                tblClientes.setItems(listaClientes);
                return;
            }

            ObservableList<Cliente> filtrados =
                    FXCollections.observableArrayList(
                            listaClientes.stream()
                                    .filter(c -> c.getIdentificacion().toLowerCase().contains(criterio)
                                            || c.getNombre().toLowerCase().contains(criterio)
                                            || c.getPrimerApellido().toLowerCase().contains(criterio))
                                    .collect(Collectors.toList())
                    );

            tblClientes.setItems(filtrados);
        }
        catch (Exception error)
        {
            mostrarAlerta("Error al buscar el cliente", error.getMessage());
        }
    }
    private void limpiarCampos() {
        txtIdentificacionCli.clear();
        txtNombreCli.clear();
        txtApellidoCli.clear();
        dtpFechaNacimiento.setValue(null);
    }
}
