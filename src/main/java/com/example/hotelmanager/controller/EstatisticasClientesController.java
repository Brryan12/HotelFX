package com.example.hotelmanager.controller;
import com.example.hotelmanager.logica.ClienteLogica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import com.example.hotelmanager.logica.EstadisticasClienteLogica;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.LinkedHashMap;


public class EstatisticasClientesController implements Initializable {

    private static final String RUTA__CLIENTES = java.nio.file.Paths.
            get(System.getProperty("user.dir"), "bd", "clientes.xml").toString();
    private final EstadisticasClienteLogica EstadisticasclienteLogica = new EstadisticasClienteLogica(RUTA__CLIENTES);

    @FXML private Label lblTotalClientes;
    @FXML private Label lblEdadPromedio;

    @FXML private BarChart<String, Number> chartRangosEdad;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML private PieChart chartRangosEdadPie;

    private EstadisticasClienteLogica service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.service = new EstadisticasClienteLogica(RUTA__CLIENTES);
        cargarGraficos();
    }

    public void cargarGraficos(){
        int total= service.totalClientes();
        lblTotalClientes.setText(Integer.toString(total));

        lblEdadPromedio.setText(service.edadPromedio().toString());
        //Rangos -> Graficos
        LinkedHashMap<String, Long> rangos = service.clientesPorRangoEdad();
        chartRangosEdad.getData().clear();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Clientes por rango de edad");

        for (Map.Entry<String, Long> e : rangos.entrySet()) {
            series1.getData().add(new XYChart.Data(e.getKey(), e.getValue()));
        }

        chartRangosEdad.getData().add(series1);

        //rangos -> PieChart
        chartRangosEdadPie.getData().clear();
        for (Map.Entry<String, Long> e : rangos.entrySet()) {
            chartRangosEdadPie.getData().add(new PieChart.Data(e.getKey(), e.getValue()));
        }
    }
}
