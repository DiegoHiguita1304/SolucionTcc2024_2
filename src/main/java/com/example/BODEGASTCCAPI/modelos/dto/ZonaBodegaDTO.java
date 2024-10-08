package com.example.BODEGASTCCAPI.modelos.dto;

public class ZonaBodegaDTO {

    private Long idZona;
    private String nombreZona;
    private Double capacidadMaximaVolumen;
    private Double capacidadMaximaPeso;
    private Double capacidadVolumenOcupado;
    private Double capacidadPesoOcupado;

    public ZonaBodegaDTO() {
    }

    public ZonaBodegaDTO(Long idZona, String nombreZona, Double capacidadMaximaVolumen, Double capacidadMaximaPeso, Double capacidadVolumenOcupado, Double capacidadPesoOcupado) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
        this.capacidadMaximaPeso = capacidadMaximaPeso;
        this.capacidadVolumenOcupado = capacidadVolumenOcupado;
        this.capacidadPesoOcupado = capacidadPesoOcupado;
    }

    public Long getIdZona() {
        return idZona;
    }

    public void setIdZona(Long idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public Double getCapacidadMaximaVolumen() {
        return capacidadMaximaVolumen;
    }

    public void setCapacidadMaximaVolumen(Double capacidadMaximaVolumen) {
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
    }

    public Double getCapacidadMaximaPeso() {
        return capacidadMaximaPeso;
    }

    public void setCapacidadMaximaPeso(Double capacidadMaximaPeso) {
        this.capacidadMaximaPeso = capacidadMaximaPeso;
    }

    public Double getCapacidadVolumenOcupado() {
        return capacidadVolumenOcupado;
    }

    public void setCapacidadVolumenOcupado(Double capacidadVolumenOcupado) {
        this.capacidadVolumenOcupado = capacidadVolumenOcupado;
    }

    public Double getCapacidadPesoOcupado() {
        return capacidadPesoOcupado;
    }

    public void setCapacidadPesoOcupado(Double capacidadPesoOcupado) {
        this.capacidadPesoOcupado = capacidadPesoOcupado;
    }
}
