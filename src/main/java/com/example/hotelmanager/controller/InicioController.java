package com.example.hotelmanager.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.example.hotelmanager.model.Cliente;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import com.example.hotelmanager.logica.ClienteLogica;

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
    private static final String RUTA__CLIENTES = java.nio.file.Paths.
            get(System.getProperty("user.dir"), "bd", "clientes.xml").toString();
    private final ClienteLogica clienteLogica = new ClienteLogica(RUTA__CLIENTES);

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

        listaClientes.addAll(clienteLogica.findAll());

        tblClientes.setItems(listaClientes);
    }

    @FXML
    private void agregarCliente() {
        Cliente nuevo = mostrarFormulario(null, false);
        if(nuevo != null) {
            int nextId = listaClientes.isEmpty() ? 1 : listaClientes.getLast().getId() + 1;
            nuevo.setId(nextId);

            // Verificamos que el cliente no se repita
            for (Cliente cliente : listaClientes) {
                if (cliente.getIdentificacion().equals(nuevo.getIdentificacion())) {
                    // Error de identificación repetida
                    mostrarAlerta("Cliente ya existe", "El cliente con la identificación " + nuevo.getIdentificacion() + " ya existe.");
                    return;
                }
            }

            /*
            Cliente resultado = clienteLogica.create(nuevo); //primero a la bd
            listaClientes.add(resultado); //luego se carga a la memoria
            */

            clienteLogica.create(nuevo); //primero a la bd
            listaClientes.add(nuevo); //luego se carga a la memoria
        }
    }

    private Cliente mostrarFormulario(Cliente cliente, boolean editar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanager/formulario-cliente-view.fxml"));
            Parent root = loader.load();

            //LLamar a la clase de FormularioClienteController
            FormularioCliController controller = loader.getController();
            //Vamos a setear la información del cliente que vamos a agregar
            //Pero para eso necesitamos el metodo respectivo en FormularioClienteController
            controller.setCliente(cliente, editar);

            Stage stage = new Stage();
            stage.setTitle(editar ? "Modificar Cliente" : "Agregar Cliente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            return (Cliente) stage.getUserData();
        }
        catch (IOException error)
        {
            mostrarAlerta("Error al abrir el formulario", error.getMessage());
            return null;
        }
    }

    @FXML
    private void modificarCliente() {
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un cliente", "Por favor, seleccione un cliente de la tabla para modificar.");
            return;
        }

        Cliente modificado = mostrarFormulario(seleccionado, true);
        if (modificado != null) {
            clienteLogica.update(modificado);
            tblClientes.refresh();
        }
    }

    @FXML
    private void eliminarCliente() {
        try {
            Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Seleccione un cliente", "Por favor, seleccione un cliente de la tabla para eliminar.");
                return;
            }
            clienteLogica.deleteById(seleccionado.getId());
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
            /* si se actualiza la lista mientras se busca no sale
            ObservableList<Cliente> filtrados =
                    FXCollections.observableArrayList(
                            listaClientes.stream()
                                    .filter(c -> c.getIdentificacion().toLowerCase().contains(criterio)
                                            || c.getNombre().toLowerCase().contains(criterio)
                                            || c.getPrimerApellido().toLowerCase().contains(criterio))
                                    .collect(Collectors.toList())
                    );
            */
            listaClientes.addAll(clienteLogica.findByParameters(criterio));

            tblClientes.setItems(listaClientes);
        }
        catch (Exception error)
        {
            mostrarAlerta("Error al buscar el cliente", error.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
