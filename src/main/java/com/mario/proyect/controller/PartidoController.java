package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Partido;
import com.mario.proyect.service.PartidoService;

import jakarta.validation.Valid;


@Controller
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping("/partidos")
    public ModelAndView getPartidos() {
        return partidoService.getPartidos();
    }

    @GetMapping("/partido/{idLocal}/{idVisitante}")
    public ModelAndView getPartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
        return partidoService.getPartido(idLocal, idVisitante);
    }

    @GetMapping("/partido/del/{idLocal}/{idVisitante}")
    public ModelAndView deletePartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
        return partidoService.deletePartido(idLocal, idVisitante);
    }

    @GetMapping("/partido/add")
    public ModelAndView addPartido() {
        return partidoService.addPartido();
    }

    @PostMapping("/partido/save")
    public ModelAndView savePartido(@ModelAttribute("partidoNuevo") @Valid Partido partidoNuevo, BindingResult bindingResult) {
        return partidoService.savePartido(partidoNuevo, bindingResult);
    }

    @GetMapping("/partido/edit/{idLocal}/{idVisitante}")
    public ModelAndView editPartido(@PathVariable long idLocal, @PathVariable long idVisitante) {
        return partidoService.editPartido(idLocal, idVisitante);
    }    
}
