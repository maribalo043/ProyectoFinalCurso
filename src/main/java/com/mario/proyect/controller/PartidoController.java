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

    @GetMapping("/partido/{id}")
    public ModelAndView getPartido(@PathVariable long id) {
        return partidoService.getPartido(id);
    }

    @GetMapping("/partido/del/{id}")
    public ModelAndView deletePartido(@PathVariable long id) {
        return partidoService.deletePartido(id);
    }

    @GetMapping("/partido/add")
    public ModelAndView addPartido() {
        return partidoService.addPartido();
    }

    @PostMapping("/partido/save")
    public ModelAndView savePartido(@ModelAttribute("partidoNuevo") @Valid Partido partidoNuevo, BindingResult bindingResult) {
        return partidoService.savePartido(partidoNuevo, bindingResult);
    }

    @GetMapping("/partido/edit/{id}")
    public ModelAndView editPartido(@PathVariable long id) {
        return partidoService.editPartido(id);
    }    
}
