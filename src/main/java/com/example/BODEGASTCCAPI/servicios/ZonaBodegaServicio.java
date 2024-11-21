package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.ZonaBodegaValidacion;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ZonaBodegaServicio {

    @Autowired
    IZonaBodegaRepositorio zonaBodegaRepositorio;
    @Autowired
    ZonaBodegaValidacion zonaBodegaValidacion;


    //Guardar
    public ZonaBodega almacenarZonaBodega(ZonaBodega datosZonaBodega) throws ValidationException {

        if (datosZonaBodega.getCapacidadPesoOcupado()== null){
            datosZonaBodega.setCapacidadPesoOcupado(0.0);
        }
        if (datosZonaBodega.getCapacidadVolumenOcupado()== null){
            datosZonaBodega.setCapacidadVolumenOcupado(0.0);
        }
        if (Objects.equals(datosZonaBodega.getNombreZona().toLowerCase(), buscarZonaBodegaPorNombre(datosZonaBodega.getNombreZona()).map(ZonaBodega::getNombreZona).orElse("").toLowerCase())){
            throw new ValidationException(Mensaje.NOMBRE_BODEGA_DUPLICADO.getMensaje());
        }
        if (!this.zonaBodegaValidacion.validarNombre(datosZonaBodega.getNombreZona())){
            throw new ValidationException(Mensaje.NOMBRE_BODEGA.getMensaje());
        }
        if (!this.zonaBodegaValidacion.validarCapacidadMaximaVolumen(datosZonaBodega.getCapacidadMaximaVolumen())){
            throw new ValidationException(Mensaje.CAPACIDAD_MAXIMA_VOLUMEN.getMensaje());
        }
        if (!this.zonaBodegaValidacion.validarCapacidadMaximaPeso(datosZonaBodega.getCapacidadMaximaPeso())){
            throw new ValidationException(Mensaje.CAPACIDAD_MAXIMA_PESO.getMensaje());
        }
        if (!this.zonaBodegaValidacion.validarCapacidadVolumenOcupado(datosZonaBodega.getCapacidadVolumenOcupado(), datosZonaBodega.getCapacidadMaximaVolumen())){
            throw new ValidationException(Mensaje.CAPACIDAD_VOLUMEN_OCUPADO.getMensaje());
        }
        if (!this.zonaBodegaValidacion.validarCapacidadPesoOcupado(datosZonaBodega.getCapacidadPesoOcupado(), datosZonaBodega.getCapacidadMaximaPeso())){
            throw new ValidationException(Mensaje.CAPACIDAD_PESO_OCUPADO.getMensaje());
        }
        return zonaBodegaRepositorio.save(datosZonaBodega);

    }
    //Buscar todas las zonas de bodega
    public List<ZonaBodega> buscarTodasZonasBodega(){
        return zonaBodegaRepositorio.findAll();
    }

    //Buscar por id
    public ZonaBodega buscarZonaBodegaPorId (Long id) throws ValidationException{
        if(id != null){
            return zonaBodegaRepositorio.findById(id).orElse(null);
        }else {
        throw new ValidationException("El id no puede ser nulo");
        }
}

    //Modificar
    public ZonaBodega modificarZonaBodega(Long id, ZonaBodega datosNuevosZonaBodega){
        return null;
    }

    //Eliminar
    public boolean eliminarZonaBodega(Long id){
        try {
            zonaBodegaRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Buscar por nombre
    public Optional<ZonaBodega> buscarZonaBodegaPorNombre(String nombre){
        return zonaBodegaRepositorio.findByNombreZona(nombre);

    }

    public Double calcularvolumenDisponible(Long idZona){
        return buscarZonaBodegaPorId(idZona).getCapacidadMaximaVolumen() - buscarZonaBodegaPorId(idZona).getCapacidadVolumenOcupado();
    }

    public Double calcularPesoDisponible(Long idZona){
        return buscarZonaBodegaPorId(idZona).getCapacidadMaximaPeso() - buscarZonaBodegaPorId(idZona).getCapacidadPesoOcupado();
    }

}
