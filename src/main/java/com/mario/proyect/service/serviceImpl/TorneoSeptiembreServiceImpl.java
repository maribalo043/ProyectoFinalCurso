package com.mario.proyect.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.JugadorDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.TorneoSeptiembreService;

import jakarta.validation.constraints.Null;

@Service
public class TorneoSeptiembreServiceImpl implements TorneoSeptiembreService{

    @Autowired 
    private EquipoDAO equipoDao;
    @Autowired 
    private PartidoDAO partidoDao;
    @Autowired 
    private JugadorDAO jugadorDao;
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
    public ModelAndView getJugadoresGoles(long categoria) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/");
        model.addObject("jugadores", jugadorDao.findJugadoresByCategoriaSeptiembre(categoria));
        /*Opcional: ruta que sea el mayor goleador */
        model.addObject("filtro", "goles");
        return model;
    }

    @Override
    public ModelAndView getJugadoresTarjetas(long categoria) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/");
        model.addObject("jugadores", jugadorDao.findJugadoresByCategoriaSeptiembre(categoria));
        /*Opcional: ruta para obtener el jugador con mayor numero de azules y mayor numero de rojas*/
        model.addObject("filtro", "tarjetas");
        return model;
    }

    @Override
    public ModelAndView getDetallePartido(long partido) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/");
        Optional<Partido> partidoOptinal = partidoDao.findById(partido);
        if(partidoOptinal.isPresent()){
            model.addObject("partido", partidoOptinal.get());
        }
        return model;
    }

    @Override
    public ModelAndView getModificacionPartido(long partido) {
        ModelAndView model = new ModelAndView("torneoSeptiembreHTML/");
        Optional<Partido> partidoOptinal = partidoDao.findById(partido);
        if(partidoOptinal.isPresent()){
            model.addObject("partido", partidoOptinal.get());
        }
        return model;
    }
    
}
