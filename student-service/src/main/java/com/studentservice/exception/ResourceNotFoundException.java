package com.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Paquete exception

Esta capa gestiona los errores y excepciones del microservicio.
Centraliza las respuestas de error ante fallos de validación, recursos no encontrados
o errores internos del servidor, garantizando respuestas estructuradas al cliente.
*/

/*
Clase ResourceNotFoundException

Excepción personalizada para cuando no se encuentra un recurso específico (por ejemplo, un estudiante por ID).
Anota la respuesta HTTP con el estado 404 (NOT_FOUND).
*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
