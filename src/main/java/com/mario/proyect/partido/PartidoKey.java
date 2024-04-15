package com.mario.proyect.partido;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PartidoKey {

    private long IdEquipoLocal;
    private long IdEquipoVisitante;
    
	public long getIdEquipoLocal() {
		return IdEquipoLocal;
	}
	public void setIdEquipoLocal(long idEquipoLocal) {
		IdEquipoLocal = idEquipoLocal;
	}
	public long getIdEquipoVisitante() {
		return IdEquipoVisitante;
	}
	public void setIdEquipoVisitante(long idEquipoVisitante) {
		IdEquipoVisitante = idEquipoVisitante;
	}
	@Override
	public int hashCode() {
		return Objects.hash(IdEquipoLocal, IdEquipoVisitante);
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
		return IdEquipoLocal == other.IdEquipoLocal && IdEquipoVisitante == other.IdEquipoVisitante;
	}

}
