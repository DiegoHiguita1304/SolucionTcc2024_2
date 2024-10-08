package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.servicios.MercanciaServicio;
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
@RequestMapping("/soluciontcc/v1/mercancias")
@Tag(name="Servicios asociados a la entidad o tabla mercancias", description = "\nse hace CRUD completo a la tabla mercancias, permitiendo lectura y escritura de datos")
public class ControladorMercancia {

    @Autowired
    MercanciaServicio mercanciaServicio;

    @PostMapping
    @Operation(
            summary = "Registra una mercancia nueva en la base de datos",
            description = "al llevar los datos del modelomercancia se permite un registro exitoso del objeto en Bd"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Mercancia almacenada con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera lG\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar la mercancia",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"El volumen no puede ser negativo\"}")
                            )
                    )
            }
    )

    public ResponseEntity<?> LlamadoGuardarMercancia(@RequestBody Mercancia datosMercanciaEnviadosCliente) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mercanciaServicio.almacenarMercancia(datosMercanciaEnviadosCliente));
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }

}
