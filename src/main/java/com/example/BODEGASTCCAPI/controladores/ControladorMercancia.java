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
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/soluciontcc/v1/mercancia")
@Tag(name="Servicios asociados a la entidad mercancía", description = "\nSe hace CRUD completo a la tabla Mercancía permitiendo lectura y escritura de datos")
public class ControladorMercancia {
    //Llamar al servicio para
    //Inyectar una dependencia al servicio

    @Autowired
    MercanciaServicio mercanciaServicio;


    @PostMapping()
    @Operation(
            summary = "Registra una mercanciaDTO nueva en la base de datos",
            description = "al llevar los datos del modelo mercanciaDTO se permite un registro exitoso del objeto en BD"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "MercanciaDTO almecenada con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"ciudad\":\"Medellin\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\",\"nombreZona\":\"Zona A\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar la mercancia",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"eL volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> guardarMercanciaDTO(@RequestBody Mercancia datosMercanciaEnviadosCliente){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.mercanciaServicio.almacenarMercanciaDTO(datosMercanciaEnviadosCliente));
    }

    @GetMapping()
    @Operation(
            summary = "Buscar todos los registros de mercancías almacenadas en la base de datos",
            description = "Se encuentran todos los registros y se envian al cliente en formato JSON"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "MercanciasDTO encontradas con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"ciudad\":\"Medellin\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\",\"nombreZona\":\"Zona A\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al buscar los registros de mercancia en la DB",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"eL volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> LlamadoBuscarMercanciaDTO(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mercanciaServicio.buscarTodasMercancias());
    }
}
