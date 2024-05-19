package com.mario.proyect.utilidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.categoria.CategoriaDAO;
import com.mario.proyect.partido.Partido;
import com.mario.proyect.partido.PartidoDAO;



@Controller
public class GeneralController {

    @Autowired 
    private CategoriaDAO categoriaDao;

    @Autowired 
    private PartidoDAO partidoDao;

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
        List<Partido> partidos = categoriaDao.findPartidosByNombreCategoria(cat);

        model.setViewName("/torneoHTML/PartidosCategoria");
        model.addObject("partidos", partidos);

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
    
}
