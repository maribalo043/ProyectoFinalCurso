package com.mario.proyect.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.partido.PartidoDAO;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaDAO categoriaDao;
    @Autowired
    private EquipoDAO equipoDao;
    @Autowired
    private PartidoDAO partidoDao;

    private CategoriaHelper helper = new CategoriaHelper();

    @GetMapping("/categorias")
    public ModelAndView getCategorias() {

        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categorias");
        model.addObject("categorias", categoriaDao.findAll());

        return model;
    }

    @GetMapping("/categoria/{id}")
    public ModelAndView getCategoria(@PathVariable long id) {

        ModelAndView model = new ModelAndView();

        model.setViewName("categoriaHTML/categoria");
        model.addObject("categoria", categoriaDao.findById(id).get());

        return model;
    }

    @GetMapping("/categoria/add")
    public ModelAndView addCategoria() {

        ModelAndView model = new ModelAndView();
        model.setViewName("categoriaHTML/categoriaForm");
        model.addObject("categoriaNueva", new Categoria());

        return model;
    }

    @GetMapping("/categoria/del/{id}")
    public ModelAndView deleteCategoria(@PathVariable long id) {

        return helper.helperDelCategoria(id, categoriaDao, equipoDao, partidoDao);
    }

    @PostMapping("categoria/save")
    public ModelAndView saveCategoria(@ModelAttribute Categoria categoriaNueva, BindingResult bindingResult) {

        return helper.helperSaveCategoria(categoriaNueva, bindingResult, categoriaDao, equipoDao, partidoDao);
    }

    @GetMapping("categoria/edit/{id}")
    public ModelAndView editCategoria(@PathVariable long id) {

        ModelAndView model = new ModelAndView();
        Optional<Categoria> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()) {
            model.addObject("categoriaNueva", categoria.get());
        }
        model.setViewName("categoriaHTML/categoriaForm");
        return model;
    }

}
