package com.example.BODEGASTCCAPI.helpers.validaciones;

import org.springframework.stereotype.Component;

@Component
public class RemitenteValidacion {
    public boolean validarNombre(String remitenteNombre){
        if (remitenteNombre.length() > 50){
            return false;
        }
        else return(remitenteNombre.matches("^[a-zA-Z\\s]+$"));
    }

    public boolean validarDepartamento(String remitenteDepartamento){
        if (remitenteDepartamento.length() > 50){
            return false;
        }
        else return(remitenteDepartamento.matches("^[a-zA-Z\\s]+$"));
    }

    public boolean validarMunicipio(String remitenteMunicipio){
        if (remitenteMunicipio.length() > 50){
            return false;
        }
        else return(remitenteMunicipio.matches("^[a-zA-Z\\s]+$"));
    }

    public boolean validarDireccion(String remitenteDireccion){
        return remitenteDireccion.length() > 50;
    }

    public boolean validarMetodoPago(String remitenteMetodoPago){
        if (remitenteMetodoPago.length() > 50){
            return false;
        }
        else return(remitenteMetodoPago.matches("^[a-zA-Z\\s]+$"));
    }

}
