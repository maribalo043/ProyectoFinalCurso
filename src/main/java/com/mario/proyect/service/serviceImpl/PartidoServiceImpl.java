package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.PartidoService;

import jakarta.validation.Valid;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoDAO partidoDao;
    @Autowired
    private EquipoDAO equipoDao;

    @Override
    public ModelAndView getPartidos() {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidos");
        model.addObject("partidos", partidoDao.findAll());

        return model;
    }

    @Override
    public ModelAndView getPartido(long id) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partido");
        
        model.addObject("partido", partidoDao.findById(id).get());
        return model;
    }

    @Override
    public ModelAndView deletePartido(long id) {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/partidos");

        Optional<Partido> partidoOptional = partidoDao.findById(id);

        if (partidoOptional.isPresent()) {
            partidoDao.delete(partidoOptional.get());
        }
        return model;
    }

    @Override
    public ModelAndView addPartido() {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");
        model.addObject("partidoNuevo", new Partido());
        model.addObject("equipos", equipoDao.findAll());

        return model;
    }

    @Override
    public ModelAndView savePartido(@Valid Partido partidoNuevo, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors() || partidoNuevo.getEquipoLocal() == partidoNuevo.getEquipoVisitante()) {
            model.setViewName("partidoForm");
            model.setViewName("partidoHTML/partidoForm");
            model.addObject("partidoNuevo", partidoNuevo);
            model.addObject("equipos", equipoDao.findAll());
            if(partidoNuevo.getEquipoLocal() == partidoNuevo.getEquipoVisitante()){
                model.addObject("error","Los equipos no pueden ser los mismos." );
            }
            return model;
        }
        model.setViewName("redirect:/partidos");

        partidoDao.save(partidoNuevo);
        procesarPartido(partidoNuevo);

        return model;
    }

    @Override
    public ModelAndView editPartido(long id) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");

        Optional<Partido> partido = partidoDao.findById(id);

        model.addObject("partidoNuevo", partido.get());
        model.addObject("equipos", equipoDao.findAll());

        return model;
    }

    private void procesarPartido(Partido partido) {
        Equipo equipoLocal = equipoDao.findById(partido.getEquipoLocal().getId()).get();
        Equipo equipoVisitante = equipoDao.findById(partido.getEquipoVisitante().getId()).get();

        int golesLocal = partido.getGolesLocal();
        int golesVisitante = partido.getGolesVisitante();
        if (partido.isFinalizado()) {
            // Actualizar goles
            equipoLocal.setGolesFavor(equipoLocal.getGolesFavor() + golesLocal);
            equipoLocal.setGolesContra(equipoLocal.getGolesContra() + golesVisitante);
            equipoVisitante.setGolesFavor(equipoVisitante.getGolesFavor() + golesVisitante);
            equipoVisitante.setGolesContra(equipoVisitante.getGolesContra() + golesLocal);
            equipoLocal.setPartidosJugados(equipoLocal.getPartidosJugados() + 1);
            equipoVisitante.setPartidosJugados(equipoVisitante.getPartidosJugados() + 1);

            // Determinar el resultado y actualizar puntos y partidos
            if (golesLocal > golesVisitante) {
                equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
                equipoLocal.setPartidosGanados(equipoLocal.getPartidosGanados() + 1);
                equipoVisitante.setPartidosPerdidos(equipoVisitante.getPartidosPerdidos() + 1);
            } else if (golesLocal < golesVisitante) {
                equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);
                equipoVisitante.setPartidosGanados(equipoVisitante.getPartidosGanados() + 1);
                equipoLocal.setPartidosPerdidos(equipoLocal.getPartidosPerdidos() + 1);
            } else {
                equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
                equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
                equipoLocal.setPartidosEmpatados(equipoLocal.getPartidosEmpatados() + 1);
                equipoVisitante.setPartidosEmpatados(equipoVisitante.getPartidosEmpatados() + 1);
            }

            equipoDao.save(equipoLocal);
            equipoDao.save(equipoVisitante);
        }
    }
}