package com.studentservice.repository;

import com.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
Paquete repository

Esta capa se encarga de la comunicación directa con la base de datos (persistencia).
Proporciona operaciones CRUD predefinidas mediante Spring Data JPA.
Se comunica con la capa Service.
*/

/*
Interfaz StudentRepository

Permite realizar operaciones de base de datos sobre la entidad Student.
Extiende JpaRepository para obtener soporte completo de CRUD y paginación.
*/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByDni(String dni);
}
