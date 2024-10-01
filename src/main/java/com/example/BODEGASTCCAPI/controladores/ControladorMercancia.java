package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.servicios.MercanciaServicio;
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
public class ControladorMercancia {

    @Autowired
    MercanciaServicio mercanciaServicio;

    @PostMapping
    public ResponseEntity<?> LlamadoGuardarMercancia(@RequestBody Mercancia datosMercanciaEnviadosCliente) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.mercanciaServicio.almacenarMercancia(datosMercanciaEnviadosCliente));
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }

    /*@PostMapping
    public ResponseEntity<?> LlamadoGuardarMercanciaDTO(@RequestBody Mercancia datosMercanciaEnviadosCliente) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.mercanciaServicio.almacenarMercanciaDTO(datosMercanciaEnviadosCliente));
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }*/
}
