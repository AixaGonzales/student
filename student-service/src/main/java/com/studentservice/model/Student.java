package com.studentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

/*
Paquete model

Esta capa contiene las entidades de dominio de la aplicación que representan
las tablas en la base de datos. Define la estructura de los datos que
se persistirán y sus respectivas validaciones.
*/

/*
Entidad Student

Representa a un estudiante dentro del sistema.
Define los atributos del estudiante, mapea la tabla 'students'
y aplica validaciones de integridad con Jakarta Validation.
*/
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 20, message = "El DNI debe tener entre 8 y 20 caracteres")
    @Column(nullable = false, unique = true)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe exceder los 50 caracteres")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "La promoción es obligatoria")
    @Column(nullable = false)
    private String promotion;

    @NotNull(message = "La fecha de registro es obligatoria")
    @Column(nullable = false)
    private LocalDate date;
}
