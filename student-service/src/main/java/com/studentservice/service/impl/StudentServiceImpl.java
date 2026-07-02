package com.studentservice.service.impl;

import com.studentservice.exception.ResourceNotFoundException;
import com.studentservice.model.Student;
import com.studentservice.repository.StudentRepository;
import com.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
Paquete service.impl

Esta capa contiene la implementación concreta de las interfaces de negocio.
Aquí se ejecutan las validaciones lógicas, la orquestación de llamadas
y la interacción con la capa de persistencia (Repository).
*/

/*
Clase StudentServiceImpl

Implementa el CRUD completo definido en la interfaz StudentService.
Usa SLF4J para registrar logs informativos en cada operación y
gestiona transacciones lógicas mediante la anotación @Transactional.
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        log.info("Invocando listado de estudiantes");
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        log.info("Buscando estudiante por ID: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Estudiante con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Estudiante no encontrado con ID: " + id);
                });
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        log.info("Registrando estudiante con DNI: {}", student.getDni());
        if (studentRepository.findByDni(student.getDni()).isPresent()) {
            log.warn("El estudiante con DNI {} ya existe", student.getDni());
            throw new IllegalArgumentException("Ya existe un estudiante registrado con el DNI: " + student.getDni());
        }
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        log.info("Actualizando estudiante con ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se pudo actualizar. Estudiante con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Estudiante no encontrado con ID: " + id);
                });

        if (!student.getDni().equals(studentDetails.getDni()) &&
                studentRepository.findByDni(studentDetails.getDni()).isPresent()) {
            log.warn("No se pudo actualizar. El DNI {} ya está registrado", studentDetails.getDni());
            throw new IllegalArgumentException("Ya existe un estudiante registrado con el DNI: " + studentDetails.getDni());
        }

        student.setDni(studentDetails.getDni());
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setPromotion(studentDetails.getPromotion());
        student.setDate(studentDetails.getDate());

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        log.info("Eliminando estudiante con ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se pudo eliminar. Estudiante con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Estudiante no encontrado con ID: " + id);
                });
        studentRepository.delete(student);
    }
}
