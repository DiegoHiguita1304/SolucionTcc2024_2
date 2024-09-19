package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.servicios.ZonaBodegaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/zonasbodega")
public class ControladorZonaBodega {

    @Autowired
    private ZonaBodegaServicio zonaBodegaServicio;

    // Guardar una nueva zona de bodega
    @PostMapping
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
    public ResponseEntity<List<ZonaBodega>> obtenerTodasZonasBodega() {
        List<ZonaBodega> zonas = zonaBodegaServicio.buscarTodasZonasBodega();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(zonas);
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