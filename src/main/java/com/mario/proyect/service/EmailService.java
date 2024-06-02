package com.mario.proyect.service;

import com.mario.proyect.dto.EmailDto;

import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendMail(EmailDto email) throws MessagingException;
    
}
