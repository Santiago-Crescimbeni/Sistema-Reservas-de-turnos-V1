package com.example.SistemaReservaDeTurnos;

import com.example.SistemaReservaDeTurnos.BD.BaseDeDato;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


 //Aquí se utiliza la anotación @SpringBootApplication que es una combinación de varias anotaciones que se utilizan comúnmente juntas en aplicaciones Spring Boot.
// Estas anotaciones incluyen @Configuration, @EnableAutoConfiguration y @ComponentScan.
@SpringBootApplication
public class SistemaReservaDeTurnosApplication {

	public static void main(String[] args) {

		// se llama al método estático CreaeBd() de la clase BaseDeDato que crea la base de datos.
		// Luego, se llama al método estático run de la clase SpringApplication, que inicia la aplicación Spring Boot.

		BaseDeDato.CreaeBd();
		SpringApplication.run(SistemaReservaDeTurnosApplication.class, args);
	}

}
