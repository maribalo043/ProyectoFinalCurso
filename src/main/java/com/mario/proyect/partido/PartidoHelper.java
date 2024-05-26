package com.mario.proyect.partido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.equipo.Equipo;
import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.equipo.EquipoHelper;

public class PartidoHelper {

    @Autowired
    private EquipoHelper equipoHelper;

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

                procesarPartido(partidoNuevo, equipoDao);

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
    
    protected void procesarPartido(Partido partido,EquipoDAO equipoDao) {
        Equipo equipoLocal = partido.getEquipoLocal();
        Equipo equipoVisitante = partido.getEquipoVisitante();

        int golesLocal = partido.getGolesLocal();
        int golesVisitante = partido.getGolesVisitante();

        // Actualizar goles
        equipoLocal.setGolesFavor(equipoLocal.getGolesFavor() + golesLocal);
        equipoLocal.setGolesContra(equipoLocal.getGolesContra() + golesVisitante);
        equipoVisitante.setGolesFavor(equipoVisitante.getGolesFavor() + golesVisitante);
        equipoVisitante.setGolesContra(equipoVisitante.getGolesContra() + golesLocal);

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
