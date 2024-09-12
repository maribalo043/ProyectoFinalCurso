package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mario.proyect.entity.Partido;
import com.mario.proyect.service.TorneoSeptiembreService;

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
        return torneoService.getEquiposClasificacion(categoria);
    }

    @GetMapping("/partidoSep/{id}/{categoria}")
    public ModelAndView getDetallePartido (@PathVariable long id, @PathVariable long categoria){
        return torneoService.getDetallePartido(id,categoria);
    }

    @GetMapping("/partidoSep/edit/{id}/{categoria}")
    public ModelAndView getModificacionPartido(@PathVariable long id, @PathVariable long categoria) {
        return torneoService.getModificacionPartido(id, categoria);
    }

    @GetMapping("/partidoSep/modificar/{modificador}/{id}/{equipo}/{categoria}")
    public ModelAndView modificarPartido(@PathVariable String modificador,@PathVariable String equipo, @PathVariable long id, @PathVariable long categoria){
        
        return torneoService.modificarPartido(modificador,equipo, id, categoria);
    }
    
}
