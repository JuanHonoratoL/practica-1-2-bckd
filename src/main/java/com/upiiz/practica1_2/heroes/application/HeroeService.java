package com.upiiz.practica1_2.heroes.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;
import com.upiiz.practica1_2.heroes.domain.ports.in.HeroeUseCase;
import com.upiiz.practica1_2.heroes.domain.ports.out.HeroeRepository;

public class HeroeService implements HeroeUseCase {
    private final HeroeRepository heroeRepository;

    public HeroeService(HeroeRepository heroeRepository) {
        this.heroeRepository = heroeRepository;
    }

    @Override
    public Heroe actualizar(Heroe heroe) {
        if (!heroeRepository.existsById(heroe.getId())) {
            throw new NoSuchElementException("El héroe no existe.");
        }
        heroe.validar();
        verificarDuplicado(heroe);
        return heroeRepository.update(heroe);
    }

    @Override
    public Heroe buscarPorId(Long id) {
        return heroeRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        if (!heroeRepository.existsById(id)) {
            throw new NoSuchElementException("El héroe no existe.");
        }
        heroeRepository.delete(id);
    }

    @Override
    public List<Heroe> listarPorEpoca(String epoca) {
        return heroeRepository.findByEpoca(epoca);
    }

    @Override
    public List<Heroe> listarPorEstado(String estado) {
        return heroeRepository.findByEstado(estado);
    }

    @Override
    public List<Heroe> listarPorMovimiento(String movimiento) {
        return heroeRepository.findByMovimiento(movimiento);
    }

    @Override
    public List<Heroe> listarTodos() {
        return heroeRepository.findAll();
    }

    @Override
    public Heroe registrar(Heroe heroe) {
        heroe.validar();
        verificarDuplicado(heroe);
        return heroeRepository.save(heroe);
    }

    // Al actualizar, el propio héroe no cuenta como duplicado
    private void verificarDuplicado(Heroe heroe) {
        boolean duplicado = heroeRepository.findByNombreAndApellido(heroe.getNombre(), heroe.getApellido()).stream()
                .anyMatch(existente -> !Objects.equals(existente.getId(), heroe.getId()));
        if (duplicado) {
            throw new IllegalArgumentException("Ya existe un héroe con ese nombre y apellido.");
        }
    }
}
