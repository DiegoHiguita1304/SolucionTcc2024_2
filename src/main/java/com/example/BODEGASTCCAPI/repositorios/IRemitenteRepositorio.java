package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRemitenteRepositorio extends JpaRepository<Remitente,Long> {
        List<Remitente> findByNombresContainingIgnoreCase(String nombreRemitente);
    }


