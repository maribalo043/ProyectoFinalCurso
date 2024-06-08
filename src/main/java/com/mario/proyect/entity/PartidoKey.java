package com.mario.proyect.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PartidoKey {

    private long idEquipoLocal;
    private long idEquipoVisitante;

	public long getIdEquipoLocal() {
		return idEquipoLocal;
	}
	public void setIdEquipoLocal(long idEquipoLocal) {
		this.idEquipoLocal = idEquipoLocal;
	}
	public long getIdEquipoVisitante() {
		return idEquipoVisitante;
	}
	public void setIdEquipoVisitante(long idEquipoVisitante) {
		this.idEquipoVisitante = idEquipoVisitante;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idEquipoLocal, idEquipoVisitante);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartidoKey other = (PartidoKey) obj;
		return idEquipoLocal == other.idEquipoLocal && idEquipoVisitante == other.idEquipoVisitante;
	}
	@Override
	public String toString() {
		return "PartidoKey [idEquipoLocal=" + idEquipoLocal + ", idEquipoVisitante=" + idEquipoVisitante + "]";
	}
    
	

}
