package com.studentservice.service;

import com.studentservice.model.Student;
import java.util.List;

/*
Paquete service

Esta capa define las interfaces que representan las reglas de negocio del sistema.
Declara los métodos CRUD y procesos lógicos necesarios para gestionar la información de los estudiantes.
Se comunica con la capa Controller y Repository.
*/

/*
Interfaz StudentService

Contrato que define las operaciones de negocio disponibles para la gestión de estudiantes.
*/
public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student studentDetails);
    void deleteStudent(Long id);
}
