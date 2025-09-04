com.example.hotelmanager
├─ HotelApplication.java                          
├─ ui                                             (Presentación usando MVC)
│   ├─ controller                                  (LoginController, etc.)
│   ├─ model                                       
│   └─ view                                         (.fxml)
│
├─ application                                    (Servicios / Casos de uso)
│
├─ domain                                         (Reglas de negocio puras)
│   └─entity                                      (Cliente, Habitacion, Reservacion…)

│
└─ infrastructure                                 (Detalles técnicos)
    └─adapter                                     (DateAdapter, otros adaptadores)
  
ui (Presentación): Contiene controladores (controller), vistas (view en .fxml) y modelos de UI (model).     
Esta capa se encarga exclusivamente de renderizar datos, validar formatos y orquestar eventos de la interfaz,   
sin acceder a la base de datos ni implementar reglas de negocio.

application (Servicios / Casos de uso): Gestiona la lógica de orquestación y los flujos de aplicación.  

domain (Dominio): Contiene las entidades del hotel (Cliente, Habitacion, Reservacion) y concentra las reglas de negocio  
e invariantes.

infrastructure (Detalles técnicos): Implementa adaptadores (adapter) y repositorios, encapsulando la persistencia   
y los detalles técnicos (por ejemplo, conexión a base de datos o formatos de fecha). Esto permite sustituir   
la tecnología sin afectar la UI ni la capa de aplicación.
