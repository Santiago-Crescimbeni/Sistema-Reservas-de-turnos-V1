package com.example.SistemaReservaDeTurnos.Service;
import com.example.SistemaReservaDeTurnos.Repository.IDao;
import com.example.SistemaReservaDeTurnos.Entity.Turno;
import com.example.SistemaReservaDeTurnos.Repository.TurnoDaoH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//Este es un servicio que maneja la lógica de negocio relacionada con la entidad Turno en una aplicación.
// Aquí se definen los métodos para agregar, listar, modificar y eliminar objetos Turno en la base de datos.


//se utiliza la anotación @Service para indicar que esta clase es un servicio que será administrado por el contenedor de Spring.
@Service
public class TurnoService {


// se define un objeto "pacienteDaoH2" de la clase "TurnoDaoH2",
    // que es responsable de interactuar con la base de datos H2 para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en los objetos "Turnos".

    private TurnoDaoH2 turnoIDao;


//se utiliza la anotación @Autowired para inyectar una instancia de TurnoDaoH2 en el constructor de la clase TurnoService
    @Autowired
    public TurnoService(TurnoDaoH2 turnoIDao) {
        this.turnoIDao = turnoIDao;
    }


    // se definidos los métodos  siguientes:



//El método agregarTurno recibe un objeto Turno y lo agrega en la base de datos a través del objeto TurnoDaoH2,
// que es una instancia de una clase que implementa la interfaz IDao<Turno>.
    public Turno agregarTurno (Turno turno){
        return  turnoIDao.agregar(turno);
    }




    //El método listarTurnoOptional recibe un identificador de un objeto Turno y devuelve un Optional<Turno>
    // que contiene el objeto Turno correspondiente a ese identificador en la base de datos.
    // Este método utiliza el método listarOptional definido en el objeto TurnoDaoH2.
    public Optional<Turno> listarTurnoOptional(int id) {
        return turnoIDao.listarOptional(id);
    }


    //El método modificarTurno recibe un objeto Turno y lo modifica en la base de datos a través del objeto TurnoDaoH2.
    public Turno modificarTurno (Turno turno){
        return  turnoIDao.modificar(turno);
    }

    //El método eliminarTurno recibe un identificador de un objeto Turno y lo elimina de la base de datos a través del objeto TurnoDaoH2.
    public void  eliminarTurno (int id){
        turnoIDao.eliminar(id);
    }


    //El método listarTodosTurno devuelve una lista de todos los objetos Turno en la base de datos a través del objeto TurnoDaoH2.
    public List<Turno> listarTodosTurno (){
        return turnoIDao.listarTodos();
    }



}
