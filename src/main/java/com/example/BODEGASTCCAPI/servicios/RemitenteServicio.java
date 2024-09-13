package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.RemitenteValidacion;
import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.repositorios.IRemitenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemitenteServicio {

    @Autowired
    IRemitenteRepositorio repositorio;

    @Autowired
    RemitenteValidacion validacion;

    // Guardar remitente
    public Remitente almacenarRemitente(Remitente datosRemitente) throws Exception {
        try {
            // Aplicar validaciones
            if (!validacion.validarNombres(datosRemitente.getNombres())) {
                throw new Exception(Mensaje.NOMBRE_INVALIDO.getMensaje());
            }

            if (!validacion.validarDepartamento(datosRemitente.getDepartamento())) {
                throw new Exception(Mensaje.DEPARTAMENTO_INVALIDO.getMensaje());
            }

            if (!validacion.validarMetodoPago(datosRemitente.getMetodoPago())) {
                throw new Exception(Mensaje.METODO_PAGO_INVALIDO.getMensaje());
            }

            // Guardar en el repositorio si las validaciones son correctas
            return repositorio.save(datosRemitente);

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Buscar todos los remitentes
    public List<Remitente> buscarTodosRemitentes() {
        return repositorio.findAll();
    }

    // Buscar remitente por ID
    public Remitente buscarRemitentePorId(Long idRemitente) {
        return repositorio.findById(idRemitente).orElse(null);
    }

    // Buscar remitente por nombre
    public List<Remitente> buscarRemitentePorNombre(String nombreRemitente) {
        return repositorio.findByNombresContainingIgnoreCase(nombreRemitente);
    }

    // Modificar remitente
    public Remitente modificarRemitente(Long idRemitente, Remitente datosRemitente) throws Exception {
        try {
            Remitente remitenteExistente = repositorio.findById(idRemitente).orElseThrow(() -> new Exception("Remitente no encontrado"));

            remitenteExistente.setNombres(datosRemitente.getNombres());
            remitenteExistente.setDepartamento(datosRemitente.getDepartamento());
            remitenteExistente.setMunicipio(datosRemitente.getMunicipio());
            remitenteExistente.setDireccion(datosRemitente.getDireccion());
            remitenteExistente.setMetodoPago(datosRemitente.getMetodoPago());

            return repositorio.save(remitenteExistente);

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar remitente
    public boolean eliminarRemitente(Long idRemitente) {
        try {
            repositorio.deleteById(idRemitente);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}