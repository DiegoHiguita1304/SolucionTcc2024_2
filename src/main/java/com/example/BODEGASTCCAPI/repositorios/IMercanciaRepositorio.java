package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface IMercanciaRepositorio extends JpaRepository<Mercancia, UUID> {

}
