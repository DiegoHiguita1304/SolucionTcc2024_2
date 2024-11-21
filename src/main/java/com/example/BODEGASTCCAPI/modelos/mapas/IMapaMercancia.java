package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaMercancia {
    @Mappings(value = {
            @Mapping(source = "volumen", target = "volumen"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "ciudad", target = "ciudad"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "fechaIngreso", target = "fechaIngreso"),
            @Mapping(source = "nombreZona", target = "nombreZona")
    })
    public MercanciaDTO mapearMercancia(Mercancia mercancia);
    public List<MercanciaDTO> mapearListaMercancias(List<Mercancia> listaMercancia);
}
