package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.entity.PartidoKey;
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
    public ModelAndView getPartido(long idLocal, long idVisitante) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partido");
        Partido partido = new Partido();

        PartidoKey partidoKey = formarPartidoKey(idLocal, idVisitante);

        partido = partidoDao.findById(partidoKey).get();

        model.addObject("partido", partido);
        return model;
    }

    @Override
    public ModelAndView deletePartido(long idLocal, long idVisitante) {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/partidos");

        PartidoKey partidoKey = formarPartidoKey(idLocal, idVisitante);

        Optional<Partido> partidoOptional = partidoDao.findById(partidoKey);

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
        this.procesarPartido(partidoNuevo);
        return model;
    }

    @Override
    public ModelAndView editPartido(long idLocal, long idVisitante) {

        ModelAndView model = new ModelAndView();
        model.setViewName("partidoHTML/partidoForm");

        Partido partido = new Partido();
        PartidoKey key = formarPartidoKey(idLocal, idVisitante);
        partido.setId(key);

        model.addObject("partidoNuevo", partido);
        model.addObject("equipos", equipoDao.findAll());

        return model;
    }

    public PartidoKey formarPartidoKey(long idLocal, long idVisitante) {

        PartidoKey partidoKey = new PartidoKey();
        partidoKey.setIdEquipoLocal(idLocal);
        partidoKey.setIdEquipoVisitante(idVisitante);

        return partidoKey;
    }

    private void procesarPartido(Partido partido) {
        Equipo equipoLocal = partido.getEquipoLocal();
        Equipo equipoVisitante = partido.getEquipoVisitante();

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