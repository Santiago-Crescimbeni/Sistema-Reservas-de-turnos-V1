package com.example.SistemaReservaDeTurnos.Entity;

//define una clase llamada "Domicilio" que representa un domicilio.





public class Domicilio {
    //La clase tiene los siguientes campos o propiedades:

    private int id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;


    //La clase tiene tres constructores:
    //
    //El primer constructor toma todos los campos como argumentos y los inicializa.
    public Domicilio(int id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

//El segundo constructor toma todos los campos excepto "id" como argumentos y los inicializa.
// Este constructor se utiliza para crear nuevos objetos de "Domicilio" que aún no tienen un "id" asignado.

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    //El tercer constructor es un constructor (por defecto)vacío que no toma argumentos.
    // Este constructor se utiliza cuando no se tienen valores para inicializar los campos.
    //Jackson es una biblioteca de Java que se utiliza para convertir objetos Java en JSON y viceversa.
    //Jackson utiliza este constructor de la clase Java para crear objetos y establecer sus valores a partir de datos JSON.
    public Domicilio() {
    }



    //También se crean métodos "get" y "set" para cada uno de los campos de la clase, lo que permite acceder y modificar los valores de los campos.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }



}
