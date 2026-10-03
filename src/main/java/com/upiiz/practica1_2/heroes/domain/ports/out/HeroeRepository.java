package com.upiiz.practica1_2.heroes.domain.ports.out;

import java.util.List;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;

public interface HeroeRepository {
    Heroe save(Heroe heroe);

    Heroe findById(Long id);

    List<Heroe> findAll();

    Heroe update(Heroe heroe);

    void delete(Long id);

    List<Heroe> findByEpoca(String epoca);

    List<Heroe> findByMovimiento(String movimiento);

    List<Heroe> findByEstado(String estado);

    List<Heroe> findByNombreAndApellido(String nombre, String apellido);

    boolean existsById(Long id);
}
