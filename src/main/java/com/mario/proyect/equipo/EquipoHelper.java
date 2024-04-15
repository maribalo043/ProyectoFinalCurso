package com.mario.proyect.equipo;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.jugador.JugadorDAO;
import com.mario.proyect.partido.Partido;
import com.mario.proyect.partido.PartidoDAO;

public class EquipoHelper {

    protected ModelAndView helperViewEquipo(long id, JugadorDAO jugadorDao, CategoriaDAO categoriaDao,
            EquipoDAO equipoDao, PartidoDAO partidoDao) {
        ModelAndView model = new ModelAndView();
        Equipo equipo = equipoDao.findById(id).get();
        if (equipoDao.findById(id).isPresent()) {
            model.addObject("equipo", equipoDao.findById(id).get());
            model.addObject("partidos", partidoDao.obtenerPartidosPorEquipo(id));
            model.addObject("partidoNuevo", new Partido());
            model.addObject("equipos", equipo.obtenerEquiposNoEnlazadosConId(equipoDao, partidoDao));
            model.setViewName("equipoHTML/equipo");
        } else {
            model.setViewName("equipoHTML/equipos");
        }
        return model;
    }

    @SuppressWarnings("null")
    protected ModelAndView helperDelEquipo(long id, JugadorDAO jugadorDao, CategoriaDAO categoriaDao,
            EquipoDAO equipoDao, PartidoDAO partidoDao) {
        ModelAndView model = new ModelAndView();

        Optional<Equipo> equipoOptional = equipoDao.findById(id);

        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();

            List<Partido> partidosAsociados = partidoDao.findByEquipoLocalOrEquipoVisitante(equipo, equipo);
            partidoDao.deleteAll(partidosAsociados);

            equipoDao.deleteById(id);
        }

        model.setViewName("redirect:/equipos");
        return model;
    }

    @SuppressWarnings("null")
    protected ModelAndView helperSaveEquipo(Equipo equipo, BindingResult bindingResult, JugadorDAO jugadorDao,
            CategoriaDAO categoriaDao, EquipoDAO equipoDao, PartidoDAO partidoDao) {
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.addObject("equipoNuevo", equipo);
            model.addObject("categorias", categoriaDao.categoriasActive());
            model.setViewName("equipoHTML/equipoForm");
            return model;
        }

        model.setViewName("redirect:/equipos");
        equipoDao.save(equipo);

        model.addObject("equipos", equipoDao.findAll());
        return model;
    }

}
