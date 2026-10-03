package com.upiiz.practica1_2.heroes.infraestructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HeroeRepositoryJpa extends JpaRepository<HeroeEntity, Long> {
    // Containing -> LIKE %valor%, para encontrar un heroe aunque su campo
    // epoca/movimiento tenga varios valores separados por coma.
    List<HeroeEntity> findByEpocaContaining(String epoca);

    List<HeroeEntity> findByEstadoNacimiento(String estadoNacimiento);

    List<HeroeEntity> findByMovimientoContaining(String movimiento);

    List<HeroeEntity> findByNombreAndApellido(String nombre, String apellido);

}
