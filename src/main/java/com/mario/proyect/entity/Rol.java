package com.mario.proyect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nombre;

    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Rol [id=" + id + ", nombre=" + nombre + "]";
    }
    
}
