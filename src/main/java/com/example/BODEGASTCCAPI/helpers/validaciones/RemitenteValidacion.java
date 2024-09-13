package com.example.BODEGASTCCAPI.helpers.validaciones;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import org.springframework.stereotype.Component;

@Component
public class RemitenteValidacion {

    // Validar que los nombres solo contengan letras y espacios, y no estén vacíos
    public boolean validarNombres(String nombres) {
        if (nombres == null || nombres.isEmpty()) {
            return false;
        }
        return nombres.matches("^[a-zA-Z\\s]+$");
    }

    // Validar que el departamento no esté vacío
    public boolean validarDepartamento(String departamento) {
        return departamento != null && !departamento.isEmpty();
    }

    // Validar que el método de pago sea uno permitido (por ejemplo: "Tarjeta", "Efectivo", "Transferencia")
    public boolean validarMetodoPago(String metodoPago) {
        return metodoPago.equals("Tarjeta") || metodoPago.equals("Efectivo") || metodoPago.equals("Transferencia");
    }
}