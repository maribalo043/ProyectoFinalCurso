package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.service.EquipoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping(value = { "/equipos", "/equipos/{filtro}" })
    public ModelAndView getEquipos(@PathVariable(required = false) String filtro) {
        return equipoService.getEquipos(filtro);
    }

    @GetMapping("equipo/{id}")
    public ModelAndView getEquipo(@PathVariable long id) {
        return equipoService.getEquipo(id);
    }

    @GetMapping("/equipo/del/{id}")
    public ModelAndView deleteEquipo(@PathVariable long id) {
        return equipoService.deleteEquipo(id);
    }

    @GetMapping("equipo/add")
    public ModelAndView addEquipo() {
        return equipoService.addEquipo();
    }

    @PostMapping("/equipo/save")
    public ModelAndView saveEquipo(@ModelAttribute @Valid Equipo equipo, BindingResult bindingResult) {
        return equipoService.saveEquipo(equipo, bindingResult);
    }

    @GetMapping("equipo/edit/{id}")
    public ModelAndView editEquipo(@PathVariable long id) {

        return equipoService.editEquipo(id);
    }

    @GetMapping("/inscripcion/equipo")
    public ModelAndView addEquipoTorneo() {
        return equipoService.addEquipoTorneo();
    }

    @PostMapping("/equipoTorneo/save")
    public ModelAndView saveEquipoTorneo(@ModelAttribute @Valid Equipo equipo, BindingResult bindingResult) {
        return equipoService.saveEquipoTorneo(equipo, bindingResult);
    }

}
