package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.ZonaBodegaValidacion;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaBodegaServicio {

    @Autowired
    IZonaBodegaRepositorio repositorio;

    @Autowired
    ZonaBodegaValidacion validacion;

    // Método para guardar una zona de bodega
    public ZonaBodega almacenarZonaBodega(ZonaBodega zonaBodega) throws Exception {
        try {
            // Validar capacidad máxima de volumen
            if (!validacion.validarCapacidadMaximaVolumen(zonaBodega.getCapacidadMaximaVolumen())) {
                throw new Exception(Mensaje.VOLUMEN_NEGATIVO.getMensaje());
            }

            // Validar capacidad máxima de peso
            if (!validacion.validarCapacidadMaximaPeso(zonaBodega.getCapacidadMaximaPeso())) {
                throw new Exception(Mensaje.PESO_NEGATIVO.getMensaje());
            }

            // Validar que las capacidades ocupadas no excedan las capacidades máximas
            if (!validacion.validarCapacidadesOcupadas(
                    zonaBodega.getCapacidadVolumenOcupado(),
                    zonaBodega.getCapacidadMaximaVolumen(),
                    zonaBodega.getCapacidadPesoOcupado(),
                    zonaBodega.getCapacidadMaximaPeso())) {
                throw new Exception("La capacidad ocupada excede la capacidad máxima permitida.");
            }

            // Guardar la zona de bodega en el repositorio
            return repositorio.save(zonaBodega);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Método para buscar todas las zonas de bodega
    public List<ZonaBodega> buscarTodasZonasBodega() {
        return repositorio.findAll();
    }

    // Método para buscar una zona de bodega por su ID
    public ZonaBodega buscarZonaBodegaPorId(Long idZona) throws Exception {
        return repositorio.findById(idZona)
                .orElseThrow(() -> new Exception("La zona de bodega con el ID " + idZona + " no fue encontrada."));
    }

    // Método para modificar una zona de bodega
    public ZonaBodega modificarZonaBodega(Long idZona, ZonaBodega zonaBodegaActualizada) throws Exception {
        ZonaBodega zonaExistente = buscarZonaBodegaPorId(idZona);

        zonaExistente.setNombreZona(zonaBodegaActualizada.getNombreZona());
        zonaExistente.setCapacidadMaximaVolumen(zonaBodegaActualizada.getCapacidadMaximaVolumen());
        zonaExistente.setCapacidadMaximaPeso(zonaBodegaActualizada.getCapacidadMaximaPeso());
        zonaExistente.setCapacidadVolumenOcupado(zonaBodegaActualizada.getCapacidadVolumenOcupado());
        zonaExistente.setCapacidadPesoOcupado(zonaBodegaActualizada.getCapacidadPesoOcupado());

        return almacenarZonaBodega(zonaExistente);
    }

    // Método para eliminar una zona de bodega por su ID
    public boolean eliminarZonaBodega(Long idZona) throws Exception {
        ZonaBodega zonaBodega = buscarZonaBodegaPorId(idZona);
        repositorio.delete(zonaBodega);
        return true;
    }
}