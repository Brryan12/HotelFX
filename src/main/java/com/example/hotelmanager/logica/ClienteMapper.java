package com.example.hotelmanager.logica;
import com.example.hotelmanager.datos.ClienteEntity;
import com.example.hotelmanager.model.Cliente;

public class ClienteMapper {
    public static ClienteEntity toXML(Cliente c) {
        if(c == null) {
            return null;
        }
        ClienteEntity cli = new ClienteEntity();
        cli.setId(c.getId());
        cli.setNombre(c.getNombre());
        cli.setPrimerApellido(c.getPrimerApellido());
        cli.setSegundoApellido(c.getSegundoApellido());
        cli.setIdentificacion(c.getIdentificacion());
        cli.setFechaNacimiento(c.getFechaNacimiento());
        return cli;
    }
    public static Cliente toModel(ClienteEntity c) {
        if(c == null) {
            return null;
        }
        Cliente cli = new Cliente(/*completar*/);
//        cli.setId(c.getId());
//        cli.setNombre(c.getNombre());
//        cli.setPrimerApellido(c.getPrimerApellido());
//        cli.setSegundoApellido(c.getSegundoApellido());
//        cli.setIdentificacion(c.getIdentificacion());
//        cli.setFechaNacimiento(c.getFechaNacimiento());
        return cli;
    }
}
