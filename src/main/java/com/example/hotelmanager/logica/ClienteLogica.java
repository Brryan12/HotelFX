package com.example.hotelmanager.logica;
import com.example.hotelmanager.datos.ClienteDatos;
import com.example.hotelmanager.datos.ClienteConector;
import com.example.hotelmanager.datos.ClienteDatos;
import com.example.hotelmanager.datos.ClienteEntity;
import com.example.hotelmanager.model.Cliente;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ClienteLogica {
    //Referenciar a la fuente de datos
    private final ClienteDatos store;

    public ClienteLogica(String rutaArchivo) {
        this.store = new ClienteDatos(rutaArchivo);
    }

    //Lectura
    //Entity solo en la capa de datos
    public List<Cliente> findAll() {
        ClienteConector data = store.load(); //XML
        return data.getClientes().stream().map(ClienteMapper :: toModel) //se copia el XML al modelo
                .collect(Collectors.toList()); //y se hace una lista
    }

    public List<Cliente> findByParameters(String texto) {
        ClienteConector data = store.load();
        return data.getClientes().stream().filter(clienteEntity -> clienteEntity.getIdentificacion() == texto
                || clienteEntity.getNombre() == texto || clienteEntity.getPrimerApellido() ==texto
                || clienteEntity.getSegundoApellido() == texto ).map(ClienteMapper::toModel).
                collect(Collectors.toList());
    }

    //escritura
    public Cliente create(Cliente nuevo) {
        //Todo este codigo debe tener try-catch
        ClienteConector data = store.load();
        //validar
        /*Que no se repita, logintud de id, edad, etc
        la validacion va en la logica*/
        ClienteEntity clienteDatos = ClienteMapper.toXML(nuevo);
        data.getClientes().add(clienteDatos);
        store.save(data);
        return ClienteMapper.toModel(clienteDatos);
    }

    public Cliente update(Cliente actualizado) {
        ClienteConector data = store.load();
        //validaciones
        //que no se eliminen datos como la fecha de nacimiento etc
        for (ClienteEntity actual : data.getClientes()) { //int i =0; i<data.getClientes().size(); i++
            if (actual.getIdentificacion() == actualizado.getIdentificacion()) {
                //se encontro y se hacen los cambios
                //data.getClientes().set(i,ClienteMapper.toXML(actualizado));
                store.save(data);
                break;
            }
        }
        return actualizado;
    }

    public boolean deleteById(int id) {
        //Todo este codigo debe tener try-catch
        ClienteConector data = store.load();
        /* validar que el id exista(NVM), que no sea 0, que no sea negativo, etc*/
        boolean eliminado = data.getClientes().removeIf(clienteEntity -> clienteEntity.getId() == id);
        if (eliminado) {
            store.save(data);
        }
        return eliminado;
    }
}
