package com.example.hotelmanager.logica;
import com.example.hotelmanager.datos.ClienteDatos;
import com.example.hotelmanager.datos.ClienteConector;
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
        //validar
        /*Que no se repita, logintud de id, edad, etc
        la validacion va en la logica*/

        validarNuevo(nuevo);
        ClienteConector data = store.load();

        // Unicidad por identificación (si aplica)
        if (nuevo.getIdentificacion() != null && !nuevo.getIdentificacion().isBlank()) {
            boolean existsIdent = data.getClientes().stream()
                    .anyMatch(x -> nuevo.getIdentificacion().equalsIgnoreCase(x.getIdentificacion()));
            if (existsIdent) throw new IllegalArgumentException("Ya existe un cliente con esa identificación.");
        }

        // Generar ID si viene en 0 o negativo
        if (nuevo.getId() <= 0) {
            nuevo.setId(generarSiguienteId(data));
        } else {
            boolean idTaken = data.getClientes().stream().anyMatch(x -> x.getId() == nuevo.getId());
            if (idTaken) throw new IllegalArgumentException("Ya existe un cliente con id: " + nuevo.getId());
        }
        ClienteEntity clienteDatos = ClienteMapper.toXML(nuevo);
        data.getClientes().add(clienteDatos);
        store.save(data);
        return ClienteMapper.toModel(clienteDatos);
    }

    public Cliente update(Cliente actualizado) {
        ClienteConector data = store.load();
        //validaciones
        //que no se eliminen datos como la fecha de nacimiento etc
        for(int i = 0; i < data.getClientes().size(); i++) {
            ClienteEntity actual = data.getClientes().get(i);
            if (actual.getIdentificacion() == actualizado.getIdentificacion()) {
                //se encontro y se hacen los cambios
                data.getClientes().set(i,ClienteMapper.toXML(actualizado));
                store.save(data);
                break;
            }
        }
        return actualizado;
    }

    public boolean deleteById(int id) {
        //Todo este codigo debe tener try-catch
        if (id <= 0) return false;
        ClienteConector data = store.load();
        /* validar que el id exista(NVM), que no sea 0, que no sea negativo, etc*/
        boolean eliminado = data.getClientes().removeIf(clienteEntity -> clienteEntity.getId() == id);
        if (eliminado) {
            store.save(data);
        }
        return eliminado;
    }

    private int generarSiguienteId(ClienteConector data) {
        List<ClienteEntity> farmaceutas = data.getClientes();
        return farmaceutas.isEmpty() ? 1 :
                farmaceutas.stream()
                        .mapToInt(ClienteEntity::getId)
                        .max()
                        .orElse(0) + 1;
    }

    private void validarCampos(Cliente c) {
        if (c.getNombre() == null || c.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio.");
        if (c.getPrimerApellido() == null || c.getPrimerApellido().isBlank())
            throw new IllegalArgumentException("El primer apellido es obligatorio.");
        if (c.getFechaNacimiento() != null && c.getFechaNacimiento().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
    }

    private void validarNuevo(Cliente c) {
        if (c == null) throw new IllegalArgumentException("Cliente nulo.");
        validarCampos(c);
    }
}
