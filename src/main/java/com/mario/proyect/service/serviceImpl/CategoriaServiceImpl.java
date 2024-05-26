package com.mario.proyect.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.entity.Categoria;
import com.mario.proyect.repository.CategoriaDAO;
import com.mario.proyect.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDAO categoriaDao;

    @Override
    public ModelAndView getCategorias() {
        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categorias");
        model.addObject("categorias", categoriaDao.findAll());

        return model;
    }

    @Override
    public ModelAndView getCategoria(long id) {
        ModelAndView model = new ModelAndView();

        model.setViewName("categoriaHTML/categoria");
        model.addObject("categoria", categoriaDao.findById(id).get());

        return model;
    }

    @Override
    public ModelAndView addCategoria() {

        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categoriaForm");
        model.addObject("categoriaNueva", new Categoria());

        return model;
    }

    @Override
    public ModelAndView deleteCategoria(long id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/categorias");

        Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
        if (categoriaOptional.isPresent()) {
            categoriaDao.deleteById(id);
        }

        return model;
    }

    @Override
    public ModelAndView saveCategoria(Categoria categoriaNueva, BindingResult bindingResult) {

        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.setViewName("categoriaHTML/categoriaForm");

            model.addObject("categoriaNueva", new Categoria());

            return model;

        }
        model.setViewName("redirect:/categorias");

        categoriaDao.save(categoriaNueva);

        return model;
    }

    @Override
    public ModelAndView editCategoria(long id) {

        ModelAndView model = new ModelAndView();
        Optional<Categoria> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()) {
            model.addObject("categoriaNueva", categoria.get());
        }
        model.setViewName("categoriaHTML/categoriaForm");
        return model;
    }
    
}
