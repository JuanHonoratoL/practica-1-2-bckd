package com.upiiz.practica1_2.heroes.infraestructure.out.persistence.memoria;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;
import com.upiiz.practica1_2.heroes.domain.ports.out.HeroeRepository;

@Component
@Profile("memoria")
public class HeroeRepositoryMemoria implements HeroeRepository {
    private final List<Heroe> heroes = new CopyOnWriteArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    @Override
    public Heroe save(Heroe heroe) {
        Heroe guardado = copiar(heroe, secuencia.getAndIncrement());
        heroes.add(guardado);
        return guardado;
    }

    @Override
    public Heroe findById(Long id) {
        return heroes.stream()
                .filter(heroe -> heroe.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Heroe no encontrado"));
    }

    @Override
    public List<Heroe> findAll() {
        return List.copyOf(heroes);
    }

    @Override
    public Heroe update(Heroe heroe) {
        Heroe existente = findById(heroe.getId());
        Heroe actualizado = copiar(heroe, existente.getId());
        heroes.set(heroes.indexOf(existente), actualizado);
        return actualizado;
    }

    @Override
    public void delete(Long id) {
        heroes.removeIf(heroe -> heroe.getId().equals(id));
    }

    @Override
    public List<Heroe> findByEpoca(String epoca) {
        return heroes.stream()
                .filter(heroe -> contiene(heroe.getEpoca(), epoca))
                .toList();
    }

    @Override
    public List<Heroe> findByMovimiento(String movimiento) {
        return heroes.stream()
                .filter(heroe -> contiene(heroe.getMovimiento(), movimiento))
                .toList();
    }

    @Override
    public List<Heroe> findByEstado(String estado) {
        return heroes.stream()
                .filter(heroe -> Objects.equals(heroe.getEstadoNacimiento(), estado))
                .toList();
    }

    @Override
    public List<Heroe> findByNombreAndApellido(String nombre, String apellido) {
        return heroes.stream()
                .filter(heroe -> Objects.equals(heroe.getNombre(), nombre)
                        && Objects.equals(heroe.getApellido(), apellido))
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return heroes.stream().anyMatch(heroe -> heroe.getId().equals(id));
    }

    private boolean contiene(String campo, String valor) {
        return campo != null && valor != null
                && campo.toLowerCase().contains(valor.toLowerCase());
    }

    private Heroe copiar(Heroe origen, Long id) {
        return new Heroe(id, origen.getNombre(), origen.getApellido(), origen.getFechaNacimiento(),
                origen.getEstadoNacimiento(), origen.getEpoca(), origen.getMovimiento(),
                origen.getDescripcion());
    }
}
