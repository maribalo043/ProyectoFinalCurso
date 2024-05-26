package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Partido;

public interface PartidoService {
    
    ModelAndView getPartidos();

    ModelAndView getPartido(long idLocal,long idVisitante);

    ModelAndView deletePartido(long idLocal,long idVisitante);

    ModelAndView addPartido();

    ModelAndView savePartido(Partido partidoNuevo, BindingResult bindingResult);

    ModelAndView editPartido(long idLocal,long idVisitante);
}
