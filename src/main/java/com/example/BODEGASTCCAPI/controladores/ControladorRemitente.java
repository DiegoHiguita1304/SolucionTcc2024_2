package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.servicios.RemitenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/remitentes")
public class ControladorRemitente {

    // Inyectar el servicio de remitente
    @Autowired
    RemitenteServicio remitenteServicio;

    // Método para guardar remitente
    @PostMapping
    public ResponseEntity<?> llamadoGuardarRemitente(@RequestBody Remitente datosRemitenteEnviadosCliente) {
        try {
            // Llamar al servicio para almacenar remitente
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(remitenteServicio.almacenarRemitente(datosRemitenteEnviadosCliente));

        } catch (Exception error) {
            // Crear un mensaje de respuesta con el error
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }

    // Método para buscar todos los remitentes
    @GetMapping("/todos")
    public ResponseEntity<List<Remitente>> llamadoBuscarTodosRemitentes() {
        List<Remitente> remitentes = remitenteServicio.buscarTodosRemitentes();
        return ResponseEntity.status(HttpStatus.OK).body(remitentes);
    }

    // Método para buscar remitente por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> llamadoBuscarRemitentePorId(@PathVariable Long id) {
        try {
            Remitente remitente = remitenteServicio.buscarRemitentePorId(id);
            if (remitente != null) {
                return ResponseEntity.status(HttpStatus.OK).body(remitente);
            } else {
                throw new Exception("Remitente no encontrado");
            }
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeRespuesta);
        }
    }

    // Método para buscar remitente por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Remitente>> llamadoBuscarRemitentePorNombre(@PathVariable String nombre) {
        List<Remitente> remitentes = remitenteServicio.buscarRemitentePorNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(remitentes);
    }

    // Método para modificar un remitente
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> llamadoModificarRemitente(@PathVariable Long id, @RequestBody Remitente datosRemitente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(remitenteServicio.modificarRemitente(id, datosRemitente));
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }
    }

    // Método para eliminar remitente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> llamadoEliminarRemitente(@PathVariable Long id) {
        if (remitenteServicio.eliminarRemitente(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Remitente eliminado con éxito");
        } else {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", "Error al eliminar el remitente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }
    }
}