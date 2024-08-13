package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Partido;

public interface PartidoService {
    
    ModelAndView getPartidos();

    ModelAndView getPartido(long id);

    ModelAndView deletePartido(long id);

    ModelAndView addPartido();

    ModelAndView savePartido(Partido partidoNuevo, BindingResult bindingResult);

    ModelAndView editPartido(long id);
}
