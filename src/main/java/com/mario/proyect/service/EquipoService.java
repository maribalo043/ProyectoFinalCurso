package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;

public interface EquipoService {

    ModelAndView getEquipos(String filtro);

    ModelAndView getEquipo(long id);
    
    ModelAndView deleteEquipo(long id);

    ModelAndView addEquipo();

    ModelAndView saveEquipo(Equipo equipo, BindingResult bindingResult);

    ModelAndView editEquipo(long id);

    ModelAndView addEquipoTorneo();

    ModelAndView saveEquipoTorneo(Equipo equipo, BindingResult bindingResult);
}
