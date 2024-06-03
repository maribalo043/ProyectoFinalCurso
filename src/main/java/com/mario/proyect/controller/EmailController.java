package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mario.proyect.dto.EmailDto;
import com.mario.proyect.service.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(@RequestBody EmailDto email) {
        try {
            emailService.sendMail(email);
            return new ResponseEntity<>("Correo enviado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar el correo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 

