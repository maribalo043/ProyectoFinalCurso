package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.repository.EquipoDAO;
import com.mario.proyect.repository.PartidoDAO;
import com.mario.proyect.service.TorneoSeptiembreService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class TorneoSeptimebreContoller {

    @Autowired
    private TorneoSeptiembreService torneoService;
    
    @GetMapping("/seleccionCategoriaTorneoSep")
    public ModelAndView getSelecionCategoria() {
        return torneoService.getSeleccionCategoria();
    }

    @GetMapping("/partidosSep/{categoria}")
    public ModelAndView getPartidos(@PathVariable long categoria){
        return torneoService.getPartidos(categoria);
    }

    @GetMapping("/jugadores/clasificacion/{categoria}")
    public ModelAndView getClasificacionCategoria(@PathVariable long categoria){
        //return torneoService.getJugadoresGoles(categoria);
        return null;
    }

    @GetMapping("/jugadores/goles/{categoria}")
    public ModelAndView getJugadoresGoles(@PathVariable long categoria){
        return torneoService.getJugadoresGoles(categoria);
    }

    @GetMapping("/jugadores/tarjetas/{categoria}")
    public ModelAndView getJugadoresTarjetas(@PathVariable long categoria){
        return torneoService.getJugadoresTarjetas(categoria);
    }

    @GetMapping("/partidoSep/{id}")
    public ModelAndView getDetallePartido (@PathVariable long id){
        return torneoService.getDetallePartido(id);
    }

    @GetMapping("/partidoSep/edit/{id}")
    public ModelAndView getModificacionPartido(@PathVariable long id) {
        return torneoService.getModificacionPartido(id);
    }
    
    
}
