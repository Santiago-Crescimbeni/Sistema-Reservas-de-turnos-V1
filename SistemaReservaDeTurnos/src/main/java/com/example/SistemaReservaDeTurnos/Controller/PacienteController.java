package com.example.SistemaReservaDeTurnos.Controller;

import com.example.SistemaReservaDeTurnos.Entity.Paciente;
import com.example.SistemaReservaDeTurnos.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//define un controlador para manejar solicitudes HTTP relacionadas con la entidad "Pacientes"

//@RestController se utiliza para definir una clase que maneja las solicitudes HTTP de entrada y devuelve una respuesta HTTP de salida en formato JSON o XML.


// @RequestMapping("/pacientes")  se utiliza para asignar URLs a métodos específicos dentro de una clase controladora. Al agregar @RequestMapping("/pacientes")
//en la declaración de clase del controlador,
//se indica que todas las solicitudes que comiencen con /pacientes se manejarán en la clase pacientesController.

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    //Utiliza un Servicio de pacientes
    private PacienteService pacienteService;


    //@Autowired es una de las formas en que se puede inyectar una dependencia en Spring Framework
    //se utiliza para permitir que Spring resuelva y cree automáticamente una instancia de una clase o interfaz, que se puede utilizar en el componente actual.
    // Inyecta la dependencia de la clase Service en el controlador
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }



//    El método "registrarPaciente" es un controlador de POST que maneja las solicitudes para agregar un nuevo paciente
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.agregarPaciente(paciente);
    }

    //El método "actualizarPaciente" es un controlador de PUT que maneja las solicitudes para actualizar un paciente existente.
    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.modificarPaciente(paciente);
    }


    //El método "buscarPaciente" es un controlador de GET que maneja las solicitudes para listar un paciente existente.
    @GetMapping ("/{id}")
    public Paciente buscarPaciente(@PathVariable int id){
        return pacienteService.listarPaciente(id);
    }


    //El método "buscarTodos" es un controlador de GET que maneja las solicitudes para listar todos los  paciente existente.

    @GetMapping ()
    public List<Paciente> buscarTodos(){
        return  pacienteService.listarTodosPacientes();
    }


    //El método "eliminarPaciente" es un controlador de GET que maneja las solicitudes para eliminar uno de  los  paciente existente.

    @DeleteMapping ("/{id}")
    public void  eliminarPaciente(@PathVariable int id){
        pacienteService.eliminarPaciente(id);
    }







}

