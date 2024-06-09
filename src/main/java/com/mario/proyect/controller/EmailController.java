package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.dto.MensajeDto;
import com.mario.proyect.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/enviar")
    public ModelAndView enviarCorreo(@RequestParam("id") Long id) throws MessagingException  {
        try {
            emailService.sendMailInscripcion(id);
        } catch (Exception e) {
            throw new MessagingException();
        }
        return new ModelAndView("redirect:/inscrito");
    }

    @PostMapping("/mailForm")
    public ModelAndView enviarCorreoContactanos(@RequestParam String nombre, @RequestParam String correo, @RequestParam String mensaje) {
        try {
            MensajeDto mensajeDto = new MensajeDto();
            mensajeDto.setNombre(nombre);
            mensajeDto.setCorreo(correo);
            mensajeDto.setMensaje(mensaje);
            
            emailService.sendMailContacto(mensajeDto);
            
            ModelAndView model = new ModelAndView();
            model.setViewName("redirect:/");
            return model;
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de contacto: " + e.getMessage(), e);
        }
    }
    @GetMapping("/cambioContraseniaUsuario")
    public ModelAndView enviarCambioContrasenia() throws MessagingException  {
        try {
            emailService.sendCambioContrasenia();
        } catch (Exception e) {
            throw new MessagingException();
        }
        return new ModelAndView("generalHTML/cambioContrasenia");
    }
}
