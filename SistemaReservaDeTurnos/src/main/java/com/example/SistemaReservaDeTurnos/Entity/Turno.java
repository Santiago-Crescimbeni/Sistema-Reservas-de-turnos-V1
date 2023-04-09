package com.example.SistemaReservaDeTurnos.Entity;


import java.time.LocalDate;

//define una clase llamada "Turno" que representa un Turno.


public class Turno {
    //La clase tiene los siguientes campos o propiedades:

    private int id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;


    //La clase tiene tres constructores:

    //El primer constructor toma todos los campos como argumentos y los inicializa.
    public Turno(int id, Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }


    //El segundo constructor toma todos los campos excepto "id" como argumentos y los inicializa.
// Este constructor se utiliza para crear nuevos objetos de "Turnos" que aún no tienen un "id" asignado.
    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    //El tercer constructor es un constructor (por defecto)vacío que no toma argumentos.
    // Este constructor se utiliza cuando no se tienen valores para inicializar los campos.
    //Jackson es una biblioteca de Java que se utiliza para convertir objetos Java en JSON y viceversa.
    //Jackson utiliza este constructor de la clase Java para crear objetos y establecer sus valores a partir de datos JSON.

    public Turno() {
    }




    //También se crean métodos "get" y "set" para cada uno de los campos de la clase, lo que permite acceder y modificar los valores de los campos.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
