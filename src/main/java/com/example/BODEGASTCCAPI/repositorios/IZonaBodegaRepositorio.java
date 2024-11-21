package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IZonaBodegaRepositorio extends JpaRepository<ZonaBodega, Long> {
    public Optional<ZonaBodega> findByNombreZona(String nombreZona);
}
