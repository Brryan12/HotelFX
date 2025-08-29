package com.example.hotelmanager;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Cliente nuevo = mostrarFormularioCliente(null, false);
        if(nuevo != null){
            nuevo.setId(listaClientes.getLast().getId()+1);
            //validar cedula
            for (Cliente cliente : listaClientes) {
                if (cliente.getIdentificacion().equals(nuevo.getIdentificacion())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de Validación");
                    alert.setHeaderText("Identificación Duplicada");
                    alert.setContentText("Ya existe un cliente con la identificación."+nuevo.getIdentificacion());
                    alert.showAndWait();
                    return; //salir del metodo
                }
            }
            listaClientes.add(nuevo);
            tblClientes.refresh();
            mostrarAlerta("Cliente agregado", "El cliente ha sido agregado correctamente.");
        }
    }

    private Cliente mostrarFormularioCliente(Cliente cliente, boolean editar){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/formulario-cliente-view.fxml"));
            Parent root = loader.load();
            FormularioCliController controller = loader.getController();
            controller.setCliente(cliente, editar);

            Stage stage = new Stage();
            stage.setTitle(editar ? "Modificar Cliente" : "Agregar Cliente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            return (Cliente) stage.getUserData();
        }
        catch(IOException error){
            //error sistema
            mostrarAlerta("Eror al abrir formulario", error.getMessage());
            return  null;
        }
    }

    @FXML private void modificarCliente(){

        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if(seleccionado == null){
            mostrarAlerta("Error de selección", "Debe seleccionar un cliente para modificar.");
            return; //salir del metodo
        }

        Cliente cliente = mostrarFormularioCliente(seleccionado, true);
        if(cliente == null){
            tblClientes.refresh();
        }
    }

    @FXML private void eliminarCliente(){
        try {
            Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Error de selección", "Debe seleccionar un cliente para eliminar.");
                return; //salir del metodo
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar Eliminación");
            confirm.setHeaderText(null);
            confirm.setContentText("¿Está seguro de que desea eliminar al cliente: " + seleccionado.getNombre()+
                    "Identificacion" + seleccionado.getIdentificacion()+ "?");

            if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                listaClientes.remove(seleccionado);
                tblClientes.refresh();
                mostrarAlerta("Cliente eliminado", "El cliente ha sido eliminado correctamente.");
            }
        }catch (Exception error){
            mostrarAlerta("Error al eliminar cliente", error.getMessage());
        }
    }

    @FXML private void buscarCliente(){
        try {
            String criterio = txtBuscarCli.getText().trim().toLowerCase();
            if (criterio.isEmpty()) {
                tblClientes.setItems(listaClientes);
                return;
            } else {
                ObservableList<Cliente> listaFiltrada = FXCollections.observableArrayList(
                        (Cliente) listaClientes.stream()
                                .filter(cliente -> cliente.getIdentificacion().toLowerCase().contains(criterio)
                                        || cliente.getNombre().toLowerCase().contains(criterio)
                                || cliente.getPrimerApellido().toLowerCase().contains(criterio)));
                tblClientes.setItems(listaFiltrada);
            }
            tblClientes.refresh();
        }catch (Exception error){
            mostrarAlerta("Error en la búsqueda", error.getMessage());
        }
    }
    private void mostrarAlerta(String titulo, String mensaje) {;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
