package com.mario.proyect.service.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.dto.MensajeDto;
import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.JugadorDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private CategoriaDAO categoriaDao;

    @Autowired
    private PartidoDAO partidoDao;

    @Autowired
    private EquipoDAO equipoDao;

    @Autowired
    private JugadorDAO jugadorDao;

    public ModelAndView getIndex() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("mensaje", new MensajeDto());
        return model;
    }

    public ModelAndView getTorneo() {
        ModelAndView model = new ModelAndView();
        model.setViewName("generalHTML/Torneo");
        return model;
    }

    public ModelAndView getEquipos() {
        ModelAndView model = new ModelAndView();
        model.setViewName("generalHTML/NuestrosEquipos");
        model.addObject("mensaje", new MensajeDto());
        return model;
    }

    public ModelAndView getLinks() {
        ModelAndView model = new ModelAndView();
        model.setViewName("generalHTML/Links");
        model.addObject("mensaje", new MensajeDto());
        return model;
    }

    public ModelAndView getCategorias() {
        ModelAndView model = new ModelAndView();
        model.setViewName("torneoHTML/SeleccionCategoria");
        model.addObject("mensaje", new MensajeDto());
        model.addObject("categorias", categoriaDao.categoriasActive());
        return model;
    }

    public ModelAndView getPartidosCategoria(String cat) {
        ModelAndView model = new ModelAndView();
        List<Partido> partidos = partidoDao.findPartidosByCategoria(cat);
        this.ordenarPartidosPorHora(partidos);
        List<Equipo> equipos = equipoDao.findByCategoria_Nombre(cat);

        ordenarEquipos(equipos);
        model.addObject("mensaje", new MensajeDto());
        model.setViewName("torneoHTML/PartidosCategoria");
        if (!partidos.isEmpty()) {
            model.addObject("partidos", partidos);
        }
        if (!equipos.isEmpty()) {
            model.addObject("equiposCategoria", equipos);
        }

        return model;
    }

    /* ordena los partidos por puntos y sino por golAverage */
    private void ordenarEquipos(List<Equipo> equipos) {
        equipos.sort(Comparator.comparing(Equipo::getPuntos)
                .thenComparing(Equipo::getGolAverage).reversed());
    }

    // Definición del método para ordenar partidos por hora
    private void ordenarPartidosPorHora(List<Partido> partidos) {
        partidos.sort(Comparator.comparing(Partido::getHora));
    }

    public ModelAndView mostrarEstadisticas(@RequestParam(required = false, defaultValue = "18") int edadParametro,
            @RequestParam(required = false) Long categoriaId) {
        ModelAndView model = new ModelAndView("generalHTML/Estadisticas");
        /* Cuenta de todos los atributos */
        model.addObject("totalEquipos", equipoDao.countTotalEquipos());
        model.addObject("totalPartidosEmpatados", partidoDao.countPartidosEmpatados());
        model.addObject("partidosGanadosLocal", partidoDao.countPartidosGanadosLocal());
        model.addObject("partidosGanadosVisitante", partidoDao.countPartidosGanadosVisitante());
        model.addObject("jugadoresMenoresDeEdad", jugadorDao.countJugadoresMenoresDeEdad(edadParametro));
        model.addObject("equiposPorCategoria", equipoDao.countEquiposPorCategoria(categoriaId));
        model.addObject("categoriasConMasDeCincoEquipos", categoriaDao.countCategoriasConMasDeCincoEquipos());
        model.addObject("edadParametro", edadParametro);
        /* Paso de las litas de los objetos */
        model.addObject("equipos", equipoDao.findAll());
        model.addObject("partidosEmpatados", partidoDao.selectPartidosEmpatados());
        model.addObject("ganadosLocal", partidoDao.selectPartidosGanadosLocal());
        model.addObject("ganadosVisitante", partidoDao.selectPartidosGanadosVisitante());
        model.addObject("jugadoresMenoresDeEdadE", jugadorDao.selectJugadoresMenoresDeEdad(edadParametro));
        model.addObject("equiposPorCategoriaE", equipoDao.selectEquiposPorCategoria(categoriaId));
        model.addObject("categoriasConMasDeCincoEquiposE", categoriaDao.selectCategoriasConMasDeCincoEquipos());

        // Agregar las categorías para el formulario
        model.addObject("categorias", categoriaDao.findAll());

        return model;
    }

    @Override
    public ModelAndView envioDatosFormContacto() {
        ModelAndView model = new ModelAndView();

        MensajeDto mensaje = new MensajeDto();

        return model;
    }

}
