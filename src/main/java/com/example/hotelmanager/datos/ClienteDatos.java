package com.example.hotelmanager.datos;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


//gestiona la base de datos para el cliente
public class ClienteDatos {
    private final Path xmlPath;
    private final JAXBContext ctx;
    private ClienteConector cache;

    public ClienteDatos(String filePath) {
        try {
            this.xmlPath = Paths.get(Objects.requireNonNull(filePath));
            this.ctx = JAXBContext.newInstance(ClienteEntity.class, ClienteEntity.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public synchronized ClienteConector load() {
        try{
            if(cache!=null){
                return cache;
            }
            if(!Files.exists(xmlPath)){
                cache = new ClienteConector();
                save(cache); //crea archivo vacio
                return cache;
            }
            //xml to java
            Unmarshaller um = ctx.createUnmarshaller();
            //gestiona la info del xml
            cache = (ClienteConector) um.unmarshal(xmlPath.toFile());

            //instancia de clientes en el xml
            //primera ejecucion o cuando se limpia la base de datos
            if(cache.getClientes()==null){
                cache.setClientes(new java.util.ArrayList<>());
            }
            return cache;

        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public synchronized void save(ClienteConector data) {
        try {
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            File out = xmlPath.toFile();
            File parent = out.getParentFile();

            if(parent != null){
                parent.mkdirs();
            }
            java.io.StringWriter sw = new java.io.StringWriter();
            m.marshal(data, sw); //pasa los datos a escritura
            m.marshal(data, out); //los escribe en el xml

            cache=data;

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Path getXmlPath() {
        return xmlPath;
    }
}
