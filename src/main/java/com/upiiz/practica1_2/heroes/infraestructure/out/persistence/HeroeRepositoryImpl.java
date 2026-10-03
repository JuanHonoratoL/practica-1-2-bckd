package com.upiiz.practica1_2.heroes.infraestructure.out.persistence;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;
import com.upiiz.practica1_2.heroes.domain.ports.out.HeroeRepository;

@Component
@Profile("!memoria")
public class HeroeRepositoryImpl implements HeroeRepository {
    private final HeroeRepositoryJpa heroeRepositoryJpa;

    public HeroeRepositoryImpl(HeroeRepositoryJpa heroeRepositoryJpa) {
        this.heroeRepositoryJpa = heroeRepositoryJpa;
    }

    @Override
    public void delete(Long id) {
        heroeRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Heroe> findAll() {
        return heroeRepositoryJpa.findAll().stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Heroe> findByEpoca(String epoca) {
        return heroeRepositoryJpa.findByEpocaContaining(epoca).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Heroe> findByEstado(String estado) {
        return heroeRepositoryJpa.findByEstadoNacimiento(estado).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public List<Heroe> findByNombreAndApellido(String nombre, String apellido) {
        return heroeRepositoryJpa.findByNombreAndApellido(nombre, apellido).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return heroeRepositoryJpa.existsById(id);
    }

    @Override
    public Heroe findById(Long id) {
        HeroeEntity heroeEncontrado = heroeRepositoryJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Heroe no encontrado"));

        return new Heroe(heroeEncontrado.getId(), heroeEncontrado.getNombre(),
                heroeEncontrado.getApellido(), heroeEncontrado.getFechaNacimiento(),
                heroeEncontrado.getEstadoNacimiento(), heroeEncontrado.getEpoca(),
                heroeEncontrado.getMovimiento(), heroeEncontrado.getDescripcion());
    }

    @Override
    public List<Heroe> findByMovimiento(String movimiento) {
        return heroeRepositoryJpa.findByMovimientoContaining(movimiento).stream()
                .map(this::toDomain).toList();
    }

    @Override
    public Heroe save(Heroe heroe) {
        HeroeEntity heroeEntity = new HeroeEntity(null, heroe.getNombre(),
                heroe.getApellido(), heroe.getFechaNacimiento(),
                heroe.getEstadoNacimiento(), heroe.getEpoca(),
                heroe.getMovimiento(), heroe.getDescripcion());

        HeroeEntity heroeGuardado = heroeRepositoryJpa.save(heroeEntity);
        return new Heroe(heroeGuardado.getId(), heroeGuardado.getNombre(),
                heroeGuardado.getApellido(), heroeGuardado.getFechaNacimiento(),
                heroeGuardado.getEstadoNacimiento(), heroeGuardado.getEpoca(),
                heroeGuardado.getMovimiento(), heroeGuardado.getDescripcion());
    }

    @Override
    public Heroe update(Heroe heroe) {
        HeroeEntity heroeEntity = heroeRepositoryJpa.findById(heroe.getId())
                .orElseThrow(() -> new RuntimeException("Heroe no encontrada"));

        heroeEntity.setNombre(heroe.getNombre());
        heroeEntity.setApellido(heroe.getApellido());
        heroeEntity.setFechaNacimiento(heroe.getFechaNacimiento());
        heroeEntity.setEstadoNacimiento(heroe.getEstadoNacimiento());
        heroeEntity.setEpoca(heroe.getEpoca());
        heroeEntity.setMovimiento(heroe.getMovimiento());
        heroeEntity.setDescripcion(heroe.getDescripcion());

        return toDomain(heroeRepositoryJpa.save(heroeEntity));
    }

    private Heroe toDomain(HeroeEntity entity) {
        return new Heroe(entity.getId(), entity.getNombre(),
                entity.getApellido(), entity.getFechaNacimiento(),
                entity.getEstadoNacimiento(), entity.getEpoca(),
                entity.getMovimiento(), entity.getDescripcion());
    }
}
