package com.mario.proyect.dto;

public class MensajeDto {

    private String nombre;
    private String correo;
    private String mensaje;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    @Override
    public String toString() {
        return "MensajeDto [nombre=" + nombre + ", correo=" + correo + ", mensaje=" + mensaje + "]";
    }
}
