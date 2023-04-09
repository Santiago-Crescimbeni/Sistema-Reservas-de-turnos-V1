package com.example.SistemaReservaDeTurnos.Controller;

import com.example.SistemaReservaDeTurnos.Entity.Turno;
import com.example.SistemaReservaDeTurnos.Service.OdontologoService;
import com.example.SistemaReservaDeTurnos.Service.PacienteService;
import com.example.SistemaReservaDeTurnos.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//define un controlador para manejar solicitudes HTTP relacionadas con la entidad "Turnos"
//@RestController se utiliza para definir una clase que maneja las solicitudes HTTP de entrada y devuelve una respuesta HTTP de salida en formato JSON o XML.


// @RequestMapping("/Turnos")  se utiliza para asignar URLs a métodos específicos dentro de una clase controladora. Al agregar @RequestMapping("/Turnos")
//en la declaración de clase del controlador,
//se indica que todas las solicitudes que comiencen con /Turnos se manejarán en la clase pacientesController.


@RestController
@RequestMapping("/turnos")
public class TurnoController {


    //La clase tiene tres campos privados: turnoService, pacienteService y odontologoService,
    // que se inicializan mediante la inyección de dependencias en el constructor de la clase.
    private  TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;


    //@Autowired es una de las formas en que se puede inyectar una dependencia en Spring Framework
    //se utiliza para permitir que Spring resuelva y cree automáticamente una instancia de una clase o interfaz, que se puede utilizar en el componente actual.
    //Constructor con parámetros para crear un nuevo turno.

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


   // El método @PostMapping maneja las solicitudes HTTP POST para registrar un nuevo turno.
    // Toma un objeto Turno en el cuerpo de la solicitud HTTP y devuelve una respuesta HTTP con el objeto Turno recién registrado.

    @PostMapping
    public ResponseEntity<Turno> RegistrarTurno (@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;

        //Primero, el método verifica si tanto el paciente como el odontólogo especificados en el turno existen en la base de datos.
        // Esto se hace utilizando los métodos listarPaciente() y listarOdontologo() del servicio de paciente y odontólogo, respectivamente.

        if (pacienteService.listarPaciente(turno.getPaciente().getId())!=null &&
                odontologoService.listarOdontologo(turno.getOdontologo().getId())!=null){

            //Si ambos existen, entonces se agrega el turno llamando al método agregarTurno() del servicio de turno,
            // y se devuelve una respuesta HTTP con el objeto Turno recién registrado utilizando ResponseEntity.ok().
            respuesta=ResponseEntity.ok(turnoService.agregarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return respuesta;
    }


//El método @GetMapping("/{id}")  maneja las solicitudes HTTP GET para recuperar un turno con un ID específico.
// El método utiliza el método listarTurnoOptional() del servicio de turno para buscar el turno en la base de datos.
// Si el turno existe, se devuelve una respuesta HTTP con el objeto Turno encontrado utilizando ResponseEntity.ok().
// Si no existe, se devuelve una respuesta HTTP con un estado de error.
    @GetMapping("/{id}")
    public ResponseEntity<Turno> listarTurno(@PathVariable int id){
        Optional<Turno> turnoBuscado= turnoService.listarTurnoOptional(id);
        if (turnoBuscado.isPresent()){
            //Que el turno existe
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            //no existe el turno con dicho id
            return ResponseEntity.notFound().build();
        }

    }



   // El método @GetMapping maneja las solicitudes HTTP GET para recuperar una lista de todos los turnos.
    // El método utiliza el método listarTodosTurno() del servicio de turno para recuperar la lista de turnos,
    // y devuelve una respuesta HTTP con la lista utilizando ResponseEntity.ok().

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosTurnos(){
        return ResponseEntity.ok(turnoService.listarTodosTurno());
    }





//El método @DeleteMapping("/{id}") maneja las solicitudes HTTP DELETE para eliminar un turno con un ID específico.
// El método utiliza el método listarTurnoOptional() del servicio de turno para buscar el turno en la base de datos.
// Si el turno existe, se elimina utilizando el método eliminarTurno() del servicio de turno, y se devuelve una respuesta HTTP con un mensaje indicando que el turno se eliminó correctamente.
// Si no existe, se devuelve una respuesta HTTP con un estado de error.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) {
        Optional<Turno> turnoBuscado = turnoService.listarTurnoOptional(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno" +
                    " con id= " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el turno" +
                    " con id= " + id +" pues no existe en la base de datos.");
        }
    }




//El método @PutMapping maneja las solicitudes HTTP PUT para actualizar un turno existente.
// Toma un objeto Turno en el cuerpo de la solicitud HTTP y utiliza el método modificarTurno() del servicio de turno para actualizar el turno en la base de datos.
// Antes de actualizar el turno, el método verifica si el turno existe en la base de datos y si tanto el paciente como el odontólogo especificados en el turno existen en la base de datos.
// Si el turno y ambos existen, se actualiza el turno y se devuelve una respuesta HTTP con un mensaje indicando que el turno se actualizó correctamente.
// Si hay algún error, se devuelve una respuesta HTTP con un estado de error.

    @PutMapping()
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){

        //Podemos encontrar un problema con el id del turno
        //Podemos encontrar un problema con el id del odontologo y/o paciente

        Optional<Turno> turnoBuscado= turnoService.listarTurnoOptional(turno.getId());
        if (turnoBuscado.isPresent()){
            //el turno existe, podemos verificar el resto
            //PacienteService pacienteService= new PacienteService();
            //ambos existen, puedo guardar el turno
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok("Se actualizó el turno con id= "+turno.getId());
        }
        else{
            return ResponseEntity.badRequest().body("No se puede actualizar el turno con" +
                    " id= "+turno.getId()+" ya que existe un error con el odontologo y/o " +
                    "paciente");
        }

}



}
