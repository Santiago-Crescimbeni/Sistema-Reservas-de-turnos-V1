package com.example.SistemaReservaDeTurnos.Repository;

import com.example.SistemaReservaDeTurnos.Entity.Turno;

import java.util.List;
import java.util.Optional;

// define una interfaz llamada IDao que se utiliza para definir los métodos que deben ser implementados
// por cualquier clase que implemente esta interfaz.

public interface IDao <T>{


    //El método agregar se utiliza para agregar un objeto de tipo T a la fuente de datos.
    // Este método devuelve el objeto recién agregado.
    T agregar(T t);


    //El método listar se utiliza para obtener un objeto de tipo T de la fuente de datos, según su identificador id.
    T listar(int id);


//El método modificar se utiliza para actualizar un objeto de tipo T existente en la fuente de datos.
// Este método devuelve el objeto actualizado.
    T modificar (T t);


    //El método eliminar se utiliza para eliminar un objeto de tipo T de la fuente de datos según su identificador id.

    void eliminar (int id);


    //El método listarTodos se utiliza para obtener una lista de todos los objetos de tipo T que existen en la fuente de datos.
    // Este método devuelve una lista de objetos de tipo T.
    List<T> listarTodos();
}


