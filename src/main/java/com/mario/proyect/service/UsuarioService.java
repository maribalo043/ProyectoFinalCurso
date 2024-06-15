package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;

public interface UsuarioService {

    ModelAndView getUsuarios(boolean hayError);

    ModelAndView getUsuario(String dni, boolean hayError);

    ModelAndView deleteUsuario(String nombre);

    ModelAndView addUsuario();
    
    ModelAndView saveUsuario(Usuario usuarioNuevo, BindingResult bindingResult,HttpServletRequest requestS);

    ModelAndView editUsuario(String dni, HttpServletRequest request);

    /* Gestion de sesiones: */

    Usuario findUserByUsuario(String usuario);
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
