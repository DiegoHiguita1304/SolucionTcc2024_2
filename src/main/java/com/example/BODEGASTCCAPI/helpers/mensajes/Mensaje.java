package com.example.BODEGASTCCAPI.helpers.mensajes;

public enum Mensaje {

    PESO_NEGATIVO("El peso no puede ser negativo"),
    VOLUMEN_NEGATIVO("eL volumen no puede ser negativo"),
    FECHA_INVALIDA("Revisa la fecha ingresada"),
    DEPARTAMENTO_INVALIDO("Verifica el departamento ingresado"),
    METODO_PAGO_INVALIDO("Metodo de pago no valido"),
    NOMBRE_INVALIDO("El nombre que ingresaste es invalido, verificalo")

    ;

    private String mensaje;

    Mensaje() {
    }

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
