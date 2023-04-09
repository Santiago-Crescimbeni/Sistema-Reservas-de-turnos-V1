package com.example.SistemaReservaDeTurnos.Repository;


import com.example.SistemaReservaDeTurnos.BD.BaseDeDato;
import com.example.SistemaReservaDeTurnos.Entity.Domicilio;
import com.example.SistemaReservaDeTurnos.Entity.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente>{
    private  static final Logger logger = Logger.getLogger(PacienteDaoH2.class);
    @Override
    public Paciente agregar(Paciente paciente) {

        // Se define una conexión a la base de datos
        Connection connection=null;


        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();

            // Se crea un objeto DomicilioDaoH2 para insertar el domicilio del paciente
            DomicilioDaoH2 domicilioDaoH2= new DomicilioDaoH2();

            // Se inserta el domicilio del paciente en la base de datos
            Domicilio domicilio= domicilioDaoH2.agregar(paciente.getDomicilio());

            // Se crea la sentencia SQL para insertar el nuevo paciente en la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES (APELLIDO, NOMBRE, DOCUMENTO, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            // Se establecen los parámetros de la sentencia SQL con los datos del objeto Paciente
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getDocumento());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5,domicilio.getId());

            // Se ejecuta la sentencia SQL para insertar un nuevo paciente en la tabla
            preparedStatement.execute();


            // Se recuperan las claves generadas para el registro recién insertado
            ResultSet rs= preparedStatement.getGeneratedKeys();

            // Se recorre el resultado del conjunto de claves generadas para obtener el ID del nuevo paciente
            while (rs.next()){
                paciente.setId(rs.getInt(1));
            }

            // Se registra en el registro de actividad (log) que se insertó el paciente con éxito
            logger.info("Paciente  insertado con exito ");

        }catch (Exception e){

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

                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión
                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");
            }
        }

        // Se retorna el objeto Paciente con el ID generado
        return paciente;
    }

    @Override
    public Paciente listar(int id) {

        // Se define una conexión a la base de datos
        Connection connection= null;

        //inicializa la variable paciente con un valor nulo.
        //en el caso de que no se encuentre ningún paciente  en la base de datos, se devolverá null
        Paciente paciente=null;

        try{
            // Se obtiene la conexión a la base de datos
            connection=BaseDeDato.getConnection();

            // se crea una instancia de la clase DomicilioDaoH2 que se utilizará más adelante para obtener información sobre el domicilio del paciente.
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();


            // Se prepara la consulta SQL para obtener el paciente con el id dado
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID= ?");
            preparedStatement.setInt(1,id);


            // Se ejecuta la consulta SQL y se obtiene el resultado en un objeto ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            // Se recorre el resultado y se construye un objeto Paciente a partir de los datos obtenidos
            while (rs.next()){
              paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilioDaoH2.listar(rs.getInt(6)));
            }

            // Se registra en el registro de actividad (log) que se listo el paciente con éxito
            logger.info("paciente listado con exito ");
        }catch (Exception e){
            e.printStackTrace();
            // Se registra en el registro de actividad (log) que se interrumpió la conexión
            logger.info("La conexion fue interrumpida ");

        }finally {
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
        return paciente;
    }

    @Override
    public Paciente modificar(Paciente paciente) {

        // Se define una conexión a la base de datos
        Connection connection=null;

        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();


            // Se prepara la sentencia SQL para modificar un nuevo Paciente en la tabla "Pacientes "
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE PACIENTES SET APELLIDO=?, NOMBRE=?, DOCUMENTO=?, FECHA_INGRESO=?, DOMICILIO_ID=? WHERE ID=?");

            // Se establecen los valores de los parámetros de la sentencia SQL
                preparedStatement.setString(1,paciente.getApellido());
                preparedStatement.setString(2,paciente.getNombre());
                preparedStatement.setString(3,paciente.getDocumento());
                preparedStatement.setDate(4,Date.valueOf(paciente.getFechaIngreso()));
                preparedStatement.setInt(5,paciente.getDomicilio().getId());
                preparedStatement.setInt(6,paciente.getId());


            // Se ejecuta la sentencia SQL para modificar el paciente
            preparedStatement.execute();


            // Se registra en el registro de actividad (log) que se modifico el paciente con éxito
            logger.info("Paciente modificado con exito ");

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
        // Se devuelve el objeto paciente con valores modificados
        return paciente;
    }


    @Override
    public void eliminar(int id) {

        // Se define una conexión a la base de datos
        Connection connection=null;


        try{
            // Se obtiene la conexión a la base de datos
            connection= BaseDeDato.getConnection();


            // Se prepara la sentencia SQL para eliminar un nuevo Paciente en la tabla "Pacientes "
            // La sentencia contiene los valores de los parámetros a ser insertados como signos de interrogación (?)

            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");

            // Se establecen los valores de los parámetros de la sentencia SQL
            preparedStatement.setInt(1,id);


            // Se ejecuta la sentencia SQL para eliminar el paciente
            preparedStatement.execute();

            // Se registra en el registro de actividad (log) que se elimino el paciente con éxito
            logger.info("Paciente eliminado con exito ");

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

    }





    @Override
    public List<Paciente> listarTodos() {

        //Crea una nueva lista de pacientes vacía:
        List<Paciente> pacientesList= new ArrayList<>();
        //Declara una conexión a la base de datos y un objeto de acceso a datos para la tabla de domicilios:

        Connection connection=null;

        DomicilioDaoH2 domicilioDaoH2 =new DomicilioDaoH2();

        try{
            //Intenta obtener una conexión a la base de datos

            connection=BaseDeDato.getConnection();


            // ejecutar una consulta SQL para obtener todos los registros de la tabla "PACIENTES":
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PACIENTES");


            // Se recorre el resultado y se construye un objeto Paciente a partir de los datos obtenidos
            ResultSet rs = preparedStatement.executeQuery();

            //Recorre los resultados de la consulta y por cada registro encontrado crea un objeto "Paciente" y lo agrega a la lista de pacientes:

            while (rs.next()){
                pacientesList.add(new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilioDaoH2.listar(rs.getInt(6))));
            }

            //Registra un mensaje de éxito en el log:

            logger.info("Pacientes listados con exito ");


        }catch (Exception e){
            //En caso de ocurrir alguna excepción durante el proceso, imprime el error correspondiente
            // y registra un mensaje de error en el log:

            e.printStackTrace();

            logger.info("La conexion fue interrumpida ");

        }finally {
            try {

                //Cierra la conexión a la base de datos
                // y registra un mensaje de éxito en el log:

                connection.close();
                logger.info("Conexion cerrada con exito");

            }catch (Exception ex){

                // Se registra en el registro de actividad (log) que no se pudo cerrar la conexión

                ex.printStackTrace();
                logger.warn(" La conexion a la base de datos no fue cerrada con exito ");

            }
            }

        //Retorna la lista de pacientes creada:

        return pacientesList;
    }
}
