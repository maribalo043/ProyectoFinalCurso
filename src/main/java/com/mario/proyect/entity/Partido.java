package com.mario.proyect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipo_Local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_Visitante_id")
    private Equipo equipoVisitante;

    @Min(value = 0, message = "Debe ser un número entero no negativo")
    private int golesLocal;

    @Min(value = 0, message = "Debe ser un número entero no negativo")
    private int golesVisitante;

    private int faltasLocal;

    private int faltasVisitante;

    private int periodo;

    private String pista;
    private String hora;
    private boolean finalizado;

    private String nombre;

    // Getters y Setters

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public int getFaltasLocal() {
        return faltasLocal;
    }

    public void setFaltasLocal(int faltasLocal) {
        this.faltasLocal = faltasLocal;
    }

    public int getFaltasVisitante() {
        return faltasVisitante;
    }

    public void setFaltasVisitante(int faltasVisitante) {
        this.faltasVisitante = faltasVisitante;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Partido [id=" + id + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante
                + ", equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", pista=" + pista 
                + "]";
    }
}
