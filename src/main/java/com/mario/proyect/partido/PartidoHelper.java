package com.mario.proyect.partido;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.equipo.EquipoDAO;

public class PartidoHelper {

    @SuppressWarnings("null")
    protected ModelAndView helperViewPartido(long idLocal, long idVisitante, CategoriaDAO categoriaDao,
            EquipoDAO equipoDao, PartidoDAO partidoDao) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partido");
        Partido partido = new Partido();

        PartidoKey partidoKey = formarPartidoKey(idLocal, idVisitante);

        partido = partidoDao.findById(partidoKey).get();

        model.addObject("partido", partido);
        return model;
    }

    @SuppressWarnings("null")
    protected ModelAndView helperSavePartido(Partido partidoNuevo, BindingResult bindingResult,
            CategoriaDAO categoriaDao, EquipoDAO equipoDao, PartidoDAO partidoDao) {
        ModelAndView model = new ModelAndView();
        /* , BindingResult bindingResult */
        /* Revisar que todo se setea o ver si se guarda etc */
        if (bindingResult.hasErrors()) {
            model.setViewName("partidoForm");

            model.setViewName("partidoHTML/partidoForm");
            model.addObject("partidoNuevo", partidoNuevo);
            model.addObject("equipos", equipoDao.findAll());

            return model;

        }
        model.setViewName("redirect:/partidos");

        if (partidoNuevo.getId() != null) {
            Partido existente = partidoDao.findById(partidoNuevo.getId()).orElse(null);
            if (existente != null) {
                existente.setGolesLocal(partidoNuevo.getGolesLocal());
                existente.setGolesVisitante(partidoNuevo.getGolesVisitante());
                existente.setPista(partidoNuevo.getPista());
                partidoDao.save(existente);
            }
        } else {
            PartidoKey partidoKey = new PartidoKey();
            partidoKey.setIdEquipoLocal(partidoNuevo.getEquipoLocal().getId());
            partidoKey.setIdEquipoVisitante(partidoNuevo.getEquipoVisitante().getId());
            partidoNuevo.setId(partidoKey);
            partidoDao.save(partidoNuevo);
        }
        return model;
    }

    protected PartidoKey formarPartidoKey(long idLocal, long idVisitante) {

        PartidoKey partidoKey = new PartidoKey();
        partidoKey.setIdEquipoLocal(idLocal);
        partidoKey.setIdEquipoVisitante(idVisitante);

        return partidoKey;
    }
}
