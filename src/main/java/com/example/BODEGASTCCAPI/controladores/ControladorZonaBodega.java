package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.servicios.ZonaBodegaServicio;
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
@RequestMapping("/soluciontcc/v1/zonaBodegas")
@Tag(name="Servicios asociados a la entidad zonaBodega", description = "\nSe hace CRUD completo a la tabla zonaBodega permitiendo lectura y escritura de datos")
public class ControladorZonaBodega {

    @Autowired
    ZonaBodegaServicio zonaBodegaServicio;

    @PostMapping()
    @Operation(
            summary = "Registra una zonaBodega nueva en la base de datos",
            description = "al llevar los datos del modelo zonaBodega se permite un registro exitoso del objeto en BD"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Bodega almecenada con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ZonaBodega.class),
                                    examples = @ExampleObject(value = "{\"nombreZona\":\"Zona Sur\",\"capacidadMaximaVolumen\":\"400\",\"capacidadMaximaPeso\":\"800 \",\"capacidadVolumenOcupado\":\"100\",\"capacidadPesoOcupado\":\"50\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar la bodega",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"La capacidad maxima del volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> almacenarZonaBodega(@RequestBody ZonaBodega datosZonaBodega){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.zonaBodegaServicio.almacenarZonaBodega(datosZonaBodega));
    }

    @GetMapping()
    @Operation(
            summary = "Buscar todos los registros de zonaBodsegas almacenadas en la base de datos",
            description = "Se encuentran todos los registros y se envian al cliente en formato JSON"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "zonBodegas encontradas con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ZonaBodega.class),
                                    examples = @ExampleObject(value = "{\"nombreZona\":\"Zona Sur\",\"capacidadMaximaVolumen\":\"400\",\"capacidadMaximaPeso\":\"800 \",\"capacidadVolumenOcupado\":\"100\",\"capacidadPesoOcupado\":\"50\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al buscar los registros de zonaBodega en la DB",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"La capacidad maxima del volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> buscarTodasZonasBodega(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.zonaBodegaServicio.buscarTodasZonasBodega());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar el registro de zonaBodsega por el id almacenado en la base de datos",
            description = "Se encuentra el registro y se envía al cliente en formato JSON"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "zonBodega encontrado con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ZonaBodega.class),
                                    examples = @ExampleObject(value = "{\"nombreZona\":\"Zona Sur\",\"capacidadMaximaVolumen\":\"400\",\"capacidadMaximaPeso\":\"800 \",\"capacidadVolumenOcupado\":\"100\",\"capacidadPesoOcupado\":\"50\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al buscar el registro de zonaBodega en la DB",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"La capacidad maxima del volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> buscarZonaBodegaPorId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.zonaBodegaServicio.buscarZonaBodegaPorId(id));
    }
}
