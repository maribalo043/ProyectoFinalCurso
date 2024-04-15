package com.mario.proyect.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mario.proyect.equipo.Equipo;
import com.mario.proyect.equipo.EquipoDAO;
import com.mario.proyect.partido.PartidoDAO;

public class CategoriaHelper {

    /*
     * Metodo de ayuda para la funcion de borrado de categorias, ademas de sirve
     * para reducir el codigo del controller
     */
    protected ModelAndView helperDelCategoria(long id, CategoriaDAO categoriaDao, EquipoDAO equipoDao,
            PartidoDAO partidoDao) {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/categorias");

        Optional<Categoria> categoriaOptional = categoriaDao.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();

            List<Equipo> equipos = categoria.getEquipos();

            equipos.forEach(equipo -> equipo.setCategoria(null));
            equipoDao.saveAll(equipos);

            categoriaDao.deleteById(id);
        }
        return model;
    }

    @SuppressWarnings("null")
    protected ModelAndView helperSaveCategoria(Categoria categoriaNueva, BindingResult bindingResult,
            CategoriaDAO categoriaDao, EquipoDAO equipoDao, PartidoDAO partidoDao) {

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

}
