package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Usuario;
import com.mario.proyect.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
    @Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	public ModelAndView getUsuarios() {
		return usuarioService.getUsuarios();
	}

    @GetMapping("/usuario/{nombre}")
    public ModelAndView getUsuario(@PathVariable String nombre) {
        return usuarioService.getUsuario(nombre);
    }

    @GetMapping("/usuario/del/{nombre}")
    public ModelAndView deleteUsuario(@PathVariable String nombre) {
        return usuarioService.deleteUsuario(nombre);
    }

    @GetMapping("/usuario/add")
    public ModelAndView addUsuario() {
        return usuarioService.addUsuario();
    }

    @PostMapping("/usuario/save")
    public ModelAndView saveUsuario(@ModelAttribute("usuarioNuevo") @Valid Usuario usuarioNuevo,
            BindingResult bindingResult) {
        return usuarioService.saveUsuario(usuarioNuevo, bindingResult);
    }

    @GetMapping("/usuario/edit/{nombre}")
    public ModelAndView editUsuario(@PathVariable String nombre) {
        return usuarioService.editUsuario(nombre);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return usuarioService.login();
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("user") Usuario user, HttpServletRequest request) {
        return usuarioService.loginUser(user,request);
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return usuarioService.registrer();
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") Usuario user) {
        return usuarioService.registrerUser(user);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        return usuarioService.logout(request);
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        return usuarioService.home(request);
    }
    @GetMapping("/cambioContrasenia")
    public ModelAndView cambioContrasenia() {
        return usuarioService.cambioContrasenia();
    }
    
}
