package com.mario.proyect.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Categoria;

public interface CategoriaService {
    
    ModelAndView getCategorias();
    
    ModelAndView getCategoria(long id);

    ModelAndView addCategoria();

    ModelAndView deleteCategoria(long id);

    ModelAndView saveCategoria(Categoria categoriaNueva, BindingResult bindingResult);

    ModelAndView editCategoria(@PathVariable long id);
}
