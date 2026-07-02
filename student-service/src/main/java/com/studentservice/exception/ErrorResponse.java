package com.studentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/*
Clase ErrorResponse

Estructura de datos para las respuestas de error JSON que devuelve la API.
Ayuda a mantener la consistencia en el formato de errores en todo el microservicio.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String details;
}
