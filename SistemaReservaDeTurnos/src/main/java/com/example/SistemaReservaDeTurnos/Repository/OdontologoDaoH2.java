package com.example.SistemaReservaDeTurnos.Repository;

import com.example.SistemaReservaDeTurnos.BD.BaseDeDato;
import com.example.SistemaReservaDeTurnos.Entity.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
Logger logger= Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo agregar(Odontologo odontologo) {

        // Declarar una conexión nula para poder manejarla en el bloque "try-with-resources"
        Connection connection=null;

        try{

            // Obtener una conexión desde la clase "BaseDeDato"
            connection= BaseDeDato.getConnection();

            // Preparar una declaración SQL para insertar un nuevo odontólogo
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            // Configurar los parámetros de la declaración SQL con los valores del odontólogo
            preparedStatement.setString(1,odontologo.getNombre());
            preparedStatement.setString(2,odontologo.getApellido());
            preparedStatement.setString(3,odontologo.getMatricula());

            // Ejecutar la declaración SQL para insertar el odontólogo
            preparedStatement.execute();


            // Obtener el ID generado automáticamente por la base de datos y actualizar el odontólogo
            ResultSet rs= preparedStatement.getGeneratedKeys();

            // Se recorre el conjunto de resultados para obtener el valor de la clave generada
            while (rs.next()){
                odontologo.setId(rs.getInt(1));
            }

            // Se registra en el registro de actividad (log) que se insertó el odontologo con éxito

            logger.info("Odontologo insertado con exito ");

        }catch (Exception e){

            // Manejar cualquier excepción que se produzca durante el proceso
            // Se registra en el registro de actividad (log) que se interrumpió la conexión

            e.printStackTrace();
            logger.info("La conexion fue interrumpida ");

        }finally {
            try {

                // Se cierra la conexión a la base de datos
                // Se registra en el registro de actividad (log) que se cerró la conexión con éxito
                connection.close();
                logger.info("Conexion cerrada con exito");


            }catch (Exception ex){

                // Manejar cualquier excepción que se produzca durante el proceso
                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");

            }

        }

        // Devolver el odontólogo actualizado con el ID generado
        return odontologo;
    }

    @Override
    public Odontologo listar(int id) {

        // Se define una conexión a la base de datos
        Connection connection=null;


        // se crea el objeto odontologo para poder asignarle los valores recuperados de la base de datos y luego devolver el objeto con los datos correspondientes.
        // se inicializa en null porque no se han recuperado todavia  los datos de la base de datos
        Odontologo odontologo= null;

        try{

            // Se obtiene una conexión a la base de datos
            connection = BaseDeDato.getConnection();


            // Se prepara la sentencia SQL para obtener el odontólogo con el ID especificado
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID= ?");

            // Se establece el valor del parámetro en la sentencia preparada
            preparedStatement.setInt(1,id);

            // Se ejecuta la sentencia SQL y se obtiene el resultado
            preparedStatement.execute();


            // Se ejecuta la sentencia SQL para buscar el odontologo y se obtiene el conjunto de resultados.
            ResultSet rs= preparedStatement.executeQuery();

            // Se recorre el resultado y se crea un objeto Odontólogo con los datos obtenidos
            while (rs.next()){
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }

            // Se registra en el registro de actividad (log) que se listó el odontólogo con éxito
            logger.info("Odontologo listado con exito ");
        }catch (Exception e){
            // Se registra en el registro de actividad (log) que se interrumpió la conexión

            e.printStackTrace();
            logger.info("La conexion fue interrumpida ");
        }
        finally {
            try{
                // Se cierra la conexión a la base de datos
                // Se registra en el registro de actividad (log) que se cerró la conexión con éxito

                connection.close();
                logger.info("Conexion cerrada con exito");


            }catch (Exception ex){

                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");
            }
        }

        // Se devuelve el objeto odontologo
        return odontologo;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {

        // Se define una conexión a la base de datos
        Connection connection =null;

        try {


            // Se obtiene la conexión a la base de datos
            connection=BaseDeDato.getConnection();


            // Se prepara la sentencia SQL para modificar un nuevo odontologo en la tabla "Odontologos"
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=?");


            // Se establecen los valores de los parámetros de la sentencia SQL
            preparedStatement.setString(1,odontologo.getNombre());
            preparedStatement.setString(2,odontologo.getApellido());
            preparedStatement.setString(3,odontologo.getMatricula());


            // Se ejecuta la sentencia SQL para modificar el odontologo
            preparedStatement.execute();

            // Se registra en el registro de actividad (log) que se modifico el Odontologo con éxito
            logger.info("Odontologo modificado con exito ");

        }catch (Exception e){

            // Se registra en el registro de actividad (log) que se interrumpió la conexión
            logger.info("La conexion fue interrumpida ");
            e.printStackTrace();
        }finally {
            try {
                // Se cierra la conexión a la base de datos
                // Se registra en el registro de actividad (log) que se cerró la conexión con éxito
                connection.close();
                logger.info("Conexion cerrada con exito");


            }catch (Exception ex){
                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");

            }
        }

        // Se devuelve el objeto Odontologo con valores modificados
        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        // Se define una conexión a la base de datos
        Connection connection=null;


        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();

            // Se prepara la sentencia SQL para eliminar un odontologo de la tabla "odontologos"
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID=?");


            // Se establecen los valores de los parámetros de la sentencia SQL
            preparedStatement.setInt(1,id);


            // Se ejecuta la sentencia SQL para modificar el odontologo
            preparedStatement.execute();

            // Se registra en el registro de actividad (log) que se elimino el odontologo con éxito
            logger.info("Odontologo eliminado con exito ");

        }catch (Exception e) {

            // Se registra en el registro de actividad (log) que se interrumpió la conexión
            e.printStackTrace();
            logger.info("La conexion fue interrumpida ");

        }finally {
            try{
                // Se cierra la conexión a la base de datos
                // Se registra en el registro de actividad (log) que se cerró la conexión con éxito

                connection.close();
                logger.info("Conexion cerrada con exito");


            }catch (Exception ex){
                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");
                ex.printStackTrace();
            }
        }
        //  No retorna nada ya que es Void, simplemente se ejecuta

    }

    @Override
    public List<Odontologo> listarTodos() {

        //Se crea una lista vacía de objetos Domicilio:
        List<Odontologo> odontologosList=new ArrayList<>();


        // Se define una conexión a la base de datos
        Connection connection=null;
        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();


            // Se prepara la sentencia SQL que se va a ejecutar en la base de datos:
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            // Se ejecuta la sentencia SQL para buscar el odontologo y se obtiene el conjunto de resultados.
            ResultSet rs= preparedStatement.executeQuery();


            //Se recorre el ResultSet y se crean objetos odontologo con los datos obtenidos, que se agregan a la lista odontologoList:
            while (rs.next()) {
                odontologosList.add(new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));

            }


            // Se registra en el registro de actividad (log) que se listo el odontologo con éxito
            logger.info("Odontologo listado con exito ");

        }catch (Exception e) {

            // Se registra en el registro de actividad (log) que se interrumpió la conexión
            logger.info("La conexion fue interrumpida ");
            e.printStackTrace();

        }finally {
            try{
                // Se cierra la conexión a la base de datos
                // Se registra en el registro de actividad (log) que se cerró la conexión con éxito

                connection.close();
                logger.info("Conexion cerrada con exito");


            }catch (Exception ex){
                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");
                ex.printStackTrace();
            }
        }
        // Se devuelve la lista de objetos odontologo:
        return odontologosList;
    }
}
