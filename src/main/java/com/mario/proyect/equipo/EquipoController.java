package com.mario.proyect.equipo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.jugador.JugadorDAO;
import com.mario.proyect.partido.PartidoDAO;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EquipoController {
    @Autowired
    private JugadorDAO jugadorDao;
    @Autowired
    private EquipoDAO equipoDao;
    @Autowired
    private CategoriaDAO categoriaDao;
    @Autowired
    private PartidoDAO partidoDao;

    private EquipoHelper helper = new EquipoHelper();

    @GetMapping(value = { "/equipos", "/equipos/{filtro}" })
    public ModelAndView getEquipos(@PathVariable(required = false) String filtro) {

        ModelAndView model = new ModelAndView();
        model.setViewName("equipoHTML/equipos");
        if (filtro != null) {
            if (filtro.equals("categorias")) {
                model.addObject("equipos", equipoDao.findAllByCategoria());
            }
        } else {
            model.addObject("equipos", equipoDao.findAll());
        }
        return model;
    }

    @GetMapping("equipo/{id}")
    public ModelAndView getEquipo(@PathVariable long id) {

        return helper.helperViewEquipo(id, jugadorDao, categoriaDao, equipoDao, partidoDao);
    }

    @GetMapping("/equipo/del/{id}")
    public ModelAndView deleteEquipo(@PathVariable long id) {
        return helper.helperDelEquipo(id, jugadorDao, categoriaDao, equipoDao, partidoDao);
    }

    @GetMapping("equipo/add")
    public ModelAndView addJugador() {

        ModelAndView model = new ModelAndView();
        model.addObject("equipoNuevo", new Equipo());
        model.addObject("categorias", categoriaDao.categoriasActive());
        model.setViewName("equipoHTML/equipoForm");

        return model;
    }

    @PostMapping("/equipo/save")
    public ModelAndView saveEquipo(@ModelAttribute @Valid Equipo equipo, BindingResult bindingResult) {
        return helper.helperSaveEquipo(equipo, bindingResult, jugadorDao, categoriaDao, equipoDao, partidoDao);
    }

    @GetMapping("equipo/edit/{id}")
    public ModelAndView editEquipo(@PathVariable long id) {

        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipo = equipoDao.findById(id);
        if (equipo.isPresent()) {
            model.addObject("equipoNuevo", equipo.get());
            model.addObject("categorias", categoriaDao.categoriasActive());
        }
        model.setViewName("equipoHTML/equipoForm");
        return model;
    }

}
