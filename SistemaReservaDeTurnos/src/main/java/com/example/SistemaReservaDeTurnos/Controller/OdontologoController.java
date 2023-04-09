package com.example.SistemaReservaDeTurnos.Controller;

import com.example.SistemaReservaDeTurnos.Entity.Odontologo;
import com.example.SistemaReservaDeTurnos.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//define un controlador para manejar solicitudes HTTP relacionadas con la entidad "Odontólogo"

//@RestController se utiliza para definir una clase que maneja las solicitudes HTTP de entrada y devuelve una respuesta HTTP de salida en formato JSON o XML.


// @RequestMapping("/odontologos")  se utiliza para asignar URLs a métodos específicos dentro de una clase controladora. Al agregar @RequestMapping("/odontologos")
//en la declaración de clase del controlador,
//se indica que todas las solicitudes que comiencen con /odontologos se manejarán en la clase OdontologoController.

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    //Utiliza un Servicio de Odontologos
    private OdontologoService odontologoService;



    //@Autowired es una de las formas en que se puede inyectar una dependencia en Spring Framework
    //se utiliza para permitir que Spring resuelva y cree automáticamente una instancia de una clase o interfaz, que se puede utilizar en el componente actual.
    // Inyecta la dependencia de la clase Service en el controlador
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    // llama al método listarOdontologo de la instancia odontologoService, que devuelve un objeto Odontologo buscado por id

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable int id){
        return odontologoService.listarOdontologo(id);
    }




    // llama al método actualizarOdontologo de la instancia odontologoService, que actualiza un Odontologo
    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.modificarOdontologo(odontologo);
    }



    // llama al método listarTodosOdontologo de la instancia odontologoService, que devuelve una lista de todos los objetos Odontologo


    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos(){
        return ResponseEntity.ok(odontologoService.listarTodosOdontologo());
    }


    // llama al método eliminarOdontologo de la instancia odontologoService, que elimina el objeto Odontologo

    @DeleteMapping ("/{id}")
    public void  eliminarOdontologo(@PathVariable int id){
        odontologoService.eliminarOdontologo(id);
    }

    // llama al método agregarOdontologo de la instancia odontologoService, que agrega un nuevo objeto Odontologo y devuelve el objeto agregado

    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.agregarOdontologo(odontologo));
    }
}


//En este código, ok se utiliza en los métodos buscarTodosOdontologos y agregarOdontologo para devolver una respuesta HTTP con un estado 200 OK
//  y el cuerpo de la respuesta contiene los datos que se quieren enviar como respuesta.
// En el método buscarTodosOdontologos, se llama al método listarTodosOdontologo de la instancia odontologoService,
//  que devuelve una lista de todos los objetos Odontologo.
// Luego, se envía la respuesta HTTP 200 OK con la lista de Odontologo como el cuerpo de la respuesta.








