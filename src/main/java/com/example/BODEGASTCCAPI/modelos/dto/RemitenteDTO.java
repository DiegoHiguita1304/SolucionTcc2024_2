package com.example.BODEGASTCCAPI.modelos.dto;

public class RemitenteDTO {
    private String nombres;
    private String departamento;
    private String municipio;
    private String direccion;
    private String metodoPago;

    public RemitenteDTO() {
    }

    public RemitenteDTO(String nombres, String departamento, String municipio, String direccion, String metodoPago) {
        this.nombres = nombres;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
