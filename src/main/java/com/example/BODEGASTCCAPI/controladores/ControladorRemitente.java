package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.modelos.dto.RemitenteDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/remitentes")
@Tag(name="Servicios asociados a la entidad o tabla remitente", description = "\nse hace CRUD completo a la tabla remitente, permitiendo lectura y escritura de datos")
public class ControladorRemitente {

    // Inyectar el servicio de remitente
    @Autowired
    RemitenteServicio remitenteServicio;

    // Método para guardar remitente
    @PostMapping
    @Operation(
            summary = "Registra un remitente nuevo en la base de datos",
            description = "al llevar los datos del modelo remitente se permite un registro exitoso del objeto en Bd"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Remitente almacenado con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"nombre\":\"Diego Higuita\",\"departamento\":\"Antioquia\",\"municipio\":\"Medellin\",\"direccion\":\"calle 34b sur 123\",\"metodo_pago\":\"Efectivo\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar el remitente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"El nombre que ingresaste es invalido, verificalo\"}")
                            )
                    )
            }
    )

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

     // Método para obtener todos los remitentes como DTO
    @GetMapping("/todos")
    public ResponseEntity<List<RemitenteDTO>> llamadoBuscarTodosRemitentes() {
        try {
            List<RemitenteDTO> remitentesDTO = remitenteServicio.buscarTodosLosRemitentes();
            return ResponseEntity.status(HttpStatus.OK).body(remitentesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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