package com.mario.proyect.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoDAO equipoDao;
    @Autowired
    private CategoriaDAO categoriaDao;
    @Autowired
    private PartidoDAO partidoDao;

    @Override
    public ModelAndView getEquipos(String filtro) {
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

    @Override
    public ModelAndView getEquipo(long id) {
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

    @Override
    public ModelAndView deleteEquipo(long id) {
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

    @Override
    public ModelAndView addEquipo() {

        ModelAndView model = new ModelAndView();
        model.addObject("equipoNuevo", new Equipo());
        model.addObject("categorias", categoriaDao.categoriasActive());
        model.setViewName("equipoHTML/equipoForm");

        return model;
    }

    @Override
    public ModelAndView saveEquipo(Equipo equipo, BindingResult bindingResult) {
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

    @Override
    public ModelAndView editEquipo(long id) {
        
        ModelAndView model = new ModelAndView();
        Optional<Equipo> equipo = equipoDao.findById(id);
        if (equipo.isPresent()) {
            model.addObject("equipoNuevo", equipo.get());
            model.addObject("categorias", categoriaDao.categoriasActive());
        }
        model.setViewName("equipoHTML/equipoForm");
        return model;
    }

    @Override
    public ModelAndView addEquipoTorneo() {

        ModelAndView model = new ModelAndView();
        model.addObject("equipoNuevo", new Equipo());
        model.addObject("categorias", categoriaDao.categoriasActive());
        model.setViewName("torneoHTML/inscripcionEquipo");

        return model;

    }

    @Override
    public ModelAndView saveEquipoTorneo(Equipo equipo, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()) {
            model.addObject("equipoNuevo", equipo);
            model.addObject("categorias", categoriaDao.categoriasActive());
            model.setViewName("torneoHTML/inscripcionEquipo");
            return model;
        }
        equipoDao.save(equipo);
        model.setViewName("redirect:/equipo/jugador/" + equipo.getId());
        
        return model;
    }

}
