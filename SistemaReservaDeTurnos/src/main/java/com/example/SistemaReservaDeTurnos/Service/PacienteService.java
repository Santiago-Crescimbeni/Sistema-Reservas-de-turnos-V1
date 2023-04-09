package com.example.SistemaReservaDeTurnos.Service;


import com.example.SistemaReservaDeTurnos.Repository.PacienteDaoH2;
import com.example.SistemaReservaDeTurnos.Entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// "PacienteService" proporciona una capa de abstracción entre la aplicación y la base de datos,
// permitiendo a los usuarios realizar operaciones CRUD en objetos "Paciente" de manera fácil y segura.




//La anotación "@Service" indica que esta clase es un componente de servicio y puede ser administrada por un contenedor de Spring.
@Service
public class PacienteService {


    // se define un objeto "pacienteDaoH2" de la clase "PacienteDaoH2",
    // que es responsable de interactuar con la base de datos H2 para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en los objetos "Paciente".
    private PacienteDaoH2 pacienteDaoH2;


    @Autowired
    public PacienteService(PacienteDaoH2 pacienteDaoH2) {
        this.pacienteDaoH2 = pacienteDaoH2;
    }

// se definidos los métodos  siguientes:

    //agrega un nuevo objeto "Paciente" a la base de datos y devuelve el objeto agregado.
      public Paciente agregarPaciente (Paciente paciente){
        return  pacienteDaoH2.agregar(paciente);
    }


    //devuelve el objeto "Paciente" correspondiente al identificador "id" especificado.

    public Paciente listarPaciente (int id){
        return  pacienteDaoH2.listar(id);
    }

    //modifica el objeto "Paciente" especificado en la base de datos y devuelve el objeto modificado
    public Paciente modificarPaciente (Paciente paciente){
        return  pacienteDaoH2.modificar(paciente);
    }

//elimina el objeto "Paciente" correspondiente al identificador "id" especificado de la base de datos
    public void  eliminarPaciente (int id){
        pacienteDaoH2.eliminar(id);
    }


    //devuelve una lista de todos los objetos "Paciente" en la base de datos.
    public List<Paciente>  listarTodosPacientes (){
        return pacienteDaoH2.listarTodos();
    }


}
