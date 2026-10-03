package com.upiiz.practica1_2.heroes.infraestructure.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upiiz.practica1_2.heroes.domain.models.Heroe;
import com.upiiz.practica1_2.heroes.domain.ports.in.HeroeUseCase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/heroes")
public class HeroeController {
    private final HeroeUseCase heroeUseCase;

    public HeroeController(HeroeUseCase heroeUseCase) {
        this.heroeUseCase = heroeUseCase;
    }

    @PostMapping
    public Heroe createHeroe(@RequestBody final Heroe heroe) {
        return heroeUseCase.registrar(heroe);
    }

    @GetMapping
    public List<Heroe> getHeroes() {
        return heroeUseCase.listarTodos();
    }

    @GetMapping("/{id}")
    public Heroe getHeroe(@PathVariable Long id) {
        return heroeUseCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Heroe updateHeroe(@PathVariable Long id, @RequestBody Heroe heroe) {
        heroe.setId(id);
        return heroeUseCase.actualizar(heroe);
    }

    @DeleteMapping("/{id}")
    public void deleteHeroe(@PathVariable Long id) {
        heroeUseCase.eliminar(id);
    }

    @GetMapping("/epoca/{epoca}")
    public List<Heroe> getHeroesPorEpoca(@PathVariable String epoca) {
        return heroeUseCase.listarPorEpoca(epoca);
    }

    @GetMapping("/movimiento/{movimiento}")
    public List<Heroe> getHeroePorMovimiento(@PathVariable String movimiento) {
        return heroeUseCase.listarPorMovimiento(movimiento);
    }

    @GetMapping("/estado/{estado}")
    public List<Heroe> getHeroesPorEstado(@PathVariable String estado) {
        return heroeUseCase.listarPorEstado(estado);
    }
}
