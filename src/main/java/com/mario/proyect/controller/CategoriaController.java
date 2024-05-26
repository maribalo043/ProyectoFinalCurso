package com.mario.proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Categoria;
import com.mario.proyect.service.CategoriaService;

@Controller
public class CategoriaController {

    @Autowired 
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ModelAndView getCategorias() {

        return categoriaService.getCategorias();

    }

    @GetMapping("/categoria/{id}")
    public ModelAndView getCategoria(@PathVariable long id) {

        return categoriaService.getCategoria(id);
        
    }

    @GetMapping("/categoria/add")
    public ModelAndView addCategoria() {

        return categoriaService.addCategoria();
    }

    @GetMapping("/categoria/del/{id}")
    public ModelAndView deleteCategoria(@PathVariable long id) {

       return categoriaService.deleteCategoria(id);
    }

    @PostMapping("categoria/save")
    public ModelAndView saveCategoria(@ModelAttribute Categoria categoriaNueva, BindingResult bindingResult) {

        return categoriaService.saveCategoria(categoriaNueva, bindingResult);
    }

    @GetMapping("categoria/edit/{id}")
    public ModelAndView editCategoria(@PathVariable long id) {

        return categoriaService.editCategoria(id);
    }

}
