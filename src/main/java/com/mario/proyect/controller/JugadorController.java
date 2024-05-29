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

    @GetMapping("/jugador/{dni}")
    public ModelAndView getJugador(@PathVariable String dni) {
        return jugadorService.getJugador(dni);
    }

    @GetMapping("/jugador/del/{dni}")
    public ModelAndView deleteJugador(@PathVariable String dni) {
        return jugadorService.deleteJugador(dni);
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

    @GetMapping("/jugador/edit/{dni}")
    public ModelAndView editJugador(@PathVariable String dni) {
        return jugadorService.editJugador(dni);
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