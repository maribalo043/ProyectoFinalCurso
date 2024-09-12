package com.mario.proyect.service;

import org.springframework.web.servlet.ModelAndView;

public interface TorneoSeptiembreService {

    ModelAndView getSeleccionCategoria();

    ModelAndView getPartidos(long categoria);

    ModelAndView getDetallePartido(long partido, long categoria); 

    ModelAndView getModificacionPartido(long partido, long categoria);

    ModelAndView getEquiposClasificacion(long categoria);

    public ModelAndView modificarPartido (String modificador,String equipo, long id, long categoria);
}
