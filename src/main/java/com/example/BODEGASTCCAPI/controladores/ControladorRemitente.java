package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.servicios.RemitenteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/soluciontcc/v1/remitente")
@Tag(name="Servicios asociados a la entidad Remitente", description = "\nSe hace CRUD completo a la tabla Remitente permitiendo lectura y escritura de datos")
public class ControladorRemitente {

    @Autowired
    RemitenteServicio remitenteServicio;

    @PostMapping()
    @Operation(
            summary = "Registra un remitente nueva en la base de datos",
            description = "al llevar los datos del modelo remitente se permite un registro exitoso del objeto en BD"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "remitente almecenado con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"nombres\":\"Andres Palacio\",\"departamento\":\"Antioquia\",\"municipio\":\"Medellin\",\"direccion\":\"calle 100 sur 123\",\"metodoPago\":\"Tarjeta credito\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar el remitente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"La dirección no puede superar 50 caracteres\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> almacenarRemitente(@RequestBody Remitente datosRemitente){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.remitenteServicio.almacenarRemitente(datosRemitente));

        } catch (Exception e) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }
}
