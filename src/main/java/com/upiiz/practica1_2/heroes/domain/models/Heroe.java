package com.upiiz.practica1_2.heroes.domain.models;

import java.time.LocalDate;

public class Heroe {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String estadoNacimiento;
    private String epoca;
    private String movimiento;
    private String descripcion;

    public Heroe() {

    }

    public Heroe(Long id, String nombre, String apellido, LocalDate fechaNacimiento, String estadoNacimiento, String epoca,
            String movimiento, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoNacimiento = estadoNacimiento;
        this.epoca = epoca;
        this.movimiento = movimiento;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoNacimiento() {
        return estadoNacimiento;
    }

    public void setEstadoNacimiento(String estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void validar() {
        if (estaVacio(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (estaVacio(apellido)) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }
        if (estaVacio(estadoNacimiento)) {
            throw new IllegalArgumentException("El estado de nacimiento es obligatorio.");
        }
        if (estaVacio(epoca)) {
            throw new IllegalArgumentException("La época histórica es obligatoria.");
        }
        if (estaVacio(movimiento)) {
            throw new IllegalArgumentException("El movimiento es obligatorio.");
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}
