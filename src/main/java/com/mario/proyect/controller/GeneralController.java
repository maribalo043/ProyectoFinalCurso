package com.mario.proyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.JugadorDAO;
import com.mario.proyect.repository.PartidoDAO;



@Controller
public class GeneralController {

    @Autowired 
    private CategoriaDAO categoriaDao;

    @Autowired 
    private PartidoDAO partidoDao;

    @Autowired
    private EquipoDAO equipoDao;

    @Autowired
    private JugadorDAO jugadorDao;

    @GetMapping("/torneo")
    public ModelAndView getTorneo() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Torneo");
        return model;
    }
    
    @GetMapping("/nuestrosEquipos")
    public ModelAndView getEquipos() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/NuestrosEquipos");
        return model;
    }
    
    @GetMapping("/contacto")
    public ModelAndView getContacto() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Contacto");
        return model;
    }
    
    @GetMapping("/links")
    public ModelAndView getLinks() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/Links");
        return model;
    }
    
    @GetMapping("/avisoLegal")
    public ModelAndView getAvisoLegal() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/AvisoLegal");
        return model;
    }
    
    @GetMapping("/politicaPrivacidad")
    public ModelAndView getPoliticaDePrivacidad() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/PoliticaPrivacidad");
        return model;
    }

    @GetMapping("/torneo/categorias")
    public ModelAndView getCategorias() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/torneoHTML/SeleccionCategoria");

        model.addObject("categorias", categoriaDao.categoriasActive());
        return model;
    }

    
    @GetMapping("/torneo/categoria/{cat}")
    public ModelAndView getPartidosCategoria(@PathVariable String cat) {
        ModelAndView model = new ModelAndView();
        List<Partido> partidos = partidoDao.findPartidosByCategoria(cat);
        List<Equipo> equipos = equipoDao.findByCategoria_Nombre(cat);

        model.setViewName("/torneoHTML/PartidosCategoria");
        if(!partidos.isEmpty()) {
        	model.addObject("partidos", partidos);
        }
        if(!equipos.isEmpty()) {
        	model.addObject("equiposCategoria", equipos);
        }
        

        return model;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("generalHTML/LogIn");
        return model;
    }
    
    @GetMapping("/logout")
    public ModelAndView getLogOut() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("index");
        return model;
    }

    @GetMapping("/estadisticas")
    public ModelAndView mostrarEstadisticas(@RequestParam(required = false, defaultValue = "18") int edadParametro,
                                            @RequestParam(required = false) Long categoriaId) {
        ModelAndView model = new ModelAndView("generalHTML/Estadisticas");

        model.addObject("totalEquipos", equipoDao.countTotalEquipos());
        model.addObject("totalPartidosEmpatados", partidoDao.countPartidosEmpatados());
        model.addObject("partidosGanadosLocal", partidoDao.countPartidosGanadosLocal());
        model.addObject("partidosGanadosVisitante", partidoDao.countPartidosGanadosVisitante());
        model.addObject("jugadoresMenoresDeEdad", jugadorDao.countJugadoresMenoresDeEdad(edadParametro));
        model.addObject("equiposPorCategoria", equipoDao.countEquiposPorCategoria(categoriaId));
        model.addObject("categoriasConMasDeCincoEquipos", categoriaDao.countCategoriasConMasDeCincoEquipos());
        model.addObject("edadParametro", edadParametro);

        // Agregar las categorías para el formulario
        model.addObject("categorias", categoriaDao.findAll());

        return model;
    }
    
}
