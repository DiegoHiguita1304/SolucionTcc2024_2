package com.example.BODEGASTCCAPI.helpers.mensajes;

public enum Mensaje {
    PESO_NEGATIVO("El peso no puede ser menor o igual a 0"),
    VOLUMEN_NEGATIVO("El volumen no puede ser menor o igual a 0"),
    FECHA_INGRESO_INVALIDA("La fecha de ingreso no puede ser mayor a la fecha de salida"),
    NOMBRE_INVALIDO("El nombre no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    DEPARTAMENTO_INVALIDO("El departamento no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    MUNICIPIO_INVALIDO("El municipio no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    DIRECCION_INVALIDA("La direccion no puede tener mas de 50 caracteres"),
    METODO_PAGO_INVALIDO("El metodo de pago no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    NOMBRE_MERCANCIA_INVALIDO("El nombre de la mercancia no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    NOMBRE_BODEGA("El nombre de la mercancia no puede tener mas de 50 caracteres y solo se aceptan letras y espacios"),
    CAPACIDAD_MAXIMA_VOLUMEN("La capacidad maxima de volumen no puede ser menor o igual a 0"),
    CAPACIDAD_MAXIMA_PESO("La capacidad maxima de peso no puede ser menor o igual a 0"),
    CAPACIDAD_VOLUMEN_OCUPADO("La capacidad de volumen ocupado no puede ser menor a 0 ni mayor a la capacidad maxima de volumen"),
    CAPACIDAD_PESO_OCUPADO("La capacidad de peso ocupado no puede ser menor a 0 ni mayor a la capacidad maxima de peso"),
    NOMBRE_BODEGA_DUPLICADO("Esta bodega ya existe en la base de datos"),
    VOLUMEN_DISPONIBLE("El volumen disponible es de %.1f, no se puede ingresar la mercancía porque excede la capacidad."),
    PESO_DISPONIBLE("El peso disponible es de %.1f, no se puede ingresar la mercancía porque excede la capacidad."),
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
