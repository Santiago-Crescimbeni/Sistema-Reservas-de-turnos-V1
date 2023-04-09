package com.example.SistemaReservaDeTurnos.Repository;

import com.example.SistemaReservaDeTurnos.BD.BaseDeDato;
import com.example.SistemaReservaDeTurnos.Entity.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio>{
  private static final Logger logger= Logger.getLogger(DomicilioDaoH2.class);
    @Override
    public Domicilio agregar(Domicilio domicilio) {
        // Se define una conexión a la base de datos
        Connection connection=null;
        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();

            // Se prepara la sentencia SQL para insertar un nuevo domicilio en la tabla "DOMICILIO"
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)
            // Se utiliza la opción RETURN_GENERATED_KEYS para obtener la clave generada por la base de datos

            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO DOMICILIOS (CALLE,NUMERO,LOCALIDAD,PROVINCIA) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            // Se establecen los valores de los parámetros de la sentencia SQL
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            // Se ejecuta la sentencia SQL para insertar un nuevo domicilio en la tabla
                preparedStatement.execute();

            ResultSet rs =preparedStatement.getGeneratedKeys();
            // Se recorre el conjunto de resultados para obtener el valor de la clave generada
            while (rs.next()) {
            // Se utiliza el método getInt(1) para obtener el valor de la primera columna (en este caso, la clave generada)
            domicilio.setId(rs.getInt(1));
            }
        // Se registra en el registro de actividad (log) que se insertó el domicilio con éxito
            logger.info("Domicilio insertado con exito ");

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
        // Se devuelve el objeto domicilio con su clave generada por la base de datos actualizada
        return domicilio;
    }

    @Override
    public Domicilio listar(int id) {
        // se crea el objeto domicilio para poder asignarle los valores recuperados de la base de datos y luego devolver el objeto con los datos correspondientes.
        // se inicializa en null porque no se han recuperado todavia  los datos de la base de datos
        Domicilio domicilio=null;
            // Se define una conexión a la base de datos
            Connection connection=null;
            try{
                // Se obtiene la conexión a la base de datos
                connection= BaseDeDato.getConnection();

                // Se prepara la sentencia SQL para buscar un nuevo domicilio en la tabla "DOMICILIO"
                // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

                PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID =?");


                // Se establecen los valores de los parámetros de la sentencia SQL
                preparedStatement.setInt(1,id);


                // Se ejecuta la sentencia SQL para buscar el domicilio y se obtiene el conjunto de resultados.
                ResultSet rs= preparedStatement.executeQuery();


                // Se recorre el conjunto de resultados y se crea un objeto 'domicilio' con los valores obtenidos.
                // El constructor de la clase 'Domicilio' espera los siguientes parámetros: (id, calle, numero, localidad, provincia)
                while (rs.next()) {
                  domicilio= new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));

                }


                // Se registra en el registro de actividad (log) que se listo el domicilio con éxito
                logger.info("Domicilio listado con exito ");

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
            // Se devuelve el objeto domicilio
            return domicilio;
        }


    @Override
    public Domicilio modificar(Domicilio domicilio) {

        // Se define una conexión a la base de datos
        Connection connection=null;

        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();

            // Se prepara la sentencia SQL para modificar un nuevo domicilio en la tabla "DOMICILIO"
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?");


            // Se establecen los valores de los parámetros de la sentencia SQL
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getLocalidad());
            preparedStatement.setString(3, domicilio.getProvincia());
            preparedStatement.setInt(5,domicilio.getId());


            // Se ejecuta la sentencia SQL para modificar el domicilio
           preparedStatement.execute();



            // Se registra en el registro de actividad (log) que se modifico el domicilio con éxito
            logger.info("Domicilio modificado con exito ");

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
        // Se devuelve el objeto domicilio con valores modificados
        return domicilio;
        }


    @Override
    public void eliminar(int id) {
        // Se define una conexión a la base de datos
        Connection connection=null;


        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();

            // Se prepara la sentencia SQL para eliminar un domicilio de la tabla "DOMICILIO"
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID=?");


            // Se establecen los valores de los parámetros de la sentencia SQL
          preparedStatement.setInt(1,id);


            // Se ejecuta la sentencia SQL para modificar el domicilio
            preparedStatement.execute();



            // Se registra en el registro de actividad (log) que se elimino el domicilio con éxito
            logger.info("Domicilio eliminado con exito ");

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
        //  No retorna nada ya que es Void, simplemente se ejecuta

    }



    @Override
    public List<Domicilio> listarTodos() {

        //Se crea una lista vacía de objetos Domicilio:
        List<Domicilio> domicilioList= new ArrayList<>();

        // Se define una conexión a la base de datos
        Connection connection=null;
        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();


           // Se prepara la sentencia SQL que se va a ejecutar en la base de datos:
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM DOMICILIOS");

            // Se ejecuta la sentencia SQL para buscar el domicilio y se obtiene el conjunto de resultados.
            ResultSet rs= preparedStatement.executeQuery();


            //Se recorre el ResultSet y se crean objetos Domicilio con los datos obtenidos, que se agregan a la lista domicilioList:
            while (rs.next()) {
                domicilioList.add(new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }


            // Se registra en el registro de actividad (log) que se listo el domicilio con éxito
            logger.info("Domicilio listado con exito ");

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
        // Se devuelve la lista de objetos Domicilio:
        return domicilioList;
    }


    }

