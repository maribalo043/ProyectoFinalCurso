package com.mario.proyect.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	/* ATRIBUTOS COMUNES PARA LOS DOS TORNEOS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Equipo> equipos;
	/* ATRIBUTOS DEL 3X3 */
	@Column(name = "url_img")
	private String urlImg;

	@Column(name = "max_equipos")
	private int maxEquipos;

	/* ATRIBUTOS DE CONFIGURACIÓN */
	private boolean activa;

	@Column(name = "ultima_actualizacion")
	private String ultimaActualizacion;

	@Column(name = "torneo_sara")
	private boolean torneoSara;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public int getMaxEquipos() {
		return maxEquipos;
	}

	public void setMaxEquipos(int maxEquipos) {
		this.maxEquipos = maxEquipos;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	public boolean isTorneoSara() {
		return torneoSara;
	}

	public void setTorneoSara(boolean torneoSara) {
		this.torneoSara = torneoSara;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}

}
