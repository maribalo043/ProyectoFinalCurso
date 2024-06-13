package com.mario.proyect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "jugadores")
public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @Pattern(regexp = "\\d{8}[A-Z]", message = "Dale formato de DNI, 8 números y 1 letra mayúscula")
    private String dni;
	@Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
    private String nombre;
	@Min(value = 10,message="El numero de seguro minimo tiene 10 números")
    private String numeroSeguro;
    private String tallaCamiseta;
    private boolean portero;
	@Min(value = 0,message="La edad tiene que ser superior a 0 años")
	private int edad;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipo_id", nullable = true)
    private Equipo equipo;

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroSeguro() {
		return numeroSeguro;
	}
	public void setNumeroSeguro(String numeroSeguro) {
		this.numeroSeguro = numeroSeguro;
	}
	public String getTallaCamiseta() {
		return tallaCamiseta;
	}
	public void setTallaCamiseta(String tallaCamiseta) {
		this.tallaCamiseta = tallaCamiseta;
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
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Jugador [dni=" + dni + ", nombre=" + nombre + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
