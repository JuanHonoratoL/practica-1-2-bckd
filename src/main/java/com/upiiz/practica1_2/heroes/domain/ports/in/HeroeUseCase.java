package com.upiiz.practica1_2.heroes.domain.ports.in;

import java.util.List;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;

public interface HeroeUseCase {
    Heroe registrar(Heroe heroe);

    List<Heroe> listarTodos();

    Heroe buscarPorId(Long id);

    Heroe actualizar(Heroe heroe);

    void eliminar(Long id);

    // Listados por epoca, movimienot y estado de nacimiento
    List<Heroe> listarPorEpoca(String epoca);

    List<Heroe> listarPorMovimiento(String movimiento);

    List<Heroe> listarPorEstado(String estado);
}
