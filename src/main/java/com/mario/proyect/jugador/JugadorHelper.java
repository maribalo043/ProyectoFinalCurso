package com.mario.proyect.jugador;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.equipo.EquipoDAO;

public class JugadorHelper {

    protected ModelAndView helperViewJugadores(String filtro, JugadorDAO jugadorDao, EquipoDAO equipoDao) {

        ModelAndView model = new ModelAndView("jugadorHTML/jugadores");
        if (filtro != null) {
            if (filtro.equals("tallaCamiseta")) {
                model.addObject("jugadores", jugadorDao.findAllPorTallaCamiseta());
            } else if (filtro.equals("equipo")) {
                model.addObject("jugadores", jugadorDao.findAllPorEquipo());
            } else {
                model.addObject("jugadores", jugadorDao.findAll());
            }
        } else {
            model.addObject("jugadores", jugadorDao.findAll());
        }
        return model;

    }

    protected ModelAndView helperSaveJugador(Jugador jugadorNuevo, BindingResult bindingResult, JugadorDAO jugadorDao,
            EquipoDAO equipoDao) {
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.addObject("jugadorNuevo", jugadorNuevo);
            model.addObject("equipos", equipoDao.findAll());
            model.setViewName("jugadorHTML/jugadoresForm");
            return model;
        }
        jugadorNuevo.setEquipo(jugadorNuevo.getEquipo());
        jugadorDao.save(jugadorNuevo);

        model.setViewName("redirect:/jugadores");
        return model;
    }

}
