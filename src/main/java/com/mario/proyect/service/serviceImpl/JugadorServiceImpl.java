package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Jugador;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.JugadorDAO;
import com.mario.proyect.service.JugadorService;

@Service
public class JugadorServiceImpl implements JugadorService{

    @Autowired
    private JugadorDAO jugadorDao;
    @Autowired
    private EquipoDAO equipoDao;


    @Override
    public ModelAndView getJugadores(String filtro) {

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

    @Override
    public ModelAndView getJugador(String dni) {

        Jugador jugador = jugadorDao.findById(dni).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("jugadorHTML/Jugador");
        model.addObject("jugador", jugador);

        return model;
    }

    @Override
    public ModelAndView deleteJugador(String dni) {

        ModelAndView model = new ModelAndView();
        Optional<Jugador> jugador = jugadorDao.findById(dni);
        if (jugador.isPresent()) {
            jugadorDao.deleteById(dni);
        }
        model.setViewName("redirect:/jugadores");

        return model;
    }
    @Override
    public ModelAndView addJugador() {

        ModelAndView model = new ModelAndView();
        model.addObject("jugadorNuevo", new Jugador());
        model.addObject("equipos", equipoDao.findAll());
        model.addObject("equipoItem", equipoDao.findAll());
        model.setViewName("jugadorHTML/jugadoresForm");

        return model;
    }
    @Override
    public ModelAndView saveJugador(Jugador jugadorNuevo, BindingResult bindingResult) {

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
    @Override
    public ModelAndView editJugador(String dni) {

        ModelAndView model = new ModelAndView();
        Optional<Jugador> jugOpt = jugadorDao.findById(dni);

        if (jugOpt.isPresent()) {
            Jugador jugador = jugOpt.get();
            model.addObject("jugadorNuevo", jugador);
            model.addObject("equipos", equipoDao.findAll());
            model.setViewName("jugadorHTML/jugadoresForm");
        } else {
            model.setViewName("redirect:/jugadores");
        }

        return model;
    }
    @Override
    public ModelAndView postJugadorEquipo(long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipoModificado = equipoDao.findById(id);
        model.addObject("jugadorNuevo", new Jugador());
        model.addObject("equipo", equipoModificado.get());
        model.addObject("equipoItem", equipoDao.findAll());
        if (equipoModificado.get().getJugadores().size() == 4) {
            model.setViewName("torneoHTML/adesion");
        } else {
            model.setViewName("torneoHTML/inscripcionJugadores");
        }

        return model;
    }
    @Override
    public ModelAndView saveJugadorTorneo(Jugador jugadorNuevo, BindingResult bindingResult) {

        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.addObject("jugadorNuevo", jugadorNuevo);
            model.addObject("equipos", equipoDao.findAll());
            model.setViewName("jugadorHTML/jugadoresForm");
            return model;
        }
        jugadorNuevo.setEquipo(jugadorNuevo.getEquipo());
        jugadorDao.save(jugadorNuevo);

        model.setViewName("redirect:/equipo/jugador/"+jugadorNuevo.getEquipo().getId());
        return model;
    }



}
