package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Jugador;
import com.mario.proyect.service.JugadorService;

import jakarta.validation.Valid;

@Controller
public class JugadorController {
	
	@Autowired
    private JugadorService jugadorService;

    @GetMapping(value = { "/jugadores", "/jugadores/{filtro}" })
    public ModelAndView getJugadores(@PathVariable(required = false) String filtro) {
        return jugadorService.getJugadores(filtro);
    }

    @GetMapping("/jugador/{id}")
    public ModelAndView getJugador(@PathVariable long id) {
        return jugadorService.getJugador(id);
    }

    @GetMapping("/jugador/del/{id}")
    public ModelAndView deleteJugador(@PathVariable long id) {
        return jugadorService.deleteJugador(id);
    }

    @GetMapping("/jugador/add")
    public ModelAndView addJugador() {
        return jugadorService.addJugador();
    }

    @PostMapping("/jugador/save")
    public ModelAndView saveJugador(@ModelAttribute("jugadorNuevo") @Valid Jugador jugadorNuevo,
            BindingResult bindingResult) {
        return jugadorService.saveJugador(jugadorNuevo, bindingResult);
    }

    @GetMapping("/jugador/edit/{id}")
    public ModelAndView editJugador(@PathVariable long id) {
        return jugadorService.editJugador(id);
    }

    @GetMapping("/equipo/jugador/{id}")
    public ModelAndView postJugadorEquipo(@PathVariable long id) {
        return jugadorService.postJugadorEquipo(id);
    }

    @PostMapping("/jugadorTorneo/save")
    public ModelAndView saveJugadorTorneo(@ModelAttribute("jugadorNuevo") @Valid Jugador jugadorNuevo,
            BindingResult bindingResult) {
        return jugadorService.saveJugadorTorneo(jugadorNuevo, bindingResult);
    }
}