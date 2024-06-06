package com.mario.proyect.service;

import com.mario.proyect.dto.MensajeDto;

import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendMailInscripcion(long id) throws MessagingException;

    public void sendMailContacto(MensajeDto mensaje);
    
}
