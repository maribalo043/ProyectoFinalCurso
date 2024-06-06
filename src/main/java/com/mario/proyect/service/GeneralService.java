package com.mario.proyect.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface GeneralService {

    ModelAndView getIndex();

    ModelAndView getTorneo();

    ModelAndView getEquipos();

    ModelAndView getLinks();

    ModelAndView getCategorias();

    ModelAndView getPartidosCategoria(@PathVariable String cat);

    ModelAndView mostrarEstadisticas(@RequestParam(required = false, defaultValue = "18") int edadParametro, @RequestParam(required = false) Long categoriaId);

    ModelAndView envioDatosFormContacto();
}
