package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;

import jakarta.validation.Valid;

public interface UsuarioService {

    ModelAndView getUsuarios();

    ModelAndView getUsuario(@PathVariable String dni);

    ModelAndView deleteUsuario(@PathVariable String nombre);

    ModelAndView addUsuario();
    
    ModelAndView saveUsuario(@ModelAttribute("usuarioNuevo") @Valid Usuario usuarioNuevo, BindingResult bindingResult);

    ModelAndView editUsuario(@PathVariable String dni);
}
