package com.mario.proyect.jugador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mario.proyect.equipo.EquipoDAO;

import jakarta.validation.Valid;

@Controller
public class JugadorController {

    @Autowired
    private JugadorDAO jugadorDao;
    @Autowired
    private EquipoDAO equipoDao;

    private JugadorHelper helper = new JugadorHelper();

    @GetMapping(value = { "/jugadores", "/jugadores/{filtro}" })
    public ModelAndView getJugadores(@PathVariable(required = false) String filtro) {
        return helper.helperViewJugadores(filtro, jugadorDao, equipoDao);
    }

    @SuppressWarnings("null")
    @GetMapping("/jugador/{dni}")
    public ModelAndView getJugador(@PathVariable String dni) {
        Jugador jugador = jugadorDao.findById(dni).get();
        ModelAndView model = new ModelAndView();
        model.setViewName("jugadorHTML/Jugador");
        model.addObject("jugador", jugador);
        return model;
    }

    @SuppressWarnings("null")
    @GetMapping("/jugador/del/{dni}")
    public ModelAndView deleteJugador(@PathVariable String dni) {

        ModelAndView model = new ModelAndView();
        Optional<Jugador> jugador = jugadorDao.findById(dni);
        if (jugador.isPresent()) {
            jugadorDao.deleteById(dni);
        }
        model.setViewName("redirect:/jugadores");

        return model;
    }

    @GetMapping("/jugador/add")
    public ModelAndView addJugador() {
        ModelAndView model = new ModelAndView();
        model.addObject("jugadorNuevo", new Jugador());
        model.addObject("equipos", equipoDao.findAll());
        model.addObject("equipoItem", equipoDao.findAll());
        model.setViewName("jugadorHTML/jugadoresForm");

        return model;
    }

    @PostMapping("/jugador/save")
    public ModelAndView saveJugador(@ModelAttribute("jugadorNuevo") @Valid Jugador jugadorNuevo,
            BindingResult bindingResult) {
        return helper.helperSaveJugador(jugadorNuevo, bindingResult, jugadorDao, equipoDao);
    }

    @SuppressWarnings("null")
    @GetMapping("/jugador/edit/{dni}")
    public ModelAndView editJugador(@PathVariable String dni) {
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
}