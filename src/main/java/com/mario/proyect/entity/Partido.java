package com.mario.proyect.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "partidos")
public class Partido {

    @EmbeddedId 
    private PartidoKey id; 
    
    @ManyToOne 
    @MapsId("idEquipoLocal")
    @JoinColumn(name = "equipo_Local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @MapsId("idEquipoVisitante")
    @JoinColumn(name = "equipo_Visitante_id")
    private Equipo equipoVisitante;
    
    @Min(value = 0, message = "Debe ser un número entero no negativo")
    private int golesLocal;
    
    @Min(value = 0, message = "Debe ser un número entero no negativo")
    private int golesVisitante;
    
    private String pista;
    private String hora;
    private boolean finalizado;

    

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

    public PartidoKey getId() {
        return id;
    }

    public void setId(PartidoKey id) {
        this.id = id;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
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

    @Override
    public String toString() {
        return "Partido [id=" + id + ", " + golesLocal + " : " + golesVisitante  + ", Local: " + equipoLocal + ", Visitante: " + equipoVisitante + ", pista: " + pista + "]";
    }
}
