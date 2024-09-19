package com.example.BODEGASTCCAPI.helpers.validaciones;

import org.springframework.stereotype.Component;

@Component
public class ZonaBodegaValidacion {

    // Validación 1: La capacidad máxima de volumen debe ser mayor a 0
    public boolean validarCapacidadMaximaVolumen(Double capacidadMaximaVolumen) {
        return capacidadMaximaVolumen > 0;
    }

    // Validación 2: La capacidad máxima de peso debe ser mayor a 0
    public boolean validarCapacidadMaximaPeso(Double capacidadMaximaPeso) {
        return capacidadMaximaPeso > 0;
    }

    // Validación 3: El volumen y peso ocupados no deben superar la capacidad máxima
    public boolean validarCapacidadesOcupadas(Double volumenOcupado, Double capacidadMaximaVolumen, Double pesoOcupado, Double capacidadMaximaPeso) {
        return volumenOcupado <= capacidadMaximaVolumen && pesoOcupado <= capacidadMaximaPeso;
    }
}
