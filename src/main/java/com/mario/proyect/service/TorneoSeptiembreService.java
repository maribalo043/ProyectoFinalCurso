package com.mario.proyect.service;

import org.springframework.web.servlet.ModelAndView;

public interface TorneoSeptiembreService {

    ModelAndView getSeleccionCategoria();

    ModelAndView getPartidos(long categoria);

    ModelAndView getJugadoresGoles(long categoria);

    ModelAndView getJugadoresTarjetas(long categoria);

    ModelAndView getDetallePartido(long partido); 

    ModelAndView getModificacionPartido(long partido);
}
