package com.example.hotelmanager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import com.example.hotelmanager.Cliente;
import java.time.LocalDate;
public class FormularioCliController {

    @FXML private TextField txtNombreCli;
    @FXML private TextField txtApellidoCli;
    @FXML private TextField txtIdentificacionCli;
    @FXML private DatePicker dtpFechaNacCli;

    private Cliente cliente;
    private boolean editar = false;

    public void setCliente(Cliente cliente, boolean editar){
        this.cliente = cliente;
        this.editar = editar;
        //editar true, cliente existe, modificar info
        if(editar && cliente != null){
            txtNombreCli.setText(cliente.getNombre());
            txtApellidoCli.setText(cliente.getPrimerApellido());
            txtIdentificacionCli.setText(cliente.getIdentificacion());
            dtpFechaNacCli.setValue(cliente.getFechaNacimiento());
        }
    }

    @FXML private void guardarCliente(){
        try {
            String nombre = txtNombreCli.getText();
            String apellido = txtApellidoCli.getText();
            String identificacion = txtIdentificacionCli.getText();
            LocalDate fechaNac = dtpFechaNacCli.getValue();

            if (nombre.isEmpty() || apellido.isEmpty() || identificacion.isEmpty() || fechaNac == null) {
                //mostrar error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Validación");
                alert.setHeaderText("Dates Incompletos");
                alert.setContentText("Por favor, complete todos los campos antes de guardar.");
                alert.showAndWait();
                return;
            }

            if (editar && cliente != null) {
                //modificar cliente existente
                cliente.setNombre(nombre);
                cliente.setPrimerApellido(apellido);
                cliente.setIdentificacion(identificacion);
                cliente.setFechaNacimiento(fechaNac);
            } else {
                //crear nuevo cliente
                cliente = new Cliente(nombre, 0, fechaNac, apellido, identificacion);
            }
            //cerrar ventana y regresar a la principal

            Stage stage = (Stage) txtNombreCli.getScene().getWindow();
            stage.setUserData(cliente);
            stage.close();
        }catch(Exception error){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Sistema");
            alert.setHeaderText(error.getStackTrace()[0].getMethodName());
            alert.setContentText("Error al guardar los datos" + error.getMessage());
            alert.showAndWait(); //solo un boton y espera por input para salir
        }
    }

    private void cancelarCliente(){
        try {
            Stage stage = (Stage) txtNombreCli.getScene().getWindow();
            stage.setUserData(null);
            stage.close();
        }catch (Exception error){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Sistema");
            alert.setHeaderText(error.getStackTrace()[0].getMethodName());
            alert.setContentText("Error al guardar los datos" + error.getMessage());
            alert.showAndWait(); //solo un boton y espera por input para salir
        }
    }
    private void LimpiarCampos() {
        txtNombreCli.clear();
        txtApellidoCli.clear();
        txtIdentificacionCli.clear();
        dtpFechaNacCli.setValue(null);
    }
}
