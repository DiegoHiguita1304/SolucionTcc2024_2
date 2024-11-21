package com.example.BODEGASTCCAPI.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mercancias")
public class Mercancia {

    //iup
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID iup;
    //volumen
    @Column(name = "volumen", nullable = false, unique = false)
    private Double volumen; //Solo se aceptan numeros positivos
    //peso
    private Double peso; //Solo se aceptan numeros positivos
    //nombre
    @Column(name = "nombre_mercancia", nullable = false, unique = false, length = 50)
    private String nombre; //Maximo 50 caracteres y solo se aceptan letras y espacios
    //tipoDestinatario
    @Column(name = "tipo_destinatario", nullable = false,  length = 50)
    private String tipoDestinatario;
    //nombreDestinatario
    @Column(name = "nombre_destinatario", nullable = false,  length = 50)
    private String nombreDestinatario;
    //UbicacionDestino(depto/ciudad/direccion)
    private String departamento;
    private String ciudad;
    private String direccion;
    //fechaIngreso
    private LocalDate fechaIngreso; //Fecha ingreso no puede ser posterior a la fecha salida
    //fechaSalida
    private LocalDate fechaSalida;
    private  String nombreZona;


//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_zona_bodega", referencedColumnName = "id_zona")
    @JsonBackReference
    private ZonaBodega zonaBodega;

    public Mercancia() {
    }

    public Mercancia(UUID iup, Double volumen, Double peso, String nombre, String tipoDestinatario, String nombreDestinatario, String departamento, String ciudad, String direccion, LocalDate fechaIngreso, LocalDate fechaSalida, String nombreZona) {
        this.iup = iup;
        this.volumen = volumen;
        this.peso = peso;
        this.nombre = nombre;
        this.tipoDestinatario = tipoDestinatario;
        this.nombreDestinatario = nombreDestinatario;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.nombreZona = nombreZona;
    }

    public UUID getIup() {
        return iup;
    }

    public void setIup(UUID iup) {
        this.iup = iup;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public ZonaBodega getZonaBodega() {
        return  null;

    }
}
