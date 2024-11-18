package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.modelos.dto.ZonaBodegaDTO;
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

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/zonasbodega")
@Tag(name="Servicios asociados a la entidad o tabla zonaBodega", description = "\nse hace CRUD completo a la tabla zonaBodega, permitiendo lectura y escritura de datos")
public class ControladorZonaBodega {

    @Autowired
    private ZonaBodegaServicio zonaBodegaServicio;

    // Guardar una nueva zona de bodega
    @PostMapping
    @Operation(
            summary = "Registra una zonaBodega nueva en la base de datos",
            description = "al llevar los datos del modelo zonaBodega se permite un registro exitoso del objeto en Bd"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "ZonaBodega almacenada con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"idZona\":\"123\",\"nombre_zona\":\"A\",\"capacidad_maxima_volumen\":\"2.500\",\"capacidad_maxima_peso\":\"5.000\",\"capacidad_volumen_ocupado\":\"1.000\",\"capacidad_peso_ocupado\":\"4.000\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar la ZonaBodega",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"Zona Bodega no encontrada\"}")
                            )
                    )
            }
    )

    public ResponseEntity<?> guardarZonaBodega(@RequestBody ZonaBodega zonaBodega) {
        try {
            ZonaBodega zonaGuardada = zonaBodegaServicio.almacenarZonaBodega(zonaBodega);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(zonaGuardada);
        } catch (Exception error) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuesta);
        }
    }

    // Obtener todas las zonas de bodega
    @GetMapping("/todas")
    public ResponseEntity<List<ZonaBodegaDTO>> obtenerTodasLasZonasBodega() {
        try {
            List<ZonaBodegaDTO> zonasDTO = zonaBodegaServicio.buscarTodasLasZonasBodega();
            return ResponseEntity.status(HttpStatus.OK).body(zonasDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una zona de bodega por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerZonaBodegaPorId(@PathVariable("id") Long idZona) {
        try {
            ZonaBodega zonaBodega = zonaBodegaServicio.buscarZonaBodegaPorId(idZona);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(zonaBodega);
        } catch (Exception error) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(respuesta);
        }
    }

    // Modificar una zona de bodega
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarZonaBodega(@PathVariable("id") Long idZona, @RequestBody ZonaBodega zonaBodegaActualizada) {
        try {
            ZonaBodega zonaModificada = zonaBodegaServicio.modificarZonaBodega(idZona, zonaBodegaActualizada);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(zonaModificada);
        } catch (Exception error) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuesta);
        }
    }

    // Eliminar una zona de bodega
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarZonaBodega(@PathVariable("id") Long idZona) {
        try {
            boolean eliminado = zonaBodegaServicio.eliminarZonaBodega(idZona);
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Zona de bodega eliminada correctamente");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(respuesta);
        } catch (Exception error) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(respuesta);
        }
    }
}