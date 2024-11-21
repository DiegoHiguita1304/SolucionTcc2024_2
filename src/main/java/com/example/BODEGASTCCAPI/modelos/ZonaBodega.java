package com.example.BODEGASTCCAPI.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "zonas_bodega")
public class ZonaBodega {

    //idZona
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona", nullable = false, unique = true)
    private Long idZona;
    //nombreZona
    @Column(name = "nombre_zona", nullable = false, length = 100)
    private String nombreZona; //Solo se aceptan letras y espacios y maximo 50 caracteres
    //capacidadMaximaVolumen
    @Column(name = "capacidad_maxima_volumen", nullable = false)
    private Double capacidadMaximaVolumen; //Solo se aceptan numeros positivos
    //capacidadMaximaPeso
    @Column(name = "capacidad_maxima_peso", nullable = false)
    private Double capacidadMaximaPeso; //Solo se aceptan numeros positivos
    //capacidadVolumenOcupado
    @Column(name = "capacidad_volumen_ocupado", nullable = false)
    private Double capacidadVolumenOcupado; //Solo se aceptan numeros positivos
    //capacidadPesoOcupado
    @Column(name = "capacidad_peso_ocupado", nullable = false)
    private Double capacidadPesoOcupado; //Solo se aceptan numeros positivos

    @OneToMany(mappedBy = "zonaBodega")
    @JsonManagedReference
    private List<Mercancia> mercancias;

    public ZonaBodega() {
    }

    public ZonaBodega(Long idZona, String nombreZona, Double capacidadMaximaVolumen, Double capacidadMaximaPeso, Double capacidadVolumenOcupado, Double capacidadPesoOcupado) {
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
