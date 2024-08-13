package com.mario.proyect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "jugadores")
public class Jugador {

	/* ATRIBUTOS COMUNES A LOS DOS TORNEOS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
	private String nombre;

	@Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
	@Column(name = "primer_apellido")
	private String primerApellido;

	@Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "identificacion")
	private String identificacion;

	@Min(value = 10, message = "El numero de seguro minimo tiene 10 números")
	private String numeroSeguro;

	private boolean portero;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipo_id", nullable = true)
	private Equipo equipo;

	/* ATRIBUTOS UNICOS DEL 3X3 */

	private String tallaCamiseta;
	@Min(value = 0, message = "La edad tiene que ser superior a 0 años")
	private int edad;

	/* ATRIBUTOS UNICOS DEL TORNEO DE SEPTIEMBRE */

	private int goles;

	@Column(name = "tarjetas_azules")
	private int tarjetasAzules;

	@Column(name = "tarjetas_rojas")
	private int tarjetasRojas;

	/*ATRIBUTOS DE CONFIGURACION */
	private boolean activa;

	@Column(name = "ultima_actualizacion")
	private String ultimaActualizacion;

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

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNumeroSeguro() {
		return numeroSeguro;
	}

	public void setNumeroSeguro(String numeroSeguro) {
		this.numeroSeguro = numeroSeguro;
	}

	public boolean isPortero() {
		return portero;
	}

	public void setPortero(boolean portero) {
		this.portero = portero;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getTallaCamiseta() {
		return tallaCamiseta;
	}

	public void setTallaCamiseta(String tallaCamiseta) {
		this.tallaCamiseta = tallaCamiseta;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getTarjetasAzules() {
		return tarjetasAzules;
	}

	public void setTarjetasAzules(int tarjetasAzules) {
		this.tarjetasAzules = tarjetasAzules;
	}

	public int getTarjetasRojas() {
		return tarjetasRojas;
	}

	public void setTarjetasRojas(int tarjetasRojas) {
		this.tarjetasRojas = tarjetasRojas;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activo) {
		this.activa = activo;
	}

	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	@Override
	public String toString() {
		return "Jugador [identificacion=" + identificacion + ", nombre=" + nombre + "]";
	}
}
