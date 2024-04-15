package com.mario.proyect.partido;

import com.mario.proyect.equipo.Equipo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "partidos")
public class Partido {

   	@EmbeddedId 
	private PartidoKey id; 
	
	@ManyToOne 
	@MapsId("IdEquipoLocal")
	@JoinColumn(name = "equipo_Local")
	private Equipo equipoLocal;

	@ManyToOne
	@MapsId("IdEquipoVisitante")
	@JoinColumn(name = "equipo_Visitante")
	private Equipo equipoVisitante;
	
	@Size(min = 0,max=100, message = "Debe ser un número entero no negativo")
    private String golesLocal;
	@Size(min = 0,max=100, message = "Debe ser un número entero no negativo")
	private String golesVisitante;
	private String pista;

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
	
	public String getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(String golesLocal) {
		this.golesLocal = golesLocal;
	}

	public String getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(String golesVisitante) {
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
		return "Partido [id=" + id + "," + golesLocal + " : " + golesVisitante  +" Local: "+equipoLocal +" Visitante: "+ equipoVisitante + " pista: "+ pista +"]";
	}
}
