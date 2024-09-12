package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.TorneoSeptiembreService;

@Service
public class TorneoSeptiembreServiceImpl implements TorneoSeptiembreService{

    @Autowired 
    private EquipoDAO equipoDao;
    @Autowired 
    private PartidoDAO partidoDao;
    @Autowired
    private CategoriaDAO categoriaDao;

    @Override
    public ModelAndView getSeleccionCategoria() {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/SeleccionCategoriaSeptiembre");
        model.addObject("categorias", categoriaDao.findAllCategoriasSeptiembre());
        return model;
    }

    @Override
    public ModelAndView getPartidos(long categoria) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/VistaPartidosSeptiembre");
        model.addObject("categoria", categoriaDao.findById(categoria).get());
        model.addObject("partidos", partidoDao.findAllPartidosSeptiembreByCategoria(categoria));
        model.addObject("equipos", equipoDao.selectEquiposPorCategoriaTorneoSeptiembre(categoria));
        return model;
    }

    @Override
    public ModelAndView getDetallePartido(long partido,long categoria) {
        ModelAndView model = new ModelAndView();
        model.setViewName("torneoSeptiembreHTML/DetallePartido");
        Optional<Partido> partidoOptinal = partidoDao.findById(partido);
        model.addObject("categoria", categoriaDao.findById(categoria).get());
        if(partidoOptinal.isPresent()){
            model.addObject("partido", partidoOptinal.get());
        }
        return model;
    }

    @Override
    public ModelAndView getModificacionPartido(long partido,long categoria) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/EdicionPartido");
        Optional<Partido> partidoOptinal = partidoDao.findById(partido);
        model.addObject("categoria", categoriaDao.findById(categoria).get());
        if(partidoOptinal.isPresent()){
            model.addObject("partido", partidoOptinal.get());
        }
        return model;
    }

    @Override
    public ModelAndView getEquiposClasificacion(long categoria){
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/clasificacion");
        model.addObject("categoria", categoriaDao.findById(categoria).get());
        model.addObject("equipos",equipoDao.selectEquiposPorCategoriaTorneoSeptiembre(categoria));
        return model;
    }

    @Override
    public ModelAndView modificarPartido (String modificador,String equipo, long id, long categoria){
        if(modificador != null){
            if (modificador.equals("gol")) {
                return sumarGol(equipo, id, categoria);
            }
            if (modificador.equals("falta")) {
                return sumarFalta(equipo, id, categoria);
            }
        }
        return getSavePartido(partidoDao.findById(id).get(), categoria);
    }

    public ModelAndView getSavePartido(Partido partido, long categoria) {
        ModelAndView model = new ModelAndView("redirect:/partidoSep/edit/"+partido.getId()+"/"+categoria);
        partidoDao.save(partido);
        model.addObject("categoria", categoriaDao.findById(categoria).get());
        model.addObject("partido",partido);

        return model;
    }

    private ModelAndView sumarGol(String equipo,long id,long categoria){
        Partido partido = partidoDao.findById(id).get();
        if(equipo!=null){
            if(equipo.equals("local")){
                partido.setGolesLocal(partido.getGolesLocal()+1);
                partidoDao.save(partido);
                return getSavePartido(partido, categoria);
            }
            if(equipo.equals("visitante")){
                partido.setGolesVisitante(partido.getGolesVisitante()+1);
                partidoDao.save(partido);
                return getSavePartido(partido, categoria);
            }
        }
        return null;
    }

    private ModelAndView sumarFalta(String equipo,long id,long categoria){
        Partido partido = partidoDao.findById(id).get();
        if(equipo!=null){
            if(equipo.equals("local")){
                partido.setFaltasLocal(partido.getFaltasLocal()+1);
                partidoDao.save(partido);
                return getSavePartido(partido, categoria);
            }
            if(equipo.equals("visitante")){
                partido.setFaltasVisitante(partido.getFaltasVisitante()+1);
                partidoDao.save(partido);
                return getSavePartido(partido, categoria);
            }
        }
        return null;
    }





    /*Para cuando se finalize el acta del partido */
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
