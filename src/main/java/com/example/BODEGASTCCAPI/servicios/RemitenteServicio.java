package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.RemitenteValidacion;
import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.modelos.dto.RemitenteDTO;
import com.example.BODEGASTCCAPI.modelos.mapas.IMapaRemitente;
import com.example.BODEGASTCCAPI.repositorios.IRemitenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemitenteServicio {

    @Autowired
    IRemitenteRepositorio remitenteRepositorio;
    @Autowired
    IMapaRemitente mapaRemitente;
    @Autowired
    RemitenteValidacion remitenteValidacion;

    //Guardar
    public Remitente almacenarRemitente(Remitente datosRemitente) throws Exception {
        try {
            if (!this.remitenteValidacion.validarNombre(datosRemitente.getNombres())){
                throw new Exception(Mensaje.NOMBRE_INVALIDO.getMensaje());
            }
            if (!this.remitenteValidacion.validarDepartamento(datosRemitente.getDepartamento())){
                throw new Exception (Mensaje.DEPARTAMENTO_INVALIDO.getMensaje());
            }
            if (!this.remitenteValidacion.validarMunicipio(datosRemitente.getMunicipio())){
                throw new Exception(Mensaje.MUNICIPIO_INVALIDO.getMensaje());
            }
            if (this.remitenteValidacion.validarDireccion(datosRemitente.getDireccion())){
                throw new Exception(Mensaje.DIRECCION_INVALIDA.getMensaje());
            }
            if (!this.remitenteValidacion.validarMetodoPago(datosRemitente.getMetodoPago())){
                throw new Exception(Mensaje.METODO_PAGO_INVALIDO.getMensaje());
            }
            return remitenteRepositorio.save(datosRemitente);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //Buscar todos los remitentes
    public List<RemitenteDTO> buscarTodosRemitentes() throws Exception{
        try{
            return this.mapaRemitente.mapearListaRemitentes(remitenteRepositorio.findAll());
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    //Buscar por id
    public Remitente buscarRemitentePorId(Long id){
        return remitenteRepositorio.findById(id).orElse(null);
    }
    //Buscar por nombre
    public Remitente buscarRemitentePorNombre(String nombre){
        return null;
    }
    //Modificar
    public Remitente modificarRemitente(Long id, Remitente datosNuevosRemitente){
        return null;
    }
    //Eliminar
    public boolean eliminarRemitente(Long id){
        try {
            remitenteRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
