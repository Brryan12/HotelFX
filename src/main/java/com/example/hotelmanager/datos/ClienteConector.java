package com.example.hotelmanager.datos;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="clientesData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClienteConector {
    @XmlElementWrapper(name="clientes")
    @XmlElement(name = "Cliente")
    private List<ClienteEntity> clientes = new ArrayList<>();

    public List<ClienteEntity> getClientes() {
        return clientes;
    }
    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }
}
