package com.mario.proyect.dto;

public class RespuestaValidadorUsuarioDto {

    private boolean valido;
    private String error;

    public boolean isValido() {
        return valido;
    }
    public void setValido(boolean valido) {
        this.valido = valido;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
