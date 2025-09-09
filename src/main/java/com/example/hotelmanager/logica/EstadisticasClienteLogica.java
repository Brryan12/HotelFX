package com.example.hotelmanager.logica;
import com.example.hotelmanager.model.Cliente;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class EstadisticasClienteLogica {
    private final ClienteLogica clienteLogica;

    public EstadisticasClienteLogica(String rutaXML) {
        this.clienteLogica = new ClienteLogica(rutaXML);
    }

    public List<Cliente> cargarClientes() {
        return clienteLogica.findAll();
    }

    public int totalClientes() {
        return clienteLogica.findAll().size();
    }

    //por rango de edad
    public LinkedHashMap<String, Long> clientesPorRangoEdad(){
        LinkedHashMap<String, Long> resultado = new LinkedHashMap<>();
        List<String> rangos = Arrays.asList("18-30", "30-40", "40-50", "50-60","60 o más");
        Map<String, Long> conteos = cargarClientes().stream().collect(
                Collectors.groupingBy(c-> rangoEdad(c.getFechaNacimiento())/*edad*/, Collectors.counting()
        ));
        for( String rango: rangos){
            resultado.put(rango, conteos.get(rango));
        }
        return resultado;
    }

    public OptionalDouble edadPromedio(){
        LocalDate hoy = LocalDate.now();
        return cargarClientes().stream().map(Cliente::getFechaNacimiento).filter(Objects::nonNull).
                mapToInt(f-> Period.between(f,hoy).getYears()).average();
    }

    private static String rangoEdad(LocalDate fechaNac){
        int edad = Period.between(fechaNac, LocalDate.now()).getYears();
        if(edad <= 30){
            return "18-30";
        }
        if(edad <= 40){
            return "30-40";
        }
        if(edad <= 50){
            return "40-50";
        }
        if(edad <= 60){
            return "50-60";
        }
        else{
            return "60 o más";
        }
    }
}
