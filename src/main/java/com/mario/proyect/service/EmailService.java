package com.mario.proyect.service;

import com.mario.proyect.dto.MensajeDto;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendMailInscripcion(long id) throws MessagingException;

    void sendMailContacto(MensajeDto mensaje);
    
    void sendCambioContrasenia();

    public void sendConfirmacionCambioContrasenia();
    
}
