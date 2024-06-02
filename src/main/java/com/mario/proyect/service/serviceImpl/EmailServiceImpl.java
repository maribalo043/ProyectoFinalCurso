package com.mario.proyect.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mario.proyect.dto.EmailDto;
import com.mario.proyect.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendMail(EmailDto email) throws MessagingException{
        try{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

        helper.setTo(email.getDestinatario());
        helper.setSubject(email.getAsunto());
        /* Correo con texto plano: */
        helper.setText(email.getMensaje());
        /* Correo con Plantilla: */
        Context context = new Context();
        context.setVariable("mensaje", email.getMensaje());
        /* templateEngine.process("nombrePlantillaHTML,context") */
        String contenidoHtml = templateEngine.process("email",context);
        /*plantilla + si es html o no */
        helper.setText(contenidoHtml,true);
        javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo" + e.getMessage() , e);
        }
    }
    
}
