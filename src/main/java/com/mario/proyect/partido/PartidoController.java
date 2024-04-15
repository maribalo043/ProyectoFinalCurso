package com.mario.proyect.partido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.equipo.EquipoDAO;

import jakarta.validation.Valid;

@Controller
public class PartidoController {

    @Autowired
    private PartidoDAO partidoDao;
    @Autowired
    private CategoriaDAO categoriaDao;
    @Autowired
    private EquipoDAO equipoDao;

    private PartidoHelper helper = new PartidoHelper();

    @GetMapping("/partidos")
    public ModelAndView getPartidos() {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidos");
        model.addObject("partidos", partidoDao.findAll());

        return model;
    }

    @GetMapping("/partido/{idLocal}/{idVisitante}")
    public ModelAndView getPartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
        return helper.helperViewPartido(idLocal, idVisitante, categoriaDao, equipoDao, partidoDao);
    }

    @SuppressWarnings("null")
    @GetMapping("/partido/del/{idLocal}/{idVisitante}")
    public ModelAndView deletePartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/partidos");

        PartidoKey partidoKey = helper.formarPartidoKey(idLocal, idVisitante);

        Optional<Partido> partidoOptional = partidoDao.findById(partidoKey);

        if (partidoOptional.isPresent()) {
            partidoDao.delete(partidoOptional.get());
        }
        return model;
    }

    @GetMapping("/partido/add")
    public ModelAndView addPartido() {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");
        model.addObject("partidoNuevo", new Partido());
        model.addObject("equipos", equipoDao.findAll());

        return model;
    }

    @PostMapping("/partido/save")
    public ModelAndView savePartido(@ModelAttribute("partidoNuevo") @Valid Partido partidoNuevo,
            BindingResult bindingResult) {

        return helper.helperSavePartido(partidoNuevo, bindingResult, categoriaDao, equipoDao, partidoDao);

    }

    @GetMapping("/partido/edit/{idLocal}/{idVisitante}")
    public ModelAndView editPartido(@PathVariable long idLocal, @PathVariable long idVisitante) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");

        Partido partido = new Partido();
        PartidoKey key = helper.formarPartidoKey(idLocal, idVisitante);
        partido.setId(key);

        model.addObject("partidoNuevo", partido);
        model.addObject("equipos", equipoDao.findAll());

        return model;
    }
}
