package com.example.SistemaReservaDeTurnos.Service;



import com.example.SistemaReservaDeTurnos.Repository.OdontologoDaoH2;
import com.example.SistemaReservaDeTurnos.Entity.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//la clase "OdontologoService" proporciona una capa de abstracción entre la aplicación y la base de datos,
// permitiendo a los usuarios realizar operaciones CRUD en objetos "Odontologo" de manera fácil y segura.


//La anotación "@Service" indica que esta clase es un componente de servicio y puede ser administrada por un contenedor de Spring.

@Service
public class OdontologoService {


    //se define un objeto "odontologoDaoH2" de la clase "OdontologoDaoH2", que es responsable de interactuar con la base de datos H2
    // para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en los objetos "Odontologo".
    private OdontologoDaoH2 odontologoDaoH2;

    @Autowired
    public OdontologoService(OdontologoDaoH2 odontologoDaoH2) {
        this.odontologoDaoH2 = odontologoDaoH2;
    }


    // se definidos los métodos  siguientes:

    //agrega un nuevo objeto "Odontologo" a la base de datos y devuelve el objeto agregado.
    public Odontologo agregarOdontologo (Odontologo odontologo){
        return  odontologoDaoH2.agregar(odontologo);
    }

    //devuelve el objeto "Odontologo" correspondiente al identificador "id" especificado.
    public Odontologo listarOdontologo (int id){
        return  odontologoDaoH2.listar(id);
    }

    // modifica el objeto "Odontologo" especificado en la base de datos y devuelve el objeto modificado.
    public Odontologo modificarOdontologo (Odontologo odontologo){
        return  odontologoDaoH2.modificar(odontologo);
    }

//elimina el objeto "Odontologo" correspondiente al identificador "id" especificado de la base de datos
    public void  eliminarOdontologo (int id){
        odontologoDaoH2.eliminar(id);
    }
//devuelve una lista de todos los objetos "Odontologo" en la base de datos.
    public List<Odontologo> listarTodosOdontologo (){
        return odontologoDaoH2.listarTodos();
    }




}
