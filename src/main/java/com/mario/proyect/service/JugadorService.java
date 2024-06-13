package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Jugador;

public interface JugadorService {

    ModelAndView getJugadores(String filtro);

    ModelAndView getJugador(long id);

    ModelAndView deleteJugador(long id);
    
    ModelAndView addJugador();

    ModelAndView saveJugador(Jugador jugadorNuevo,BindingResult bindingResult);

    ModelAndView editJugador(long id);

    ModelAndView postJugadorEquipo(long id);

    ModelAndView saveJugadorTorneo(Jugador jugadorNuevo,BindingResult bindingResult);
}
