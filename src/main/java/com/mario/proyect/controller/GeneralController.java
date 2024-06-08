package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.service.GeneralService;

@Controller
public class GeneralController {

    @Autowired
    private GeneralService servicio;

    @GetMapping("/")
    public ModelAndView getIndex() {
        return servicio.getIndex();
    }

    @GetMapping("/torneo")
    public ModelAndView getTorneo() {
        return servicio.getTorneo();
    }
    
    @GetMapping("/nuestrosEquipos")
    public ModelAndView getEquipos() {
    	return servicio.getEquipos();
    }
    
    @GetMapping("/links")
    public ModelAndView getLinks() {
    	return servicio.getLinks();
    }

    @GetMapping("/torneo/categorias")
    public ModelAndView getCategorias() {
        return servicio.getCategorias();
    }
    
    @GetMapping("/torneo/categoria/{cat}")
    public ModelAndView getPartidosCategoria(@PathVariable String cat) {
        return servicio.getPartidosCategoria(cat);
    }

    @GetMapping("/estadisticas")
    public ModelAndView mostrarEstadisticas(@RequestParam(required = false, defaultValue = "18") int edadParametro, @RequestParam(required = false) Long categoriaId) {
        return servicio.mostrarEstadisticas(edadParametro, categoriaId);
    }
    @GetMapping("/formContacto")
    public ModelAndView envioDatosFormContacto() {
        return servicio.envioDatosFormContacto();
    }
    @GetMapping("/inscrito")
    public ModelAndView inscrito() {
        ModelAndView model = new ModelAndView();
        model.setViewName("torneoHTML/adesion");
        return model;
    }
}
