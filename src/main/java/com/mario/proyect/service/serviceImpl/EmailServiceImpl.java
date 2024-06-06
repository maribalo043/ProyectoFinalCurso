package com.mario.proyect.service.serviceImpl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mario.proyect.dto.EmailDto;
import com.mario.proyect.dto.MensajeDto;
import com.mario.proyect.entity.Equipo;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.service.EmailService;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EquipoDAO equipoDao;

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${email.username}")
    private String email;

    @Override
    public void sendMailInscripcion(long id) {
    try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Optional<Equipo> equipo = equipoDao.findById(id);

        helper.setFrom(email);
        helper.setTo(equipo.get().getEmailContacto());
        helper.setSubject("Inscripción Torneo 3x3");
        Context context = new Context();
        System.out.println(equipo.get());
        if (equipo.isPresent()) {
            context.setVariable("equipo", equipo.get());
        } else {
            throw new RuntimeException("Equipo no encontrado");
        }
        System.out.println(equipo.get());
        String contenidoHtml = templateEngine.process("emailHTML/email", context);

        helper.setText(contenidoHtml, true);
        javaMailSender.send(message);
        System.out.println(equipo.get());
    } catch (Exception e) {
        throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
    }
}

@Override
@Transactional
public void sendMailContacto(MensajeDto mensaje) {
    try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(email);
        helper.setTo(email);

        helper.setSubject("Contacto Usuario");

        // Procesar la plantilla Thymeleaf
        Context context = new Context();
        context.setVariable("mensaje", mensaje);

        String contenidoHtml = templateEngine.process("emailHTML/emailContacto", context);

        helper.setText(contenidoHtml, true);
        javaMailSender.send(message);
    } catch (Exception e) {
        throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
    }
}

}
