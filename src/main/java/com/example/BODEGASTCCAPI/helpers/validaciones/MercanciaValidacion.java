package com.example.BODEGASTCCAPI.helpers.validaciones;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MercanciaValidacion {

    public boolean validarVolumen(Double mercanciaVolumen){
        return mercanciaVolumen > 0;
    }

    public boolean validarPeso(Double mercanciaPeso){
        return mercanciaPeso > 0;
    }

    public boolean validarNombre(String mercanciaNombre){
        if (mercanciaNombre.length() > 50){
            return false;
        }
        else return(mercanciaNombre.matches("^[a-zA-Z\\s]+$"));
    }

    public boolean validarFechaIngreso(LocalDate fechaIngreso, LocalDate fechaSalida){
        return fechaIngreso.isBefore(fechaSalida);
    }
}
