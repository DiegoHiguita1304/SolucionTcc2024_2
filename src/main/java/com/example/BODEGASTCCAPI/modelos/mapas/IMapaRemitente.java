package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.modelos.dto.RemitenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaRemitente {
    @Mappings({
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "departamento", target = "departamento"),
            @Mapping(source = "municipio", target = "municipio"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "metodoPago", target = "metodoPago")
    })
    public RemitenteDTO mapearRemitente(Remitente remitente);
    public List<RemitenteDTO> mapearListaRemitentes(List<Remitente> listaRemitente);
}
