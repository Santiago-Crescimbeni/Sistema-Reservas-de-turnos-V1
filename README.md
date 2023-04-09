# Sistema-Reservas-de-turnos-V1

Versión 1:
El Sistema de Reserva de Turnos es una aplicación web que permite la gestión de turnos médicos en un hospital o clínica. Su objetivo principal es facilitar el proceso de asignación de turnos a los pacientes, agilizando el flujo de atención en la institución.

Descripción:
El sistema se compone de varias partes, incluyendo una interfaz de usuario, una base de datos para almacenar información de los pacientes, odontólogos y turnos, y un conjunto de servicios web que permiten la comunicación entre la interfaz de usuario y la base de datos.

La funcionalidad principal del sistema incluye la gestión de los pacientes, la programación de turnos, la asignación de turnos a los pacientes y la consulta de información de los pacientes y los turnos programados.

En resumen, el Sistema de Reserva de Turnos es una herramienta útil para administrar la reserva de turnos médicos de manera más eficiente y organizada en un centro médico.

Tecnologías Utilizadas:

Este sistema, fue desarrollado en Java con el framework Spring Boot, que facilita la construcción de aplicaciones web robustas y escalables, también utiliza la base de datos H2 para almacenar y recuperar información de los turnos. Además, utiliza la herramienta de gestión de proyectos Maven y la herramienta de desarrollo de API Postman para facilitar su desarrollo y pruebas.
Por otro lado, utiliza Thymeleaf para la generación de vistas y el framework de pruebas unitarias JUnit pruebas unitarias para garantizar la calidad del código.


Patrones:
El sistema implementa el patrón arquitectónico MVC (Model-View-Controller) que se utiliza comúnmente en el desarrollo de aplicaciones web. 

Además, el sistema también utiliza el patrón DAO (Data Access Object) que se utiliza para separar la capa de acceso a datos de la capa de lógica de negocio. Este patrón define una interfaz que abstrae el acceso a datos y se encarga de realizar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en la base de datos. La implementación concreta de esta interfaz se realiza en una clase DAO específica para cada entidad, que se encarga de interactuar con la base de datos utilizando tecnologías como JPA o Hibernate..
