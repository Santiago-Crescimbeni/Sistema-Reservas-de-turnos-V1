package com.example.SistemaReservaDeTurnos.Entity;


//define una clase llamada "Odontologo" que representa un Odontologo.




public class Odontologo {

    //La clase tiene los siguientes campos o propiedades:

    private int id;
    private String nombre;
    private String apellido;
    private String matricula;


    //La clase tiene tres constructores:

    //El primer constructor toma todos los campos como argumentos y los inicializa.

    public Odontologo(int id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }


    //El segundo constructor toma todos los campos excepto "id" como argumentos y los inicializa.
// Este constructor se utiliza para crear nuevos objetos de "Domicilio" que aún no tienen un "id" asignado.

    public Odontologo(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }


    //El tercer constructor es un constructor (por defecto)vacío que no toma argumentos.
    // Este constructor se utiliza cuando no se tienen valores para inicializar los campos.
    //Jackson es una biblioteca de Java que se utiliza para convertir objetos Java en JSON y viceversa.
    //Jackson utiliza este constructor de la clase Java para crear objetos y establecer sus valores a partir de datos JSON.

    public Odontologo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
//También se crean métodos "get" y "set" para cada uno de los campos de la clase, lo que permite acceder y modificar los valores de los campos.



    //se sobrescribe el método toString() de la clase Object y devuelve una representación en forma de cadena del objeto Odontologo.
    //se construye una cadena que incluye los valores de los campos id, nombre, apellido y nroMatricula del objeto Odontologo
    // utilizando la sintaxis de cadena de caracteres de Java.


    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
