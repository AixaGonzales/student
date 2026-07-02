package com.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Clase principal StudentServiceApplication

Esta es la clase de arranque (bootstrap) del microservicio.
Inicia el contexto de Spring Boot, activa la autoconfiguración
y escanea todos los componentes dentro del paquete com.studentservice.
*/
@SpringBootApplication
public class StudentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }
}
