package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;

public interface UsuarioService {

    ModelAndView getUsuarios();

    ModelAndView getUsuario(String dni);

    ModelAndView deleteUsuario(String nombre);

    ModelAndView addUsuario();
    
    ModelAndView saveUsuario(Usuario usuarioNuevo, BindingResult bindingResult,HttpServletRequest requestS);

    ModelAndView saveRol(Usuario usuario, BindingResult bindingResult);

    ModelAndView editUsuario(String dni, HttpServletRequest request);

    ModelAndView cambioValidoUsuario(String email);

    ModelAndView getTarjetaUsuario(HttpServletRequest request);

    /* Gestion de sesiones: */

    void saveUser(Usuario user);

    ModelAndView login();

    ModelAndView loginUser(Usuario user,HttpServletRequest request);

    ModelAndView registrer();

    ModelAndView registrerUser(Usuario user, BindingResult bindingResult);

    ModelAndView logout(HttpServletRequest request);

    ModelAndView home(HttpServletRequest request);

    ModelAndView cambioContrasenia();

    ModelAndView formCambioContrasenia();

    ModelAndView guardarCambioContrasenia(Usuario user,BindingResult bindingResult,HttpServletRequest request);
}
