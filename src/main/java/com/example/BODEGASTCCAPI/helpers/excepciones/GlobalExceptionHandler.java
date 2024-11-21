package com.example.BODEGASTCCAPI.helpers.excepciones;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> manejarValidationException(ValidationException e) {
        return construirRespuestaError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarExcepcionGeneral(Exception e) {
        return construirRespuestaError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado en el servidor");
    }

    private ResponseEntity<?> construirRespuestaError(HttpStatus status, String mensaje) {
        HashMap<String, Object> mensajeRespuesta = new HashMap<>();
        mensajeRespuesta.put("mensaje", mensaje);
        return ResponseEntity.status(status).body(mensajeRespuesta);
    }
}
