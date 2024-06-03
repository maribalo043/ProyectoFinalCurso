package com.mario.proyect.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mario.proyect.dto.EmailDto;
import com.mario.proyect.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${email.username}")
    private String email;

    @Override
    public void sendMail(EmailDto correoRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(email);
            helper.setTo(correoRequest.getDestinatario());
            helper.setSubject(correoRequest.getAsunto());

            // Procesar la plantilla Thymeleaf
            Context context = new Context();
            context.setVariable("mensaje", correoRequest.getMensaje());
            String contenidoHtml = templateEngine.process("email", context);

            helper.setText(contenidoHtml, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
