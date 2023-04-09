package com.example.SistemaReservaDeTurnos.BD;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;


//la clase "BaseDeDato" proporciona una manera de obtener una conexión a una base de datos H2
// y crear la estructura de tablas para la misma,
// todo ello utilizando los detalles de conexión almacenados en las variables constantes de la clase.



public class BaseDeDato {

    // Define un objeto Logger para registrar mensajes de información y advertencias.
    private static final Logger logger= Logger.getLogger(BaseDeDato.class);

    // Define variables constantes para almacenar la información necesaria para la conexión a la base de datos.
    private final static String DRIVER="org.h2.Driver";
    private final static String URL="jdbc:h2:./Database/Integrador/SistemaReservaTurno";
    private final static String USER="root";
    private final static String PASS="1234";



    // Define un método estático que devuelve una conexión a la base de datos.
    public static Connection getConnection() throws Exception{

        // Registra el controlador de la base de datos.
        Class.forName(DRIVER);

        // Devuelve una conexión a la base de datos utilizando los detalles de conexión almacenados en las variables constantes.
        return DriverManager.getConnection(URL,USER,PASS);

    }


    // Define un método estático que crea la estructura de tablas para la base de datos.
    public static void CreaeBd(){
        Connection connection=null;


        try {
            // Obtiene una conexión a la base de datos.
            Class.forName(DRIVER);

            // Ejecuta el script "create.sql" para crear la estructura de tablas en la base de datos.
            connection=DriverManager.getConnection(URL+";INIT=RUNSCRIPT FROM 'create.sql'",USER,PASS);

            // Registra un mensaje de información indicando que se ha creado una conexión a la base de datos.
            logger.info("Conexion creada con exito ");
        }catch(Exception e){

            // Registra un mensaje de advertencia indicando que no se ha podido establecer una conexión a la base de datos.
            logger.warn("Conexion a la base de datos interrumpida  ");

        }finally {
            try {

                // Cierra la conexión a la base de datos.
                connection.close();

                // Registra un mensaje de información indicando que se ha cerrado la conexión a la base de datos con éxito.
                logger.info("Conexion cerrada con exito");
            }catch (Exception ex){

                // Registra un mensaje de advertencia indicando que no se ha podido cerrar la conexión a la base de datos.
                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");
            }
            }
    }



}
